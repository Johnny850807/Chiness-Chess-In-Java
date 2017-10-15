package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import game.CallBack;
import game.ChessBoard;
import game.ChinessChessGame;
import game.item.chess.Chess;
import game.player.Player;

public class ChineseChessPaintPanel extends JPanel implements CallBack, MouseListener, MouseMotionListener, KeyListener{
	private Image backgroundImage;
	private Image selectedImage;
	private	ChinessChessGame chessGame;
	private ChessBoard chessBoard;
	
	private Player currentTurnPlayer;
	private Dimension[][] chessDimensionBlock = new Dimension[10][9]; // used to determine all the chesses' location
	private Dimension closestToMouseDimension;  //used to draw the selection block
	private Chess selectedChess;
	
	public ChineseChessPaintPanel(ChinessChessGame chessGame, Image backgroundImage) throws IOException{
		this.chessGame = chessGame;
		this.backgroundImage = backgroundImage;
		this.selectedImage = ImageIO.read(new File("selected.png"));
		setupDimensionBlock();
		setFocusable(true);  //ensure that the keyboard event can be passed into the panel.
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		chessGame.setCallback(this);
		chessGame.startGame();
	}
	
	private void setupDimensionBlock() {
		final int ORG_X = 52;  //original
		final int ORG_Y = 37;
        
        for (int y = 0 ; y < 10 ; y ++)
        	for (int x = 0 ; x < 9 ; x ++)
        		chessDimensionBlock[y][x] = new Dimension(ORG_X + x*59  , ORG_Y + y*61);
	}

	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final int CHESS_SIZE = 55;
        final int SELECTED_BLOCK_SIZE = 58;
        final int OFFSET_X = 25;
        final int OFFSET_Y = 24;
        final int OFFSET_SELECTED_X = 1;
        final int OFFSET_SELECTED_Y = 4;
        g.drawImage(backgroundImage, 0, 0, null);
        
        Chess[][] chessStatus = chessBoard.getChesses();
        for (int y = 0 ; y < 10 ; y ++)
        	for (int x = 0 ; x < 9 ; x ++)
        	{
        		Dimension dimension = chessDimensionBlock[y][x];
        		if (chessBoard.hasChess(x, y))
	        		g.drawImage(chessStatus[y][x].getImage(), (int)(dimension.getWidth() - OFFSET_X), (int)(dimension.getHeight() - OFFSET_Y), 
	        				CHESS_SIZE, CHESS_SIZE, null);
        	}
        
        if (closestToMouseDimension != null)
        {
        	g.drawImage(selectedImage, (int)(closestToMouseDimension.getWidth() - OFFSET_SELECTED_X - OFFSET_X), 
        			(int)(closestToMouseDimension.getHeight() - OFFSET_SELECTED_Y - OFFSET_Y), 
        				SELECTED_BLOCK_SIZE, SELECTED_BLOCK_SIZE, null);
        }
        	
        if (selectedChess != null)
        {		
        	Dimension selectedDimension = chessDimensionBlock[selectedChess.getY()][selectedChess.getX()];
        	g.drawImage(selectedImage, (int)(selectedDimension.getWidth() - OFFSET_SELECTED_X - OFFSET_X), 
        			(int)(selectedDimension.getHeight() - OFFSET_SELECTED_Y - OFFSET_Y), 
        				SELECTED_BLOCK_SIZE, SELECTED_BLOCK_SIZE, null);
        }
    }

	@Override
	public void onGameStarted() {
		repaint();  // paint the prepared chesses.
	}

	@Override
	public void onPlayerTurn(Player player) {
		currentTurnPlayer = player;
	}

	@Override
	public void onGameStatusUpdated(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
		repaint();
	}

	@Override
	public void onMoveRejected(Player player, Chess chess) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameOver(Player winner) {
		System.out.println("The winner is " + winner.getTeam().toString() + ".");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		Dimension dimension = getClosestDimension(e.getX(), e.getY());
		int x = 0, y = 0;
		
		OutLoop:
		for (int i = 0 ; i < 10 ; i ++)
			for (int j = 0 ; j < 9 ; j ++)
				if (dimension == chessDimensionBlock[i][j])
				{
					y = i;
					x = j;
					break OutLoop;
				}

		handleSelectedEvent(x, y);
		repaint();
	}
	
	private void handleSelectedEvent(int x, int y){
		Chess chess = chessBoard.getChess(x, y);
		if (selectedChess == null)
			selectedChess = chess;
		else
		{
			chessGame.moveChess(currentTurnPlayer, selectedChess, x, y);
			selectedChess = null;
			closestToMouseDimension = null;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {
		closestToMouseDimension = null;
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		closestToMouseDimension = getClosestDimension(e.getX(), e.getY());
		repaint();
	}
	
	private Dimension getClosestDimension(int x, int y){
		double closedOffset = 1000;
		Dimension closestDimension = new Dimension(1000, 1000);
		for (int i = 0 ; i < 10 ; i ++)
        	for (int j = 0 ; j < 9 ; j ++)
        	{
        		Dimension dimension = chessDimensionBlock[i][j];
        		int offsetX = Math.abs(x - (int)dimension.getWidth());
        		int offsetY = Math.abs(y - (int)dimension.getHeight());
        		double offsetZ = Math.sqrt(offsetX*offsetX + offsetY*offsetY);

        		if (offsetZ < closedOffset)
        		{
        			closedOffset = offsetZ;
        			closestDimension = dimension;
        		}
        	}
		
		return closestDimension;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_R)
			chessGame.rollback();
	}

	
}

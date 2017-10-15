package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import game.CallBack;
import game.ChessBoard;
import game.ChinessChessGame;
import game.item.chess.Chess;
import game.player.Player;

public class ChineseChessPaintPane extends JPanel implements CallBack, MouseListener{
	private Image backgroundImage;
	private	ChinessChessGame chessGame;
	
	public ChineseChessPaintPane(ChinessChessGame chessGame, Image backgroundImage){
		this.chessGame = chessGame;
		this.backgroundImage = backgroundImage;
		chessGame.setCallback(this);
		chessGame.startGame();
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }

	@Override
	public void onGameStarted() {
		
	}

	@Override
	public void onPlayerTurn(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameStatusUpdated(ChessBoard chessBoard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMoveRejected(Player player, Chess chess) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameOver(Player winner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}

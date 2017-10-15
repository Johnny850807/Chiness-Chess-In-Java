package view;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import game.CallBack;
import game.ChessBoard;
import game.ChinessChessGame;
import game.item.chess.Chess;
import game.player.Player;

public class ChinessChessGameView extends JFrame{
	private final int WIDTH;
	private final int HEIGHT;
	private ChineseChessPaintPane paintPane;
	
	public ChinessChessGameView(ChinessChessGame chessGame) throws IOException{
		Image backgroundImage = ImageIO.read(new File("chessboard.png"));
		this.paintPane = new ChineseChessPaintPane(chessGame, backgroundImage);
		WIDTH = backgroundImage.getWidth(null);
		HEIGHT = backgroundImage.getHeight(null);
		setupUI();
	}

	private void setupUI() {
		setBasicSetting();
		setCenterWindowLocation();
		setLayout();
	}
	
	private void setBasicSetting(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	private void setCenterWindowLocation(){
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    setLocation(x, y);
	}
	
	private void setLayout(){
		this.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new BorderLayout());
		add(paintPane, BorderLayout.CENTER);
	}

	
	public static void main(String[] argv){
		try{
			ChinessChessGame chessGame = new ChinessChessGame();
			ChinessChessGameView chessGameView = new ChinessChessGameView(chessGame);
			chessGameView.setVisible(true);
			chessGameView.pack();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

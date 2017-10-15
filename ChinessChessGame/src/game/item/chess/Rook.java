package game.item.chess;

import java.awt.Image;

import game.ChessBoard;
import game.ChessColor;

//��
public class Rook extends Chess {

	public Rook(ChessBoard context, ChessColor color, Image img) {
		super(context, color, img);
	}
	
	@Override
	public boolean validateDistination(int x, int y) {
		return false;
	}

}

package game.item.chess;

import java.awt.Dimension;
import java.awt.Image;
import java.util.HashSet;
import java.util.Set;

import game.ChessBoard;
import game.ChessColor;

//ио
public class Rook extends NeedCountBlockingChess {

	public Rook(ChessBoard context, ChessColor color, Image img) {
		super(context, color, img);
	}
	
	@Override
	public boolean validateDistination(int x, int y) {
		return isStraight(x, y) && getBlockedAmount(x, y) == 0;
	}
	
	private boolean isStraight(int x, int y){
		return x == getX() || y == getY();
	}

}

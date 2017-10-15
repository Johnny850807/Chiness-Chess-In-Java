package game.item.chess;

import java.awt.Image;

import game.ChessBoard;
import game.ChessColor;

//¶H
public class Elephant extends Chess {

	public Elephant(ChessBoard context, ChessColor color, Image img) {
		super(context, color, img);
	}

	@Override
	public boolean validateDistination(int x, int y) {
		if (!context.isAcrossRiver(getColor(), x, y) && !isBlockedOnTheWay(x, y) && is¥Ð(x, y))
			return true;
		return false;
	}
	
	
	private boolean isBlockedOnTheWay(int x, int y){
		if (x > getX() && y > getY())
			return context.hasChess(getX() + 1, getY() + 1);
		if (x > getX() && y < getY())
			return context.hasChess(getX() + 1, getY() - 1);
		if (x < getX() && y > getY())
			return context.hasChess(getX() - 1, getY() + 1);
		else
			return context.hasChess(getX() - 1, getY() - 1);
	}
	
	private boolean is¥Ð(int x, int y){
		return Math.abs(getX() - x) == 2 && Math.abs(getY() - y) == 2;
	}

}

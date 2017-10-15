package game.item.chess;

import java.awt.Image;

import game.ChessBoard;
import game.ChessColor;

//°¨
public class Knight extends Chess {

	public Knight(ChessBoard context, ChessColor color, Image img) {
		super(context, color, img);
	}

	@Override
	public boolean validateDistination(int x, int y) {
		if (is¤é(x, y) && isBlockedOnTheWay(x, y)) 
			return true;
		return false;
	}
	
	private boolean is¤é(int x, int y) {
		return (Math.abs(getX() - x) == 2 && Math.abs(getY() - y) == 1) || (Math.abs(getX() - x) == 1 && Math.abs(getY() - y) == 2);
	}

	private boolean isBlockedOnTheWay(int x, int y) {
		if (x - getX() == 2) 
			return context.hasChess(getX() + 1, getY());
		else if (getX() - x == 2) 
			return context.hasChess(getX() - 1, getY());
		else if (y - getY() == 2)
			return context.hasChess(getX(), getY() - 1);
		else if (getY() - y == 2)
			return context.hasChess(getX(), getY() + 1);
		return false;
	}
	
}

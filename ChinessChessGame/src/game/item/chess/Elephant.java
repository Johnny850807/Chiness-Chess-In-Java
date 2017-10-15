package game.item.chess;

import java.awt.Image;

import game.ChessBoard;
import game.ChessColor;

//¶H
public class Elephant extends Chess {

	public Elephant(ChessBoard context, ChessColor color, Image img) {
		super(context, color, img);
	}

	public boolean canMoveTo(int x, int y) {
		if (context.hasChess(x, y) && context.getChess(x, y).getColor() == getColor())
			return false;
		if (context.isAcrossRiver(getColor(), x, y) && Math.abs(getX() - x) == 2 && Math.abs(getY() - y) == 2)
			return true;
		return false;
	}

}

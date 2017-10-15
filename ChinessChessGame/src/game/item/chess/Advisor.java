package game.item.chess;

import java.awt.Image;

import game.ChessBoard;
import game.ChessColor;

//�h
public class Advisor extends Chess {

	public Advisor(ChessBoard context, ChessColor color, Image img) {
		super(context, color, img);
	}

	@Override
	public boolean validateDistination(int x, int y) {
		if (context.isInsideCastle(getColor(), x, y) && Math.abs(getX() - x) == 1 && Math.abs(getY() - y) == 1)
			return true;
		return false;
	}

}

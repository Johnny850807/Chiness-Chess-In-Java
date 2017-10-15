package game.item.chess;

import java.awt.Image;

import game.ChessBoard;
import game.ChessColor;

//±N­x
public class General extends Chess {

	public General(ChessBoard context, ChessColor color, Image img) {
		super(context, color, img);
	}

	@Override
	public boolean validateDistination(int x, int y) {
		if (context.getChess(x, y) instanceof General) {
			int high,low;
			if (getY() > y) {
				high = getY();
				low = y;
			}
			else {
				high = y;
				low = getY();
			}
			for (int i = low + 1; i < high; i++)
				if (context.hasChess(x, i)) 
					return false;
			return true;
		}
		if (context.isInsideCastle(getColor(), x, y) && ((Math.abs(getX() - x) == 1 && getY() == y) ||(getX() == x && Math.abs(getY() - y) == 1)))
			return true;
		return false;
	}

}

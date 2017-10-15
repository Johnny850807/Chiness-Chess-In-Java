package game.item.chess;

import java.awt.Image;

import game.ChessBoard;
import game.ChessColor;

//±N­x
public class General extends Chess {

	public General(ChessBoard context, ChessColor color, Image img) {
		super(context, color, img);
	}

	public boolean canMoveTo(int x, int y) {
		
		return false;
	}

}

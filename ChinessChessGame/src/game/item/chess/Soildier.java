package game.item.chess;

import java.awt.Image;

import game.ChessBoard;
import game.ChessColor;

//§L
public class Soildier extends Chess {

	public Soildier(ChessBoard context, ChessColor color, Image img) {
		super(context, color, img);
	}

	@Override
	public boolean validateDistination(int x, int y) {
		return true;
	}

}

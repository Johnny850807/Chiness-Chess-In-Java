package game.item.chess;

import java.awt.Image;

import game.ChessBoard;
import game.ChessColor;

//��
public class Cannon extends Chess {

	public Cannon(ChessBoard context, ChessColor color, Image img) {
		super(context, color, img);
	}

	public boolean canMoveTo(int x, int y) {
		return false;
	}

}
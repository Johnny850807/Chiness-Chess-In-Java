package game.item.chess;

import java.awt.Image;

import game.ChessBoard;
import game.ChessColor;

//¬¶
public class Cannon extends NeedCountBlockingChess {

	public Cannon(ChessBoard context, ChessColor color, Image img) {
		super(context, color, img);
	}

	@Override
	public boolean validateDistination(int x, int y) {
		return isStraight(x, y) && ( canEatWithOneBlock(x, y) || canRunWithoutBlock(x, y));
	}
	
	private boolean isStraight(int x, int y){
		return getX() == x || getY() == y;
	}
	
	private boolean canEatWithOneBlock(int x, int y){
		return getBlockedAmount(x, y) == 1 && context.hasChess(x, y);
	}
	
	private boolean canRunWithoutBlock(int x, int y){
		return getBlockedAmount(x, y) == 0 && !context.hasChess(x, y);
	}

}

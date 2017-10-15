package game.item.chess;

import java.awt.Dimension;
import java.awt.Image;
import java.util.HashSet;
import java.util.Set;

import game.ChessBoard;
import game.ChessColor;

//ио
public class Rook extends Chess {

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
	
	private int getBlockedAmount(int x, int y) {
		int offsetX = x - getX();
		int offsetY = y - getY();
		int count = 0;
		
		if (offsetX != 0)
		{
			int offset = offsetX / Math.abs(offsetX);
			for (int i = getX() + offset; i != x; i += offset)
				if (context.hasChess(i, getY()))
					count ++;	
		}
			
		
		if (offsetY != 0)
		{
			int offset = offsetY / Math.abs(offsetY);
			for (int i = getY() + offset; i != y; i += offset)
				if (context.hasChess(getX(), i))
					count ++;
		}
			
		
		return count - (context.hasChess(x, y) ? 1 : 0); //minus one for not counting the eaten chess in.
	}

}

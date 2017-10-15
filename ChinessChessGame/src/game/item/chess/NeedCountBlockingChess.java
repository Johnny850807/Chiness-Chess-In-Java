package game.item.chess;

import java.awt.Image;

import game.ChessBoard;
import game.ChessColor;

public abstract class NeedCountBlockingChess extends Chess{

	public NeedCountBlockingChess(ChessBoard context, ChessColor color, Image img) {
		super(context, color, img);
	}
	
	protected int getBlockedAmount(int x, int y) {
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
		
		return count; //minus one for not counting the eaten chess in.
	}

}

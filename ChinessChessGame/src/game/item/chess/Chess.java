package game.item.chess;
import java.awt.Image;

import game.ChessBoard;
import game.ChessColor;
import game.ChinessChessGame;
import game.PaintObject;

public abstract class Chess extends PaintObject implements Cloneable{
	protected ChessBoard context;
	protected ChessColor color;

	
	public Chess(ChessBoard context, ChessColor color, Image img) {
		this.context = context;
		this.color = color;
		this.image = img;
	}
	
	public boolean canMoveTo(int x, int y){
		return validateEatenChessNotMineOrNull(x, y) && validateDistination(x, y);
	}
	
	protected boolean validateEatenChessNotMineOrNull(int x, int y){
		return !context.hasChess(x, y) || context.getChess(x, y).getColor() != getColor();
	}
	
	/**
	 * @return the boolean that whether the move matches the rule of the chess.
	 */
	protected abstract boolean validateDistination(int x, int y);
	
	public ChessBoard getContext() {
		return context;
	}

	public void setContext(ChessBoard context) {
		this.context = context;
	}

	public ChessColor getColor() {
		return color;
	}

	public void setColor(ChessColor color) {
		this.color = color;
	}

	@Override
	public Chess clone(){
		return (Chess)super.clone();
	}
}

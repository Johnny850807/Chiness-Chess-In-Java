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

	public abstract boolean canMoveTo(int x, int y);
	
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

	public Image getImg() {
		return image;
	}

	public void setImg(Image img) {
		this.image = img;
	}

	@Override
	public Chess clone(){
		return (Chess)super.clone();
	}
}

package game.item.chess;
import java.awt.Image;

import game.ChessBoard;
import game.ChessColor;
import game.ChinessChessGame;
import game.PaintObject;

public abstract class Chess extends PaintObject implements Cloneable{

	private ChessBoard context;

	private ChessColor color;

	private int x;

	private int y;

	private Image img;

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
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	@Override
	public Chess clone(){
		return (Chess)super.clone();
	}
}

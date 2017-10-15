package game.item.chess;

import java.awt.Dimension;
import java.awt.Image;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.HashSet;
import java.util.Set;

import game.ChessBoard;
import game.ChessColor;

//§L
public class Soildier extends Chess {

	public Soildier(ChessBoard context, ChessColor color, Image img) {
		super(context, color, img);
	}

	@Override
	public boolean validateDistination(int x, int y) {
		if (context.isAcrossRiver(getColor(), getX(), getY()))
			return isLeftOrRight(x, y) || isOnlyForward(x, y);
		
		return isOnlyForward(x, y);
	}
	
	private boolean isLeftOrRight(int x, int y){
		Set<Dimension> straightSet = new HashSet<>();
		straightSet.add(new Dimension(getX() + 1, getY()));
		straightSet.add(new Dimension(getX() - 1, getY()));
		
		return straightSet.contains(new Dimension(x, y));
	}
	
	private boolean isOnlyForward(int x, int y){
		if (getColor() == ChessColor.RED)
			return x == getX() && y == getY() - 1;;
			
		return x == getX() && y == getY() + 1;
	}

}

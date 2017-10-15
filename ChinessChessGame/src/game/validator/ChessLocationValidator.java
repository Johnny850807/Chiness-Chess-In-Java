package game.validator;

import game.ChessColor;
import game.item.chess.Chess;

public interface ChessLocationValidator {
	public boolean isInsideCastle(ChessColor chessColor, int x, int y);
	public boolean isAcrossRiver(ChessColor chessColor, int x, int y);
	
}

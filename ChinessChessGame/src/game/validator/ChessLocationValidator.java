package game.validator;

import game.ChessColor;
import game.item.chess.Chess;

public interface ChessLocationValidator {
	public boolean isAcrossCastle(ChessColor chessColor, int x, int y);
	public boolean isAcrossRiver(ChessColor chessColor, int x, int y);
	
}

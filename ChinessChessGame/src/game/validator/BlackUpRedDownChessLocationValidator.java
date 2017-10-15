package game.validator;

import game.ChessColor;

public class BlackUpRedDownChessLocationValidator implements ChessLocationValidator{

	@Override
	public boolean isInsideCastle(ChessColor color, int x, int y) {
		if (color == ChessColor.RED)
			return y >= 7 && y <= 9
					&& x >= 3 && x <= 5;
		else
			return y >= 0 && y <= 2
					&& x >= 3 && x <= 5;
	}

	@Override
	public boolean isAcrossRiver(ChessColor color, int x, int y) {
		if (color == ChessColor.RED)
			return y >= 0 && y <= 4;
		else
			return y >= 5 && y <= 9;
	}

}

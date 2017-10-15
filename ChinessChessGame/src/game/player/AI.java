package game.player;

import game.ChessBoard;
import game.ChessColor;
import game.command.ChessMoveCommand;

public class AI extends Player {

	public AI(String name, ChessColor team) {
		super(name, team);
	}

	@Override
	public ChessMoveCommand makeChoice(ChessBoard chessBoard) {
		// TODO AI ¼g³o
		return null;
	}


}

package game.player;

import game.ChessBoard;
import game.ChessColor;
import game.command.ChessMoveCommand;

public class HumanPlayer extends Player{

	public HumanPlayer(String name, ChessColor team) {
		super(name, team);
	}

	@Override
	public ChessMoveCommand makeChoice(ChessBoard chessBoard) {
		throw new RuntimeException("Not supported.");
	}

}

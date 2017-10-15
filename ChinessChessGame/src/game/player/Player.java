package game.player;
import game.ChessBoard;
import game.ChessColor;
import game.command.ChessMoveCommand;

public abstract class Player {

	private String name;
	private ChessColor team;
	
	public Player(String name, ChessColor team) {
		this.name = name;
		this.team = team;
	}

	public abstract ChessMoveCommand makeChoice(ChessBoard chessBoard);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ChessColor getTeam() {
		return team;
	}

	public void setTeam(ChessColor team) {
		this.team = team;
	}

}

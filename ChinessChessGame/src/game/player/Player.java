package game.player;
import game.ChessBoard;
import game.ChessColor;
import game.ChinessChessGame;
import game.command.ChessMoveCommand;

public abstract class Player {
	protected ChinessChessGame game;
	private String name;
	private ChessColor team;
	
	public Player(ChinessChessGame game, String name, ChessColor team) {
		this.game = game;
		this.name = name;
		this.team = team;
	}

	public abstract void runChoice();

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

package game.player;

import java.util.Stack;

import game.ChessColor;
import game.ChinessChessGame;
import game.command.ChessMoveCommand;
import game.item.chess.Chess;

public class HumanPlayer extends Player{
	private static Stack<MoveInfo> moves = new Stack<>();
	public static void setCommand(MoveInfo move){
		moves.add(move);
	}

	public HumanPlayer(ChinessChessGame game, String name, ChessColor team) {
		super(game, name, team);
	}

	@Override
	public void runChoice() {
		while(moves.isEmpty());
		
		MoveInfo move = moves.pop();
		game.moveChess(move.getPlayer(), move.getChess(), move.getX(), move.getY());
	}

	public static class MoveInfo {
		private Player player;
		private Chess chess;
		private int x;
		private int y;
		
		public MoveInfo(Player player, Chess chess, int x, int y) {
			this.player = player;
			this.chess = chess;
			this.x = x;
			this.y = y;
		}
		public Player getPlayer() {
			return player;
		}

		public Chess getChess() {
			return chess;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
	}
}

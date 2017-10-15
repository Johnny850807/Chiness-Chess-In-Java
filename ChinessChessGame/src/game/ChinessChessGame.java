package game;
import java.util.List;

import javax.management.RuntimeErrorException;

import game.command.ChessMoveCommand;
import game.command.ChessMoveCommandImp;
import game.item.chess.Chess;
import game.player.HumanPlayer;
import game.player.Player;
import game.validator.BlackUpRedDownChessLocationValidator;
import game.validator.ChessLocationValidator;

public class ChinessChessGame{
	private Player[] players = {new HumanPlayer("Player1", ChessColor.BLACK), new HumanPlayer("Player2", ChessColor.RED)};
	private ChessBoard chessBoard = new ChessBoard();
	private Player nowTurnPlayer;  
	private CallBack callback;
	private boolean gameStarted = false;

	public void startGame() {
		if (callback == null)
			throw new RuntimeException("Callback should be set.");
		
		setupBoardAsync();
	}
	
	public void setCallback(CallBack callback) {
		this.callback = callback;
	}

	
	private void setupBoardAsync() {
		new Thread(){
			public void run() {
				chessBoard.setupBoard();
				gameStarted = true;
				callback.onGameStarted();
				runPlayerTurns();
			};
		}.start();
	}

	private void runPlayerTurns() {
		while(chessBoard.getWinColor() == ChessColor.NO_COLOR)  // if nobody wins
			onNextPlayerTurn();
	}
	
	public void onNextPlayerTurn() {
		nowTurnPlayer = nowTurnPlayer == players[1] ? players[0] : players[1];
		callback.onPlayerTurn(nowTurnPlayer);
	}

	public void moveChess(Player player, Chess chess, int x, int y) {
		if (validateChessMove(player, chess, x, y))
			chessBoard.executeMoveCommand(new ChessMoveCommandImp(x, y, chess));
		else
			callback.onMoveRejected(player, chess);
	}

	private boolean validateChessMove(Player player, Chess chess, int x, int y) {
		return player.getTeam() == chess.getColor() &&
				chess.canMoveTo(x, y) &&
					nowTurnPlayer == player && gameStarted;
	}

	public void rollback() {
		chessBoard.rollback();
	}
}

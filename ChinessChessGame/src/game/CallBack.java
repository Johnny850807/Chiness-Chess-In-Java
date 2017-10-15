package game;
import game.item.chess.Chess;
import game.player.Player;

public interface CallBack {

	public void onGameStarted();
	
	public void onPlayerTurn(Player player);

	public void onGameStatusUpdated(ChessBoard chessBoard);

	public void onMoveSuccessfully(Player player, Chess chess);
	
	public void onMoveRejected(Player player, Chess chess);

	public void onGameOver(Player winner);

}

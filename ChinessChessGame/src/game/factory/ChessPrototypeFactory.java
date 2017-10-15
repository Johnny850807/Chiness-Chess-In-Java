package game.factory;
import java.util.HashMap;
import java.util.Map;

import game.ChessBoard;
import game.ChessColor;
import game.ChessName;
import game.item.chess.Chess;

public class ChessPrototypeFactory {
	private Map<ChessName, Chess> chessMap = new HashMap<>();
	private ChessBoard context;
	
	public ChessPrototypeFactory(ChessBoard context) {
		this.context = context;
	}

	public void prepare() {

	}

	public Chess createChess(ChessName name, ChessColor color) {
		return null;
	}

}

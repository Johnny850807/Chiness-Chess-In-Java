package game.factory;
import java.util.HashMap;
import java.util.Map;

import game.ChessBoard;
import game.ChessColor;
import game.ChessName;
import game.item.chess.Chess;
import game.item.chess.General;

import static game.ChessName.*;
import static game.ChessColor.*;

public class ChessPrototypeFactory {
	private Map<ChessInfo, Chess> chessMap = new HashMap<>();
	private ChessBoard context;
	
	public ChessPrototypeFactory(ChessBoard context) {
		this.context = context;
	}

	public void prepare() {
		chessMap.put(new ChessInfo(GENERAL, RED), new General(context, RED, null));
	}

	public Chess createChess(ChessName name, ChessColor color) {
		return null;
	}

	
	public static class ChessInfo{
		private ChessName chessName;
		private ChessColor chessColor;
		
		public ChessInfo(ChessName chessName, ChessColor chessColor) {
			this.chessName = chessName;
			this.chessColor = chessColor;
		}
		
		public ChessName getChessName() {
			return chessName;
		}
		public void setChessName(ChessName chessName) {
			this.chessName = chessName;
		}
		public ChessColor getChessColor() {
			return chessColor;
		}
		public void setChessColor(ChessColor chessColor) {
			this.chessColor = chessColor;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((chessColor == null) ? 0 : chessColor.hashCode());
			result = prime * result + ((chessName == null) ? 0 : chessName.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ChessInfo other = (ChessInfo) obj;
			if (chessColor != other.chessColor)
				return false;
			if (chessName != other.chessName)
				return false;
			return true;
		}

	}
}

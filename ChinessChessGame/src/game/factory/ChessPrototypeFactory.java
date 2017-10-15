package game.factory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

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
		
		try{
			prepare();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void prepare() throws IOException {
		chessMap.put(new ChessInfo(ROOK, RED), new General(context, RED, ImageIO.read(new File("chess/red_soildier.png"))));
		chessMap.put(new ChessInfo(KNIGHT, RED), new General(context, RED, ImageIO.read(new File("chess/red_soildier.png"))));
		chessMap.put(new ChessInfo(ELEPHANT, RED), new General(context, RED, ImageIO.read(new File("chess/red_soildier.png"))));
		chessMap.put(new ChessInfo(ADVISOR, RED), new General(context, RED, ImageIO.read(new File("chess/red_soildier.png"))));
		chessMap.put(new ChessInfo(GENERAL, RED), new General(context, RED, ImageIO.read(new File("chess/red_soildier.png"))));
		chessMap.put(new ChessInfo(SOILDIER, RED), new General(context, RED, ImageIO.read(new File("chess/red_soildier.png"))));
		chessMap.put(new ChessInfo(CANNON, RED), new General(context, RED, ImageIO.read(new File("chess/red_soildier.png"))));
		
		chessMap.put(new ChessInfo(ROOK, BLACK), new General(context, BLACK, ImageIO.read(new File("chess/red_soildier.png"))));
		chessMap.put(new ChessInfo(KNIGHT, BLACK), new General(context, BLACK, ImageIO.read(new File("chess/red_soildier.png"))));
		chessMap.put(new ChessInfo(ELEPHANT, BLACK), new General(context, BLACK, ImageIO.read(new File("chess/red_soildier.png"))));
		chessMap.put(new ChessInfo(ADVISOR, BLACK), new General(context, BLACK, ImageIO.read(new File("chess/red_soildier.png"))));
		chessMap.put(new ChessInfo(GENERAL, BLACK), new General(context, BLACK, ImageIO.read(new File("chess/red_soildier.png"))));
		chessMap.put(new ChessInfo(SOILDIER, BLACK), new General(context, BLACK, ImageIO.read(new File("chess/red_soildier.png"))));
		chessMap.put(new ChessInfo(CANNON, BLACK), new General(context, BLACK, ImageIO.read(new File("chess/red_soildier.png"))));
	}

	public Chess createChess(ChessName name, ChessColor color) {
		return chessMap.get(new ChessInfo(name, color)).clone();  // shadow clone
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

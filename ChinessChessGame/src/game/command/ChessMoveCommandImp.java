package game.command;

import game.ChessBoard;
import game.item.chess.Chess;

public class ChessMoveCommandImp implements ChessMoveCommand {
	private int x;
	private int y;
	private ChessBoard context;
	private ChessMemento memento;
	
	public ChessMoveCommandImp(int x, int y, Chess chess, ChessBoard context) {
		this.x = x;
		this.y = y;
		this.context = context;
		this.memento = new ChessMemento(chess, context.getChess(x, y), chess.getX(), chess.getY());
	}

	public void execute() {
		Chess eatenChess = context.moveAndGetEatenChess(memento.getMovedChess(), x, y);
		if (eatenChess != null)
			System.out.println(eatenChess.getColor().toString() + " has one chess " + eatenChess.getClass().getName() + " eaten.");
		else
			System.out.println("Chess moved, but no chess eaten.");
	}

	public void rollback() {
		if (memento == null)
			throw new IllegalStateException("The command has been rolled back.");
		
		Chess back = context.moveAndGetEatenChess(memento.getMovedChess(), memento.getBack_x(), memento.getBack_y());
		if (back != null)
			throw new IllegalStateException("Why there is a chess eaten when rolling back?");
		
		Chess eatenChess = memento.getEatenChess();
		
		if (eatenChess != null)
			back = context.moveAndGetEatenChess(eatenChess, eatenChess.getX(), eatenChess.getY());
		memento = null;
	}

	private class ChessMemento{
		private Chess movedChess;
		private Chess eatenChess;  // the chess at the location in the last move (got eaten)
		private int back_x;
		private int back_y;
		
		public ChessMemento(Chess movedChess, Chess eatenChess, int back_x, int back_y) {
			this.movedChess = movedChess;
			this.eatenChess = eatenChess;
			this.back_x = back_x;
			this.back_y = back_y;
		}

		public Chess getMovedChess() {
			return movedChess;
		}

		public Chess getEatenChess() {
			return eatenChess;
		}
		
		public int getBack_x() {
			return back_x;
		}

		public int getBack_y() {
			return back_y;
		}

	}
}

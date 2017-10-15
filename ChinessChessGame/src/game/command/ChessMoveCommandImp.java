package game.command;

import game.item.chess.Chess;

public class ChessMoveCommandImp implements ChessMoveCommand {
	private int x;
	private int y;
	private ChessMemento memento;
	private Chess chess;
	
	public ChessMoveCommandImp(int x, int y, Chess chess) {
		this.x = x;
		this.y = y;
		this.chess = chess;
		this.memento = new ChessMemento(chess.getX(), chess.getY());
	}

	public void execute() {
		chess.setX(x);
		chess.setY(y);
	}

	public void rollback() {
		chess.setX(memento.getBack_x());
		chess.setY(memento.getBack_y());
	}

	private class ChessMemento{
		private int back_x;
		private int back_y;
		
		public ChessMemento(int back_x, int back_y) {
			this.back_x = back_x;
			this.back_y = back_y;
		}

		public int getBack_x() {
			return back_x;
		}

		public void setBack_x(int back_x) {
			this.back_x = back_x;
		}

		public int getBack_y() {
			return back_y;
		}

		public void setBack_y(int back_y) {
			this.back_y = back_y;
		}
		
	}
}

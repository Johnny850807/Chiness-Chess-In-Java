package game;

import java.util.Stack;

import game.command.ChessMoveCommand;
import game.factory.ChessPrototypeFactory;
import game.item.chess.Chess;
import game.player.Player;

public class ChessBoard {
	private Chess[][] chesses = new Chess[10][9];
	private Stack<ChessMoveCommand> moveCommandStack = new Stack<>();
	private ChessPrototypeFactory prototypeFactory = new ChessPrototypeFactory(this);
	
	public void setupBoard(){
		
	}
	
	public ChessColor getWinColor(){
		return ChessColor.NO_COLOR;
	}
	
	public void executeMoveCommand(ChessMoveCommand chessMoveCommand){
		moveCommandStack.push(chessMoveCommand);
		chessMoveCommand.execute();
	}
	
	public void rollback(){
		ChessMoveCommand chessMoveCommand = moveCommandStack.pop();
		chessMoveCommand.rollback();
	}
	
	public boolean isAcrossCastle(Chess chess){
		if (chess.getColor() == ChessColor.RED)
			return chess.getY() >= 7 && chess.getY() <= 9
					&& chess.getX() >= 3 && chess.getX() <= 5;
		else
			return chess.getY() >= 0 && chess.getY() <= 2
					&& chess.getX() >= 3 && chess.getX() <= 5;
	}
	
	public boolean isAcrossRiver(Chess chess){
		if (chess.getColor() == ChessColor.RED)
			return chess.getY() >= 5 && chess.getY() <= 9;
		else
			return chess.getY() >= 0 && chess.getY() <= 4;
	}
	
	public Chess getChess(int x, int y){
		return chesses[y][x];
	}
	
	public boolean hasChess(int x, int y){
		return chesses[x][y] != null;
	}
}

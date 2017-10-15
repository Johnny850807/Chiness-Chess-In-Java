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
		return false;
	}
	
	public boolean isAcrossRiver(Chess chess){
		return false;
	}
	
	public Chess getChess(int x, int y){
		return chesses[y][x];
	}
	
	public boolean hasChess(int x, int y){
		return chesses[x][y] != null;
	}
}

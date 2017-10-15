package game;

import java.util.Stack;

import game.command.ChessMoveCommand;
import game.factory.ChessPrototypeFactory;
import game.item.chess.Chess;
import game.player.Player;
import game.validator.BlackUpRedDownChessLocationValidator;
import game.validator.ChessLocationValidator;

public class ChessBoard {
	private ChessLocationValidator chessLocationValidator = new BlackUpRedDownChessLocationValidator();
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
	
	public boolean isInsideCastle(ChessColor color, int x, int y){
		return chessLocationValidator.isInsideCastle(color, x, y);
	}
	
	public boolean isAcrossRiver(ChessColor color, int x, int y){
		return chessLocationValidator.isAcrossRiver(color, x, y);
	}
	
	public Chess getChess(int x, int y){
		return chesses[y][x];
	}
	
	public boolean hasChess(int x, int y){
		return chesses[x][y] != null;
	}
}

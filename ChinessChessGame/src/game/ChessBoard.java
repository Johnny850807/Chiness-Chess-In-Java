package game;

import java.util.Stack;

import game.command.ChessMoveCommand;
import game.factory.ChessPrototypeFactory;
import game.item.chess.Chess;
import game.player.Player;
import game.validator.BlackUpRedDownChessLocationValidator;
import game.validator.ChessLocationValidator;

import static game.ChessName.*;
import static game.ChessColor.*;

public class ChessBoard{
	private ChessLocationValidator chessLocationValidator = new BlackUpRedDownChessLocationValidator();
	private Chess[][] chesses = new Chess[10][9];
	private Stack<ChessMoveCommand> moveCommandStack = new Stack<>();
	private ChessPrototypeFactory prototyper = new ChessPrototypeFactory(this);
	
	public void setupBoard(){
		putChess(prototyper.createChess(ROOK, BLACK), x, y);
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
		chessMoveCommand = null;
		System.gc();
	}
	
	public boolean isInsideCastle(ChessColor color, int x, int y){
		return chessLocationValidator.isInsideCastle(color, x, y);
	}
	
	public boolean isAcrossRiver(ChessColor color, int x, int y){
		return chessLocationValidator.isAcrossRiver(color, x, y);
	}
	
	/**
	 * Update the (x,y) of chess and eat the chess at the location if one exists there.
	 * @return the eaten chess.
	 */
	public Chess moveAndGetEatenChess(Chess chess, int x, int y){
		Chess eatenChess = getChess(x, y);
		chesses[chess.getY()][chess.getX()] = null;  // move from the current location, set null.
		putChess(chess, x, y);
		return eatenChess;
	}
	
	public void putChess(Chess chess, int x, int y){
		chesses[y][x] = chess; // the location the chess moves to, set the chess.
		chess.setX(x); // then update the location.
		chess.setY(y);
	}
	
	public Chess getChess(int x, int y){
		return chesses[y][x];
	}
	
	public boolean hasChess(int x, int y){
		return chesses[x][y] != null;
	}
	
	
	public Chess[][] getClonedChesses(){
		Chess[][] cloned = new Chess[10][9];
		for (int i = 0 ; i < 10 ; i ++)
			for (int j = 0 ; j < 9 ; j ++)
				cloned[i][j] = chesses[i][j];
		return cloned;
	}
}

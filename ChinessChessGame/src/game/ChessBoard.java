package game;

import java.util.Stack;

import game.command.ChessMoveCommand;
import game.factory.ChessPrototypeFactory;
import game.item.chess.Chess;
import game.item.chess.General;
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
		putChess(prototyper.createChess(ROOK, BLACK), 0, 0);
		putChess(prototyper.createChess(KNIGHT, BLACK), 1, 0);
		putChess(prototyper.createChess(ELEPHANT, BLACK), 2, 0);
		putChess(prototyper.createChess(ADVISOR, BLACK), 3, 0);
		putChess(prototyper.createChess(GENERAL, BLACK), 4, 0);
		putChess(prototyper.createChess(ADVISOR, BLACK), 5, 0);
		putChess(prototyper.createChess(ELEPHANT, BLACK), 6, 0);
		putChess(prototyper.createChess(KNIGHT, BLACK), 7, 0);
		putChess(prototyper.createChess(ROOK, BLACK), 8, 0);
		putChess(prototyper.createChess(SOILDIER, BLACK), 0, 3);
		putChess(prototyper.createChess(SOILDIER, BLACK), 2, 3);
		putChess(prototyper.createChess(SOILDIER, BLACK), 4, 3);
		putChess(prototyper.createChess(SOILDIER, BLACK), 6, 3);
		putChess(prototyper.createChess(SOILDIER, BLACK), 8, 3);
		putChess(prototyper.createChess(CANNON, BLACK), 1, 2);
		putChess(prototyper.createChess(CANNON, BLACK), 7, 2);
		
		putChess(prototyper.createChess(ROOK, RED), 0, 9);
		putChess(prototyper.createChess(KNIGHT, RED), 1, 9);
		putChess(prototyper.createChess(ELEPHANT, RED), 2, 9);
		putChess(prototyper.createChess(ADVISOR, RED), 3, 9);
		putChess(prototyper.createChess(GENERAL, RED), 4, 9);
		putChess(prototyper.createChess(ADVISOR, RED), 5, 9);
		putChess(prototyper.createChess(ELEPHANT, RED), 6, 9);
		putChess(prototyper.createChess(KNIGHT, RED), 7, 9);
		putChess(prototyper.createChess(ROOK, RED), 8, 9);
		putChess(prototyper.createChess(SOILDIER, RED), 0, 6);
		putChess(prototyper.createChess(SOILDIER, RED), 2, 6);
		putChess(prototyper.createChess(SOILDIER, RED), 4, 6);
		putChess(prototyper.createChess(SOILDIER, RED), 6, 6);
		putChess(prototyper.createChess(SOILDIER, RED), 8, 6);
		putChess(prototyper.createChess(CANNON, RED), 1, 7);
		putChess(prototyper.createChess(CANNON, RED), 7, 7);
	}
	
	public void executeMoveCommand(ChessMoveCommand chessMoveCommand){
		moveCommandStack.push(chessMoveCommand);
		chessMoveCommand.execute();
	}
	
	public void rollback(){
		if (!moveCommandStack.isEmpty())
		{
			ChessMoveCommand chessMoveCommand = moveCommandStack.pop();
			chessMoveCommand.rollback();
			chessMoveCommand = null;
		}
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
		return chesses[y][x] != null;
	}
	
	public Chess[][] getChesses() {
		return chesses;
	}
	
	public ChessColor getWinColor(){
		Chess blackGeneral = null, redGeneral = null;
		for (int i = 0 ; i < 10 ; i ++)
			for (int j = 0 ; j < 9 ; j ++)
				if (chesses[i][j] instanceof General)
				{
					if (chesses[i][j].getColor() == RED)
						redGeneral = chesses[i][j];
					else
						blackGeneral = chesses[i][j];
				}
					
		return blackGeneral == null ? RED : 
						redGeneral == null ? BLACK : NO_COLOR;
	}
}

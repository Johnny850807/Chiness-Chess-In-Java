package test;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import game.ChessBoard;
import game.item.chess.Chess;

public class ChessMoveRuleUnitTest {
	private ChessBoard chessBoard;
	private Chess[][] chesses;
	
	@Before
	public void setUp() throws Exception {
		chessBoard = new ChessBoard();
		chesses = chessBoard.getChesses();
	}

	@Test
	public void testSoildier() {
		Chess soildier = chessBoard.getChess(0, 6);
		assertTrue(soildier.canMoveTo(0, 2)); //can go forward
		assertTrue(!soildier.canMoveTo(0, 8)); //cannot go forward twice
		assertTrue(!soildier.canMoveTo(0, 7)); //cannot go back
		chessBoard.moveAndGetEatenChess(soildier, 1, 4);
		
		assertTrue(soildier.canMoveTo(0, 4)); //can go left
		assertTrue(soildier.canMoveTo(2, 4)); //can go right
		assertTrue(!soildier.canMoveTo(1, 5)); //cannot go back
		
		chessBoard.rollback();
	}
	
	@Test
	public void testConnon() {
		Chess connon = chessBoard.getChess(1, 7);
		assertTrue(connon.canMoveTo(1, 3)); //can across many blocks
		assertTrue(connon.canMoveTo(1, 0)); //can eat the knight by across a chess
		assertTrue(!connon.canMoveTo(0, 8)); //cannot go obliquely
		
		chessBoard.moveAndGetEatenChess(connon, 1, 0);
		assertTrue(!connon.canMoveTo(4, 0)); //cannot eat the general by across two chesses
		
		chessBoard.rollback();
	}
	
	@Test
	public void testRook() {
		Chess rook = chessBoard.getChess(0, 9);
		assertTrue(rook.canMoveTo(0, 8)); //can across one block
		chessBoard.moveAndGetEatenChess(rook, 0, 8);

		assertTrue(rook.canMoveTo(8, 8)); //can across many blocks
		assertTrue(!rook.canMoveTo(0, 5)); //cannot across any chess
		
		chessBoard.moveAndGetEatenChess(rook, 3, 8);
		assertTrue(!rook.canMoveTo(4, 7)); //cannot go obliquely
		
		chessBoard.rollback();
		chessBoard.rollback();
	}
	
	@Test
	public void testKnight() {
		Chess knight = chessBoard.getChess(1, 9); //left knight
		assertTrue(knight.canMoveTo(2, 7)); //can go right vertically without block
		assertTrue(knight.canMoveTo(0, 7)); //can go left vertically without block
		assertTrue(!knight.canMoveTo(3, 8)); //cannot go right horizontally with right block
		
		knight = chessBoard.getChess(7, 9); //right knight
		assertTrue(knight.canMoveTo(6, 7)); //can go left vertically without block
		assertTrue(knight.canMoveTo(0, 8)); //can go right vertically without block
		assertTrue(!knight.canMoveTo(5, 8)); //cannot go left horizontally with right block
		
		chessBoard.moveAndGetEatenChess(knight, 6, 7); // let's have a block forward
		assertTrue(!knight.canMoveTo(5, 5)); //cannot go left vertically with forward block
		assertTrue(!knight.canMoveTo(7, 5)); //cannot go right vertically with forward block
		
		chessBoard.moveAndGetEatenChess(knight, 6, 2); // let's have a block back
		assertTrue(!knight.canMoveTo(5, 4)); //cannot go left vertically with back block
		assertTrue(!knight.canMoveTo(7, 4)); //cannot go right vertically with back block
		
		chessBoard.rollback();
		chessBoard.rollback();
	}
	
	@Test
	public void testElephant() {
		Chess redElephant = chessBoard.getChess(2, 9);
		assertTrue(redElephant.canMoveTo(0, 7)); //can go left vertically without forward block
		assertTrue(redElephant.canMoveTo(4, 7)); //can go right vertically without forward block
		
		Chess blackElephant = chessBoard.getChess(2, 0);
		assertTrue(blackElephant.canMoveTo(0, 2)); //can go left vertically without back block
		assertTrue(blackElephant.canMoveTo(4, 2)); //can go right vertically without back block
		
		chessBoard.moveAndGetEatenChess(redElephant, 3, 7); // let's have a block forward
		assertTrue(!redElephant.canMoveTo(1, 5)); //cannot go left vertically with forward block
		assertTrue(!redElephant.canMoveTo(5, 5)); //cannot go right vertically with forward block
		
		chessBoard.moveAndGetEatenChess(blackElephant, 3, 2); // let's have a block back
		assertTrue(!blackElephant.canMoveTo(1, 4)); //cannot go left vertically with forward block
		assertTrue(!blackElephant.canMoveTo(5, 4)); //cannot go right vertically with forward block
		
		chessBoard.rollback();
		chessBoard.rollback();
	}
	
	@Test
	public void testAdvisor() {
		Chess advisor = chessBoard.getChess(3, 9);
		chessBoard.moveAndGetEatenChess(advisor, 4, 8); //go middle of the castle
		assertTrue(advisor.canMoveTo(3, 7)); //can go to the four directions
		assertTrue(advisor.canMoveTo(5, 7));
		assertTrue(advisor.canMoveTo(5, 9));
		
		chessBoard.moveAndGetEatenChess(advisor, 5, 7); //go right top of the castle
		assertTrue(advisor.canMoveTo(4, 8));
		assertTrue(!advisor.canMoveTo(6, 8)); //cannot go out of the castle
		
		chessBoard.rollback();
		chessBoard.rollback();
	}
	
	@Test
	public void testGeneral() {
		Chess general = chessBoard.getChess(4, 9);
		chessBoard.moveAndGetEatenChess(general, 4, 8); //go middle of the castle
		assertTrue(general.canMoveTo(4, 7)); //can go to the four directions
		assertTrue(general.canMoveTo(3, 8));
		assertTrue(general.canMoveTo(5, 8));
		assertTrue(general.canMoveTo(4, 9));
		
		assertTrue(!general.canMoveTo(3, 7)); //cannot go obliquely
		assertTrue(!general.canMoveTo(5, 7));

		chessBoard.moveAndGetEatenChess(chessBoard.getChess(4, 0), 5, 7); //move the opposite general to right side
		assertTrue(!general.canMoveTo(5, 8)); //cannot face to the opposite general
		
		chessBoard.rollback();
		chessBoard.rollback();
	}

}

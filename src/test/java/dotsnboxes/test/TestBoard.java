package dotsnboxes.test;

import junit.framework.TestCase;
import dotsnboxes.core.Board;
import dotsnboxes.core.Box;
import dotsnboxes.core.Line;

public class TestBoard extends TestCase {

	public void testCreateBoxesOneByOne() {
		
		assertEquals(1, new Board(1, 1).getBoxes().length);
	}
	
	public void testCreateBoxesTwoByOne() {
		
		assertEquals(2, new Board(2, 1).getBoxes().length);
	}
	
	public void testCreateBoxesOneByTwo() {
		
		assertEquals(2, new Board(1, 2).getBoxes().length);
	}
	
	public void testCreateBoxesTwoByTwo() {
		
		assertEquals(4, new Board(2, 2).getBoxes().length);
	}
	
	public void testCreateLinesOneByOne() {
		
		Board board = new Board(1, 1);
		
		Line[] lines = board.getLines();
		assertEquals(4, lines.length);
		
		Box box = board.getBoxes()[0];
		
		Line h11 = lines[0];
		Line h21 = lines[1];
		Line v11 = lines[2];
		Line v12 = lines[3];
		
		assertEquals(h11, box.getTopLine());
		assertEquals(1, h11.getBoxCount());
		assertTrue(h11.containsBox(box));
		
		assertEquals(h21, box.getBottomLine());
		assertEquals(1, h21.getBoxCount());
		assertTrue(h21.containsBox(box));
		
		assertEquals(v11, box.getLeftLine());
		assertEquals(1, v11.getBoxCount());
		assertTrue(v11.containsBox(box));
		
		assertEquals(v12, box.getRightLine());
		assertEquals(1, v12.getBoxCount());
		assertTrue(v12.containsBox(box));
	}
	
	public void testCreateLinesTwoByOne() {
		
		Board board = new Board(2, 1);
		
		Line[] lines = board.getLines();
		assertEquals(7, lines.length);
		
		Box topBox = board.getBoxes()[0];
		Box bottomBox = board.getBoxes()[1];
		
		Line h11 = lines[0];
		Line h21 = lines[1];
		Line h31 = lines[2];
		Line v11 = lines[3];
		Line v12 = lines[4];
		Line v21 = lines[5];
		Line v22 = lines[6];
		
		assertEquals(1, h11.getBoxCount());
		assertEquals(h11, topBox.getTopLine());
		assertTrue(h11.containsBox(topBox));
		
		assertEquals(2, h21.getBoxCount());
		assertEquals(h21, topBox.getBottomLine());
		assertEquals(h21, bottomBox.getTopLine());
		assertTrue(h21.containsBox(topBox));
		assertTrue(h21.containsBox(bottomBox));
		
		assertEquals(1, h31.getBoxCount());
		assertEquals(h31, bottomBox.getBottomLine());
		assertTrue(h31.containsBox(bottomBox));
		
		assertEquals(1, v11.getBoxCount());
		assertEquals(v11, topBox.getLeftLine());
		assertTrue(v11.containsBox(topBox));
		
		assertEquals(1, v12.getBoxCount());
		assertEquals(v12, topBox.getRightLine());
		assertTrue(v12.containsBox(topBox));
		
		assertEquals(1, v21.getBoxCount());
		assertEquals(v21, bottomBox.getLeftLine());
		assertTrue(v21.containsBox(bottomBox));
		
		assertEquals(1, v22.getBoxCount());
		assertEquals(v22, bottomBox.getRightLine());
		assertTrue(v22.containsBox(bottomBox));
	}
	
	public void testCreateLinesOneByTwo() {
		
		Board board = new Board(1, 2);
		
		Line[] lines = board.getLines();
		assertEquals(7, lines.length);
		
		Box leftBox = board.getBoxes()[0];
		Box rightBox = board.getBoxes()[1];
		
		Line h11 = lines[0];
		Line h12 = lines[1];
		Line h21 = lines[2];
		Line h22 = lines[3];
		Line v11 = lines[4];
		Line v12 = lines[5];
		Line v13 = lines[6];
		
		assertEquals(1, h11.getBoxCount());
		assertEquals(h11, leftBox.getTopLine());
		assertTrue(h11.containsBox(leftBox));
		
		assertEquals(1, h12.getBoxCount());
		assertEquals(h12, rightBox.getTopLine());
		assertTrue(h12.containsBox(rightBox));
		
		assertEquals(1, h21.getBoxCount());
		assertEquals(h21, leftBox.getBottomLine());
		assertTrue(h21.containsBox(leftBox));
		
		assertEquals(1, h22.getBoxCount());
		assertEquals(h22, rightBox.getBottomLine());
		assertTrue(h22.containsBox(rightBox));
		
		assertEquals(1, v11.getBoxCount());
		assertEquals(v11, leftBox.getLeftLine());
		assertTrue(v11.containsBox(leftBox));
		
		assertEquals(2, v12.getBoxCount());
		assertEquals(v12, leftBox.getRightLine());
		assertEquals(v12, rightBox.getLeftLine());
		assertTrue(v12.containsBox(leftBox));
		assertTrue(v12.containsBox(rightBox));
		
		assertEquals(1, v13.getBoxCount());
		assertEquals(v13, rightBox.getRightLine());
		assertTrue(v13.containsBox(rightBox));
	}
	
	public void testCreateLinesTwoByTwo() {
		
		Board board = new Board(2, 2);
		
		Line[] lines = board.getLines();
		assertEquals(12, lines.length);
		
		Box topLeftBox = board.getBoxes()[0];
		Box topRightBox = board.getBoxes()[1];
		Box bottomLeftBox = board.getBoxes()[2];
		Box bottomRightBox = board.getBoxes()[3];
		
		Line h11 = lines[0];
		Line h12 = lines[1];
		Line h21 = lines[2];
		Line h22 = lines[3];
		Line h31 = lines[4];
		Line h32 = lines[5];
		Line v11 = lines[6];
		Line v12 = lines[7];
		Line v13 = lines[8];
		Line v21 = lines[9];
		Line v22 = lines[10];
		Line v23 = lines[11];
		
		assertEquals(1, h11.getBoxCount());
		assertEquals(h11, topLeftBox.getTopLine());
		assertTrue(h11.containsBox(topLeftBox));
		
		assertEquals(1, h12.getBoxCount());
		assertEquals(h12, topRightBox.getTopLine());
		assertTrue(h12.containsBox(topRightBox));
		
		assertEquals(2, h21.getBoxCount());
		assertEquals(h21, topLeftBox.getBottomLine());
		assertEquals(h21, bottomLeftBox.getTopLine());
		assertTrue(h21.containsBox(topLeftBox));
		assertTrue(h21.containsBox(bottomLeftBox));
		
		assertEquals(2, h22.getBoxCount());
		assertEquals(h22, topRightBox.getBottomLine());
		assertEquals(h22, bottomRightBox.getTopLine());
		assertTrue(h22.containsBox(topRightBox));
		assertTrue(h22.containsBox(bottomRightBox));
		
		assertEquals(1, h31.getBoxCount());
		assertEquals(h31, bottomLeftBox.getBottomLine());
		assertTrue(h31.containsBox(bottomLeftBox));
		
		assertEquals(1, h32.getBoxCount());
		assertEquals(h32, bottomRightBox.getBottomLine());
		assertTrue(h32.containsBox(bottomRightBox));
		
		assertEquals(1, v11.getBoxCount());
		assertEquals(v11, topLeftBox.getLeftLine());
		assertTrue(v11.containsBox(topLeftBox));
		
		assertEquals(2, v12.getBoxCount());
		assertEquals(v12, topLeftBox.getRightLine());
		assertEquals(v12, topRightBox.getLeftLine());
		assertTrue(v12.containsBox(topLeftBox));
		assertTrue(v12.containsBox(topRightBox));
		
		assertEquals(1, v13.getBoxCount());
		assertEquals(v13, topRightBox.getRightLine());
		assertTrue(v13.containsBox(topRightBox));
		
		assertEquals(1, v21.getBoxCount());
		assertEquals(v21, bottomLeftBox.getLeftLine());
		assertTrue(v21.containsBox(bottomLeftBox));
		
		assertEquals(2, v22.getBoxCount());
		assertEquals(v22, bottomLeftBox.getRightLine());
		assertEquals(v22, bottomRightBox.getLeftLine());
		assertTrue(v22.containsBox(bottomLeftBox));
		assertTrue(v22.containsBox(bottomRightBox));
		
		assertEquals(1, v23.getBoxCount());
		assertEquals(v23, bottomRightBox.getRightLine());
		assertTrue(v23.containsBox(bottomRightBox));
	}
}

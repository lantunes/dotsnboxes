package dotsnboxes.test;

import junit.framework.TestCase;
import dotsnboxes.core.Box;
import dotsnboxes.core.graphical.Dot;
import dotsnboxes.core.graphical.GraphicalBoard;
import dotsnboxes.core.graphical.GraphicalBoardSettings;
import dotsnboxes.core.graphical.GraphicalLine;

public class TestGraphicalBoard extends TestCase {

	public void testCreateDotsOneByOne() {
		
		Dot[] oneByOne = new GraphicalBoard(1, 1, getSettings()).getDots();

		assertEquals(4, oneByOne.length);
		
		Dot d1 = oneByOne[0];
		Dot d2 = oneByOne[1];
		Dot d3 = oneByOne[2];
		Dot d4 = oneByOne[3];
		
		assertEquals(10, d1.getX());
		assertEquals(10, d1.getY());
		
		assertEquals(10, d2.getX());
		assertEquals(40, d2.getY());
		
		assertEquals(40, d3.getX());
		assertEquals(10, d3.getY());
		
		assertEquals(40, d4.getX());
		assertEquals(40, d4.getY());
	}
	
	public void testCreateDotsTwoByOne() {
		
		Dot[] twoByOne = new GraphicalBoard(2, 1, getSettings()).getDots();
		
		assertEquals(6, twoByOne.length);
		
		Dot d1 = twoByOne[0];
		Dot d2 = twoByOne[1];
		Dot d3 = twoByOne[2];
		Dot d4 = twoByOne[3];
		Dot d5 = twoByOne[4];
		Dot d6 = twoByOne[5];
		
		assertEquals(10, d1.getX());
		assertEquals(10, d1.getY());
		
		assertEquals(10, d2.getX());
		assertEquals(40, d2.getY());
		
		assertEquals(10, d3.getX());
		assertEquals(70, d3.getY());
		
		assertEquals(40, d4.getX());
		assertEquals(10, d4.getY());
		
		assertEquals(40, d5.getX());
		assertEquals(40, d5.getY());
		
		assertEquals(40, d6.getX());
		assertEquals(70, d6.getY());
	}
	
	public void testCreateDotsOneByTwo() {
		
		Dot[] twoByOne = new GraphicalBoard(1, 2, getSettings()).getDots();
		
		assertEquals(6, twoByOne.length);
		
		Dot d1 = twoByOne[0];
		Dot d2 = twoByOne[1];
		Dot d3 = twoByOne[2];
		Dot d4 = twoByOne[3];
		Dot d5 = twoByOne[4];
		Dot d6 = twoByOne[5];
		
		assertEquals(10, d1.getX());
		assertEquals(10, d1.getY());
		
		assertEquals(10, d2.getX());
		assertEquals(40, d2.getY());
		
		assertEquals(40, d3.getX());
		assertEquals(10, d3.getY());
		
		assertEquals(40, d4.getX());
		assertEquals(40, d4.getY());
		
		assertEquals(70, d5.getX());
		assertEquals(10, d5.getY());
		
		assertEquals(70, d6.getX());
		assertEquals(40, d6.getY());
	}
	
	public void testCreateDotsTwoByTwo() {
		
		Dot[] twoByTwo = new GraphicalBoard(2, 2, getSettings()).getDots();
		
		assertEquals(9, twoByTwo.length);
		
		Dot d1 = twoByTwo[0];
		Dot d2 = twoByTwo[1];
		Dot d3 = twoByTwo[2];
		Dot d4 = twoByTwo[3];
		Dot d5 = twoByTwo[4];
		Dot d6 = twoByTwo[5];
		Dot d7 = twoByTwo[6];
		Dot d8 = twoByTwo[7];
		Dot d9 = twoByTwo[8];
		
		assertEquals(10, d1.getX());
		assertEquals(10, d1.getY());
		
		assertEquals(10, d2.getX());
		assertEquals(40, d2.getY());
		
		assertEquals(10, d3.getX());
		assertEquals(70, d3.getY());
		
		assertEquals(40, d4.getX());
		assertEquals(10, d4.getY());
		
		assertEquals(40, d5.getX());
		assertEquals(40, d5.getY());
		
		assertEquals(40, d6.getX());
		assertEquals(70, d6.getY());

		assertEquals(70, d7.getX());
		assertEquals(10, d7.getY());
		
		assertEquals(70, d8.getX());
		assertEquals(40, d8.getY());
		
		assertEquals(70, d9.getX());
		assertEquals(70, d9.getY());
	}
	
	public void testCreateBoxesOneByOne() {
		
		assertEquals(1, new GraphicalBoard(1, 1, getSettings()).getBoxes().length);
	}
	
	public void testCreateBoxesTwoByOne() {
		
		assertEquals(2, new GraphicalBoard(2, 1, getSettings()).getBoxes().length);
	}
	
	public void testCreateBoxesOneByTwo() {
		
		assertEquals(2, new GraphicalBoard(1, 2, getSettings()).getBoxes().length);
	}
	
	public void testCreateBoxesTwoByTwo() {
		
		assertEquals(4, new GraphicalBoard(2, 2, getSettings()).getBoxes().length);
	}
	
	public void testCreateLinesOneByOne() {
		
		GraphicalBoard board = new GraphicalBoard(1 , 1, getSettings());
		
		GraphicalLine[] lines = board.getLines();
		assertEquals(4, lines.length);
		
		Box box = board.getBoxes()[0];
		
		GraphicalLine h11 = lines[0];
		GraphicalLine h21 = lines[1];
		GraphicalLine v11 = lines[2];
		GraphicalLine v12 = lines[3];
		
		assertEquals(20, h11.getX());
		assertEquals(12, h11.getY());
		assertEquals(h11, box.getTopLine());
		assertEquals(1, h11.getBoxCount());
		assertTrue(h11.containsBox(box));
		
		assertEquals(20, h21.getX());
		assertEquals(42, h21.getY());
		assertEquals(h21, box.getBottomLine());
		assertEquals(1, h21.getBoxCount());
		assertTrue(h21.containsBox(box));
		
		assertEquals(12, v11.getX());
		assertEquals(20, v11.getY());
		assertEquals(v11, box.getLeftLine());
		assertEquals(1, v11.getBoxCount());
		assertTrue(v11.containsBox(box));
		
		assertEquals(42, v12.getX());
		assertEquals(20, v12.getY());
		assertEquals(v12, box.getRightLine());
		assertEquals(1, v12.getBoxCount());
		assertTrue(v12.containsBox(box));
	}
	
	public void testCreateLinesTwoByOne() {
		
		GraphicalBoard board = new GraphicalBoard(2, 1, getSettings());
		
		GraphicalLine[] lines = board.getLines();
		assertEquals(7, lines.length);
		
		Box topBox = board.getBoxes()[0];
		Box bottomBox = board.getBoxes()[1];
		
		GraphicalLine h11 = lines[0];
		GraphicalLine h21 = lines[1];
		GraphicalLine h31 = lines[2];
		GraphicalLine v11 = lines[3];
		GraphicalLine v12 = lines[4];
		GraphicalLine v21 = lines[5];
		GraphicalLine v22 = lines[6];
		
		assertEquals(20, h11.getX());
		assertEquals(12, h11.getY());
		assertEquals(1, h11.getBoxCount());
		assertEquals(h11, topBox.getTopLine());
		assertTrue(h11.containsBox(topBox));
		
		assertEquals(20, h21.getX());
		assertEquals(42, h21.getY());
		assertEquals(2, h21.getBoxCount());
		assertEquals(h21, topBox.getBottomLine());
		assertEquals(h21, bottomBox.getTopLine());
		assertTrue(h21.containsBox(topBox));
		assertTrue(h21.containsBox(bottomBox));
		
		assertEquals(20, h31.getX());
		assertEquals(72, h31.getY());
		assertEquals(1, h31.getBoxCount());
		assertEquals(h31, bottomBox.getBottomLine());
		assertTrue(h31.containsBox(bottomBox));
		
		assertEquals(12, v11.getX());
		assertEquals(20, v11.getY());
		assertEquals(1, v11.getBoxCount());
		assertEquals(v11, topBox.getLeftLine());
		assertTrue(v11.containsBox(topBox));
		
		assertEquals(42, v12.getX());
		assertEquals(20, v12.getY());
		assertEquals(1, v12.getBoxCount());
		assertEquals(v12, topBox.getRightLine());
		assertTrue(v12.containsBox(topBox));
		
		assertEquals(12, v21.getX());
		assertEquals(50, v21.getY());
		assertEquals(1, v21.getBoxCount());
		assertEquals(v21, bottomBox.getLeftLine());
		assertTrue(v21.containsBox(bottomBox));
		
		assertEquals(42, v22.getX());
		assertEquals(50, v22.getY());
		assertEquals(1, v22.getBoxCount());
		assertEquals(v22, bottomBox.getRightLine());
		assertTrue(v22.containsBox(bottomBox));
	}
	
	public void testCreateLinesOneByTwo() {
		
		GraphicalBoard board = new GraphicalBoard(1, 2, getSettings());
		
		GraphicalLine[] lines = board.getLines();
		assertEquals(7, lines.length);
		
		Box leftBox = board.getBoxes()[0];
		Box rightBox = board.getBoxes()[1];
		
		GraphicalLine h11 = lines[0];
		GraphicalLine h12 = lines[1];
		GraphicalLine h21 = lines[2];
		GraphicalLine h22 = lines[3];
		GraphicalLine v11 = lines[4];
		GraphicalLine v12 = lines[5];
		GraphicalLine v13 = lines[6];
		
		assertEquals(20, h11.getX());
		assertEquals(12, h11.getY());
		assertEquals(1, h11.getBoxCount());
		assertEquals(h11, leftBox.getTopLine());
		assertTrue(h11.containsBox(leftBox));
		
		assertEquals(50, h12.getX());
		assertEquals(12, h12.getY());
		assertEquals(1, h12.getBoxCount());
		assertEquals(h12, rightBox.getTopLine());
		assertTrue(h12.containsBox(rightBox));
		
		assertEquals(20, h21.getX());
		assertEquals(42, h21.getY());
		assertEquals(1, h21.getBoxCount());
		assertEquals(h21, leftBox.getBottomLine());
		assertTrue(h21.containsBox(leftBox));
		
		assertEquals(50, h22.getX());
		assertEquals(42, h22.getY());
		assertEquals(1, h22.getBoxCount());
		assertEquals(h22, rightBox.getBottomLine());
		assertTrue(h22.containsBox(rightBox));
		
		assertEquals(12, v11.getX());
		assertEquals(20, v11.getY());
		assertEquals(1, v11.getBoxCount());
		assertEquals(v11, leftBox.getLeftLine());
		assertTrue(v11.containsBox(leftBox));
		
		assertEquals(42, v12.getX());
		assertEquals(20, v12.getY());
		assertEquals(2, v12.getBoxCount());
		assertEquals(v12, leftBox.getRightLine());
		assertEquals(v12, rightBox.getLeftLine());
		assertTrue(v12.containsBox(leftBox));
		assertTrue(v12.containsBox(rightBox));
		
		assertEquals(72, v13.getX());
		assertEquals(20, v13.getY());
		assertEquals(1, v13.getBoxCount());
		assertEquals(v13, rightBox.getRightLine());
		assertTrue(v13.containsBox(rightBox));
	}
	
	public void testCreateLinesTwoByTwo() {
		
		GraphicalBoard board = new GraphicalBoard(2, 2, getSettings());
		
		GraphicalLine[] lines = board.getLines();
		assertEquals(12, lines.length);
		
		Box topLeftBox = board.getBoxes()[0];
		Box topRightBox = board.getBoxes()[1];
		Box bottomLeftBox = board.getBoxes()[2];
		Box bottomRightBox = board.getBoxes()[3];
		
		GraphicalLine h11 = lines[0];
		GraphicalLine h12 = lines[1];
		GraphicalLine h21 = lines[2];
		GraphicalLine h22 = lines[3];
		GraphicalLine h31 = lines[4];
		GraphicalLine h32 = lines[5];
		GraphicalLine v11 = lines[6];
		GraphicalLine v12 = lines[7];
		GraphicalLine v13 = lines[8];
		GraphicalLine v21 = lines[9];
		GraphicalLine v22 = lines[10];
		GraphicalLine v23 = lines[11];
		
		assertEquals(20, h11.getX());
		assertEquals(12, h11.getY());
		assertEquals(1, h11.getBoxCount());
		assertEquals(h11, topLeftBox.getTopLine());
		assertTrue(h11.containsBox(topLeftBox));
		
		assertEquals(50, h12.getX());
		assertEquals(12, h12.getY());
		assertEquals(1, h12.getBoxCount());
		assertEquals(h12, topRightBox.getTopLine());
		assertTrue(h12.containsBox(topRightBox));
		
		assertEquals(20, h21.getX());
		assertEquals(42, h21.getY());
		assertEquals(2, h21.getBoxCount());
		assertEquals(h21, topLeftBox.getBottomLine());
		assertEquals(h21, bottomLeftBox.getTopLine());
		assertTrue(h21.containsBox(topLeftBox));
		assertTrue(h21.containsBox(bottomLeftBox));
		
		assertEquals(50, h22.getX());
		assertEquals(42, h22.getY());
		assertEquals(2, h22.getBoxCount());
		assertEquals(h22, topRightBox.getBottomLine());
		assertEquals(h22, bottomRightBox.getTopLine());
		assertTrue(h22.containsBox(topRightBox));
		assertTrue(h22.containsBox(bottomRightBox));
		
		assertEquals(20, h31.getX());
		assertEquals(72, h31.getY());
		assertEquals(1, h31.getBoxCount());
		assertEquals(h31, bottomLeftBox.getBottomLine());
		assertTrue(h31.containsBox(bottomLeftBox));
		
		assertEquals(50, h32.getX());
		assertEquals(72, h32.getY());
		assertEquals(1, h32.getBoxCount());
		assertEquals(h32, bottomRightBox.getBottomLine());
		assertTrue(h32.containsBox(bottomRightBox));
		
		assertEquals(12, v11.getX());
		assertEquals(20, v11.getY());
		assertEquals(1, v11.getBoxCount());
		assertEquals(v11, topLeftBox.getLeftLine());
		assertTrue(v11.containsBox(topLeftBox));
		
		assertEquals(42, v12.getX());
		assertEquals(20, v12.getY());
		assertEquals(2, v12.getBoxCount());
		assertEquals(v12, topLeftBox.getRightLine());
		assertEquals(v12, topRightBox.getLeftLine());
		assertTrue(v12.containsBox(topLeftBox));
		assertTrue(v12.containsBox(topRightBox));
		
		assertEquals(72, v13.getX());
		assertEquals(20, v13.getY());
		assertEquals(1, v13.getBoxCount());
		assertEquals(v13, topRightBox.getRightLine());
		assertTrue(v13.containsBox(topRightBox));
		
		assertEquals(12, v21.getX());
		assertEquals(50, v21.getY());
		assertEquals(1, v21.getBoxCount());
		assertEquals(v21, bottomLeftBox.getLeftLine());
		assertTrue(v21.containsBox(bottomLeftBox));
		
		assertEquals(42, v22.getX());
		assertEquals(50, v22.getY());
		assertEquals(2, v22.getBoxCount());
		assertEquals(v22, bottomLeftBox.getRightLine());
		assertEquals(v22, bottomRightBox.getLeftLine());
		assertTrue(v22.containsBox(bottomLeftBox));
		assertTrue(v22.containsBox(bottomRightBox));
		
		assertEquals(72, v23.getX());
		assertEquals(50, v23.getY());
		assertEquals(1, v23.getBoxCount());
		assertEquals(v23, bottomRightBox.getRightLine());
		assertTrue(v23.containsBox(bottomRightBox));
	}
	
	public void testGetConfigurationOneByOne() {
		
		GraphicalBoard board = new GraphicalBoard(1, 1, getSettings());
		
		GraphicalLine[] lines = board.getLines();
		
		assertEquals(4, lines.length);
		
		lines[0].setSelected(true);
		lines[1].setSelected(true);
		lines[2].setSelected(false);
		lines[3].setSelected(true);
		
		byte[] configuration = board.getConfiguration();
		
		assertEquals((byte)1, configuration[0]);
		assertEquals((byte)1, configuration[1]);
		assertEquals((byte)0, configuration[2]);
		assertEquals((byte)1, configuration[3]);
	}
	
	public void testGetConfigurationTwoByTwo() {
		
		GraphicalBoard board = new GraphicalBoard(2, 2, getSettings());
		
		GraphicalLine[] lines = board.getLines();
		
		assertEquals(12, lines.length);
		
		lines[0].setSelected(true);
		lines[1].setSelected(true);
		lines[2].setSelected(false);
		lines[3].setSelected(true);
		lines[4].setSelected(false);
		lines[5].setSelected(true);
		lines[6].setSelected(false);
		lines[7].setSelected(true);
		lines[8].setSelected(true);
		lines[9].setSelected(true);
		lines[10].setSelected(false);
		lines[11].setSelected(false);
		
		byte[] configuration = board.getConfiguration();
		
		assertEquals((byte)1, configuration[0]);
		assertEquals((byte)1, configuration[1]);
		assertEquals((byte)0, configuration[2]);
		assertEquals((byte)1, configuration[3]);
		assertEquals((byte)0, configuration[4]);
		assertEquals((byte)1, configuration[5]);
		assertEquals((byte)0, configuration[6]);
		assertEquals((byte)1, configuration[7]);
		assertEquals((byte)1, configuration[8]);
		assertEquals((byte)1, configuration[9]);
		assertEquals((byte)0, configuration[10]);
		assertEquals((byte)0, configuration[11]);
	}
	
	private GraphicalBoardSettings getSettings() {
		
		return new GraphicalBoardSettings(10, 10, 10, 30, 2, 20, 5);
	}
}
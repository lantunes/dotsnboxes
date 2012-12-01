package dotsnboxes.test;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;
import dotsnboxes.core.Box;
import dotsnboxes.core.Game;
import dotsnboxes.core.Line;

public class TestGame extends TestCase {
	
	public void testSelectLine() {
		
		Box box = new Box();
		Line line = new Line();
		line.addBox(box);
		box.setTopLine(line);
		
		Game game = new Game();
		assertEquals(game.getFirstPlayer(), game.getActivePlayer());
		assertTrue(game.selectLine(line));
		assertEquals(0, game.getFirstPlayer().getBoxesCompleted().size());
		assertTrue(game.getFirstPlayer().getLines().contains(line));
		assertEquals(game.getSecondPlayer(), game.getActivePlayer());
	}
	
	public void testComplete() {
		
		Game game = new Game();
		
		Set<Box> completed = new HashSet<Box>();
		completed.add(new Box());
		game.getFirstPlayer().addBoxesCompleted(completed);
		completed = new HashSet<Box>();
		completed.add(new Box());
		completed.add(new Box());
		game.getSecondPlayer().addBoxesCompleted(completed);
		game.complete();
		assertEquals(0, game.getFirstPlayerGamesWon());
		assertEquals(1, game.getSecondPlayerGamesWon());
		
		game.reset();
		
		completed = new HashSet<Box>();
		completed.add(new Box());
		completed.add(new Box());
		game.getFirstPlayer().addBoxesCompleted(completed);
		completed = new HashSet<Box>();
		completed.add(new Box());
		game.getSecondPlayer().addBoxesCompleted(completed);
		game.complete();
		assertEquals(1, game.getFirstPlayerGamesWon());
		assertEquals(1, game.getSecondPlayerGamesWon());
		
		game.reset();
		
		completed = new HashSet<Box>();
		completed.add(new Box());
		game.getFirstPlayer().addBoxesCompleted(completed);
		completed = new HashSet<Box>();
		completed.add(new Box());
		game.getSecondPlayer().addBoxesCompleted(completed);
		game.complete();
		assertEquals(1, game.getFirstPlayerGamesWon());
		assertEquals(1, game.getSecondPlayerGamesWon());
	}
	
	public void testReset() {
		
		Game game = new Game();
		Set<Box> completed = new HashSet<Box>();
		completed.add(new Box());
		game.getFirstPlayer().addBoxesCompleted(completed);
		completed = new HashSet<Box>();
		completed.add(new Box());
		game.getSecondPlayer().addBoxesCompleted(completed);
		Set<Line> lines = new HashSet<Line>();
		Line line = new Line();
		lines.add(line);
		game.getFirstPlayer().setLines(lines);
		game.getSecondPlayer().setLines(lines);
		
		game.reset();
		
		assertEquals(0, game.getFirstPlayer().getBoxesCompleted().size());
		assertEquals(0, game.getSecondPlayer().getBoxesCompleted().size());
		assertEquals(0, game.getFirstPlayer().getLines().size());
		assertEquals(0, game.getSecondPlayer().getLines().size());
	}
	
	public void testResetOdd() {
		
		Game game = new Game();
		
		assertEquals(game.getFirstPlayer(), game.getActivePlayer());
		Line line = new Line();
		game.selectLine(line);
		
		assertEquals(game.getSecondPlayer(), game.getActivePlayer());
		line = new Line();
		game.selectLine(line);
		
		assertEquals(game.getFirstPlayer(), game.getActivePlayer());
		line = new Line();
		game.selectLine(line);
		
		game.reset();
		
		assertEquals(game.getFirstPlayer(), game.getActivePlayer());
	}
}
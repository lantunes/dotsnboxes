package dotsnboxes.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DotsNBoxesTestSuite extends TestCase {
	
	public static Test suite() {
		
		TestSuite s = new TestSuite();
		s.addTestSuite(TestGraphicalBoard.class);
		s.addTestSuite(TestBoard.class);
		s.addTestSuite(TestGame.class);
		return s;
	}
}
package pt.isep.cms.bookmarks;

import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;
import pt.isep.cms.bookmarks.client.ExampleGWTTest;

public class ExampleGWTTestSuit extends GWTTestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for the Bookmarks");
		suite.addTestSuite(ExampleGWTTest.class);
		return suite;
	}
}

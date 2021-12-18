package pt.isep.cms.tags;

import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;
import pt.isep.cms.tags.client.ExampleGWTTest;

public class ExampleGWTTestSuit extends GWTTestSuite {
	  public static Test suite() {
		    TestSuite suite = new TestSuite("Test for the Tags");
		    suite.addTestSuite(ExampleGWTTest.class);
		    return suite;
		  }
		} 
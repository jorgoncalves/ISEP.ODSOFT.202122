package pt.isep.cms.leases;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;
import pt.isep.cms.leases.client.ExampleGWTTest;

public class ExampleGWTTestSuit extends GWTTestSuite {
	  public static Test suite() {
		    TestSuite suite = new TestSuite("Test for the Leases Application");
		    suite.addTestSuite(ExampleGWTTest.class); 
		    return suite;
		  }
		} 
package pt.isep.cms.books;

import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;
import pt.isep.cms.leases.client.ExampleGWTTest;

public class ExampleGWTTestSuit extends GWTTestSuite {
    public static Test suite() {
        TestSuite suite = new TestSuite("Test for the Books Application");
        suite.addTestSuite(ExampleGWTTest.class);
        return suite;
    }
}

package business;

import input.CSVReader;
import input.Variable;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.DataProvider;

import stats.StatsChecker;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    final static double[][] vals = {
            {0, 1},
            {1, 1},
            {2, 1},
            {3, 1},
            {0, 1},
            {1, 1},
            {2, 1},
            {3, 1},
            {0, 1},
            {1, 1},
            {2, 1},
            {3, 1},
            {400, 1}
    };

    public AppTest( String testName )
    {
        super( testName );
    }


    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }


    public void testApp()
    {
        assertTrue(StatsChecker.checkChiSquare(vals, 1));
        assertFalse(StatsChecker.checkChiSquare(vals, 0));
    }


}

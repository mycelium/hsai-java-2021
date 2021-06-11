package ru.spbstu.hsai.stats;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import ru.spbstu.hsai.outputter.OutputType;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */

    static Dictionary<Param, Double> createParams() {
        Dictionary<Param, Double> params = new Hashtable<>();
        params.put(Param.LAMBDA, 4.);
        params.put(Param.M, 1.);
        params.put(Param.D, 10.);
        params.put(Param.MIN, -10.);
        params.put(Param.MAX, 100.);

        return params;
    }

    public AppTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( business.AppTest.class );
    }


    public void testApp()
    {
        ArrayList<Double> tn = new ArrayList<>();
        ArrayList<Double> tu = new ArrayList<>();
        ArrayList<Double> tp = new ArrayList<>();

        int[] dsize = new int[]{10, 100, 1000};
        Dictionary<Param, Double> params = createParams();

        for (int i = 0; i < dsize.length; i++)
        {
            int len = dsize[i];

            Norm.rvs(len, params, OutputType.CSV, "test norm" + i + " csv");
            Poisson.rvs(len, params, OutputType.CSV, "test poisson" + i + " csv");
            Uniform.rvs(len, params, OutputType.CSV, "test uniform" + i + " csv");

            Norm.rvs(len, params, OutputType.database, "test norm" + i + " db");
            Poisson.rvs(len, params, OutputType.database, "test poisson" + i + " db");
            Uniform.rvs(len, params, OutputType.database, "test uniform" + i + " db");
        }
    }


}

package ru.spbstu.hsai.stats;
import ru.spbstu.hsai.outputter.*;

import java.util.Dictionary;
import java.util.Hashtable;

public class App 
{
    static Dictionary<Param, Double> createParams() {
        Dictionary<Param, Double> params = new Hashtable<>();
        params.put(Param.LAMBDA, 4.);
        params.put(Param.M, 1.);
        params.put(Param.D, 10.);
        params.put(Param.MIN, -10.);
        params.put(Param.MAX, 100.);

        return params;
    }

    public static void main( String[] args )
    {
        Dictionary<Param, Double> params = createParams();
        Norm.rvs(1000, params, OutputType.CSV, "csvout1.csv");
        Poisson.rvs(100, params, OutputType.CSV, "csvout2.csv");
        //Uniform.rvs(10, params, OutputType.database, "dbout.db");
    }
}

package hw1;

import hw1.distribution.NormalDistribution;
import hw1.distribution.PoissonDistribution;
import hw1.distribution.UniformDistribution;

import java.util.ArrayList;
import java.util.HashMap;

public class DataTable {
    Integer size;
    HashMap<String, ArrayList<Double>> dataTable = new HashMap<String, ArrayList<Double>>();

    public DataTable (Integer size){
        this.size = size;
    }

    public void generate (String type, double  a, double b) {
        if (type == "normal") {
            NormalDistribution normal = new NormalDistribution(a, b);
            dataTable.put(
                    type + " " + Double.toString(a) + " " + Double.toString(b),
                    normal.generate(size));
        } else if (type == "uniform") {
            UniformDistribution uniform = new UniformDistribution(a, b);
            dataTable.put(
                    type + " " + Double.toString(a) + " " + Double.toString(b),
                    uniform.generate(size));
        } else {
            System.err.println("Type error");
            return;
        }
    }

    public void generate (String type, double  a) {
        if (type == "poisson") {
            PoissonDistribution poisson = new PoissonDistribution(a);
            dataTable.put(
                    type + " " + Double.toString(a),
                    poisson.generate(size));
        } else {
            System.err.println("Type error");
            return;
        }
    }

}

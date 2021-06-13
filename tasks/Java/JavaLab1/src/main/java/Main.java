import generators.Generator;
import generators.NormalDistributionGenerator;
import generators.PoissonDistributionGenerator;
import generators.UniformDistributionGenerator;
import output.CSVOutputter;
import output.DBOutputter;

import java.io.IOException;
import java.util.ArrayList;


public class Main {
    public static void main(String... args) {
        String name = args[0];
        String typeOfDistribution = args[1];
        String number = args[2];
        String typeOfOutput = args[3];
        String firstAttribute = args[4];
        Generator generator;
        if (!typeOfDistribution.equals("poisson")) {
            String secondAttribute = args[5];
            if (typeOfDistribution.equals("uniform")) {
                generator = new UniformDistributionGenerator(Double.parseDouble(firstAttribute), Double.parseDouble(secondAttribute));
            } else {
                generator = new NormalDistributionGenerator(Double.parseDouble(firstAttribute), Double.parseDouble(secondAttribute));
            }
        } else {
            generator = new PoissonDistributionGenerator(Double.parseDouble(firstAttribute));
        }
        if (typeOfOutput.equals("CSV")) {
            CSVOutputter.output((ArrayList<Double>) generator, name);
        } else {
            DBOutputter.output((ArrayList<Double>) generator, name);
        }
    }
}
package com.spbstu;

import com.spbstu.generators.Generator;
import com.spbstu.generators.NormalDistributionGenerator;
import com.spbstu.generators.PoissonDistributionGenerator;
import com.spbstu.generators.UniformDistributionGenerator;
import com.spbstu.output.CSVOutput;
import com.spbstu.output.DBOutput;
import com.spbstu.output.Saving;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Hello world!
 */
public class App {
    public static void main(String... args) {
        String name = args[0];
        String typeOfDistribution = args[1];
        String number = args[2];
        String typeOfOutput = args[3];
        String firstAttribute = args[4];
        Generator generator;
        Saving output;
        if(!typeOfDistribution.equals("poisson")){
            String secondAttribute=args[5];
            if(typeOfDistribution.equals("uniform")){
                generator=new UniformDistributionGenerator(Double.parseDouble(firstAttribute),Double.parseDouble(secondAttribute));
            } else{
                generator=new NormalDistributionGenerator(Double.parseDouble(firstAttribute),Double.parseDouble(secondAttribute));
            }
        } else{
            generator=new PoissonDistributionGenerator(Double.parseDouble(firstAttribute));
        }
        if(typeOfOutput.equals("CSV")){
            output=new CSVOutput(name);
        } else{
            output=new DBOutput(name);
        }
        ArrayList<Double> generated = generator.generate(Integer.parseInt(number));
        try {
            output.save(generated);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

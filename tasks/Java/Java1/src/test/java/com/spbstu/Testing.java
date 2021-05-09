package com.spbstu;

import com.spbstu.generators.NormalDistributionGenerator;
import com.spbstu.generators.PoissonDistributionGenerator;
import com.spbstu.generators.UniformDistributionGenerator;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class Testing {
    @Test
    public void NormalDistributionGeneratorTest() {
        Double[][] norm = {{0.0, 1.0}, {3.0, 2.0}, {4.0, 10.0}, {100.0, 25.0}, {-50.0, 4.0}};
        for (Double[] param : norm) {
            NormalDistributionGenerator normalDistributionGenerator = new NormalDistributionGenerator(param[0], param[1]);
            ArrayList<Double> distribution = normalDistributionGenerator.generate(100);
            for (Double d : distribution) {
                assertTrue(d < param[1] * 6 + param[0]);
                assertTrue(d >= param[1] * (-6) + param[0]);
            }
        }
    }

    @Test
    public void PoissonDistributionGeneratorTest() {
        Double[] norm = {1.0, 4.5, 12.0};
        for (Double param : norm) {
            PoissonDistributionGenerator poissonDistributionGenerator = new PoissonDistributionGenerator(param);
            ArrayList<Double> distribution = poissonDistributionGenerator.generate(100);
            for (Double d : distribution) {
                double x0 = 0;
                double x1 = 0;
                double p = Math.exp(-param);
                double r0 = -p;
                double r1 = 0.999999 - p;
                while (r0 > 0) {
                    x0++;
                    p *= param / x0;
                    r0 -= p;
                }
                while (r1 > 0) {
                    x1++;
                    p *= param / x1;
                    r1 -= p;
                }
                assertTrue("d"+d+"r"+x1,d <= x1);
                assertTrue("d"+d+"r"+x0,d >= x0);
            }
        }
    }

    @Test
    public void UniformDistributionGeneratorTest() {
        Double[][] norm = {{0.0, 1.0}, {15.0, 24.0}, {-5.0, 14.0}};
        for (Double[] param : norm) {
            UniformDistributionGenerator uniformDistributionGenerator = new UniformDistributionGenerator(param[0], param[1]);
            ArrayList<Double> distribution = uniformDistributionGenerator.generate(100);
            for (Double d : distribution) {
                assertTrue(d < param[0] + (param[1] - param[0]));
                assertTrue(d >= param[0]);
            }
        }
    }
}

package ru.spbstu.telematics;

import ru.spbstu.telematics.distribution.NormalDistribution;
import ru.spbstu.telematics.distribution.PoissonDistribution;
import ru.spbstu.telematics.distribution.UniformDistribution;

import java.util.ArrayList;

public class RandomData {
    double min = 0, max = 100, lambda = 4, mu = 0, sigmaSquared = 2;
    long size;
    String name;
    String outputFormat;
    String distribution;
    ArrayList<Double> data;
    Output output;

    RandomData(String distribution, String name, long size, String outputFormat) {
        this.name = name;
        if (size < 0) {
            throw new RuntimeException("Size can not be negative.");
        } else this.size = size;
        if (outputFormat.equals("csv") || outputFormat.equals("db")) {
            this.outputFormat = outputFormat;
        } else throw new RuntimeException("Illegal output format.");
        this.distribution = distribution;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public void setMu(double mu) {
        this.mu = mu;
    }

    public void setSigmaSquared(int sigmaSquared) {
        this.sigmaSquared = sigmaSquared;
    }

    public String generate() {
        switch (distribution) {
            case "poisson" -> {
                PoissonDistribution poisson = new PoissonDistribution(size, lambda);
                data = poisson.generate();
            }
            case "uniform" -> {
                UniformDistribution uniform = new UniformDistribution(size, min, max);
                data = uniform.generate();
            }
            case "normal" -> {
                NormalDistribution normal = new NormalDistribution(size, mu, sigmaSquared);
                data = normal.generate();
            }
        }
        output = new Output(name, data);
        if (outputFormat.equals("csv")) {
            return output.OutputCSV();
        } else return output.OutputDBF();
    }
}

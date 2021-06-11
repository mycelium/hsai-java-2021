package ru.spbstu.hsai.stats;

import ru.spbstu.hsai.outputter.OutputType;

import java.util.ArrayList;
import java.util.Dictionary;

public class Poisson extends Distribution {
    private static double getRand(double lambda) {
        // по алгоритму из справочника Вадзинского

        double p = Math.exp(-lambda), x = 0;
        double r = Math.random() - p;

        while (r >= 0) {
            x += 1;
            p *= lambda / x;
            r -= p;
        }

        return x;
    }

    public static void rvs(int size, Dictionary<Param, Double> params, OutputType outputType, String fname) {
        double lambda = params.get(Param.LAMBDA);

        ArrayList<Double> res = new ArrayList<>(size);

        for (int i = 0; i < size; i++)
            res.add(getRand(lambda));

        output(outputType, res, fname);
    }
}

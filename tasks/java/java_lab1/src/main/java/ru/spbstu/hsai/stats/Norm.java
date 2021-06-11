package ru.spbstu.hsai.stats;

import ru.spbstu.hsai.outputter.OutputType;

import java.util.ArrayList;
import java.util.Dictionary;

public class Norm extends Distribution {

    private static double getRand(double m, double d) {
        // по формуле из справочника Вадзинского
        double sum = 0;
        for (int i = 1; i < 12; i++)
            sum += Math.random();

        return m + (sum - 6) * d;
    }

    public static void rvs(int size, Dictionary<Param, Double> params, OutputType outputType, String fname) {
        double m = params.get(Param.M), d = params.get(Param.D);
        if (d < 0)
            return;

        ArrayList<Double> res = new ArrayList<>(size);

        for (int i = 0; i < size; i++)
            res.add(getRand(m, d));

        output(outputType, res, fname);
    }
}

package ru.spbstu.hsai.stats;

import ru.spbstu.hsai.outputter.OutputType;

import java.util.ArrayList;
import java.util.Dictionary;

public class Uniform extends Distribution {

    public static void rvs(int size, Dictionary<Param, Double> params, OutputType outputType, String fname) {
        double min = params.get(Param.MIN), max = params.get(Param.MAX);
        if (max < min)
            return;

        ArrayList<Double> res = new ArrayList<>(size);

        for (int i = 0; i < size; i++)
            res.add(Math.random() * (max - min) + min);

        output(outputType, res, fname);
    }
}

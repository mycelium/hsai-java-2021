package com.spbstu.normal;

import com.spbstu.data.Calculator;
import com.spbstu.data.TableData;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest;

import java.util.ArrayList;

public class NormalCalculator implements Calculator {

    public ArrayList<Double> calculate(TableData tableData) {
        ArrayList<Double> toCalculate = tableData.getColumn(0);
        double[] values = toCalculate.stream().mapToDouble(Double::doubleValue).toArray();
//        Mean mean = new Mean();
//        Double average = mean.evaluate(values);
//        Double moda = 0.0;
//        Median median1 = new Median();
//        Double median = median1.evaluate(values);
//        Kurtosis kurtosis = new Kurtosis();
//        Double excess = kurtosis.evaluate(values);
//        Skewness skewness = new Skewness();
//        Double asymmetry = skewness.evaluate(values);
        KolmogorovSmirnovTest kolmogorovSmirnovTest = new KolmogorovSmirnovTest();
        double v = kolmogorovSmirnovTest.kolmogorovSmirnovTest(new NormalDistribution(), values);
        ArrayList<Double> vo=new ArrayList<>();
        vo.add(v);
        return vo;
    }
}

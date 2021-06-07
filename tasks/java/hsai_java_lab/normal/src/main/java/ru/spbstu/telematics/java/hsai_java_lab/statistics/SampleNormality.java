package ru.spbstu.telematics.java.hsai_java_lab.statistics;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.stat.Frequency;

public class SampleNormality {
    private static final int minSampleSize = 50;
    
    public static boolean isSampleNormal(ArrayList<Double> sample, double alpha) {
        if (sample == null) {
            throw new NullPointerException("Sample is null");
        }

        if (sample.isEmpty()) {
            throw new IllegalArgumentException("Sample is empty");
        }

        if (sample.size() < minSampleSize) {
            throw new IllegalArgumentException("Sample is too small to investigate");
        }

        if ((alpha < 0.) || (alpha > 1.)) {
            throw new OutOfRangeException(alpha, 0., 1.);
        }

        Collections.sort(sample);

        /* Calculate parameters */
        int n = sample.size();
        double mu = SampleStatistics.mean(sample);
        double sigma = 0.;
        for (int i = 0; i < n; i++) {
            sigma += Math.pow((sample.get(i) - mu), 2);
        }
        sigma = Math.sqrt(sigma / (double) n);
       
        int k = (int) Math.round(1.72 * Math.pow(n, (1. / 3.)));

        /* 
         * Split sample domain into the k intervals
         * with first interval (-infty, a_0] and last interval [a_k-1, infty)
        */
        ArrayList<Double> intervalLimits = new ArrayList<Double>();
        double sampleInterval = sample.get(sample.size() - 1) - sample.get(0); // max - min
        double intervaIncrement = sampleInterval / (double) k;
        double intervaLowLimit = sample.get(0) + intervaIncrement;

        for (int i = 0; i < k - 1; i++) {
            intervalLimits.add(intervaLowLimit);
            intervaLowLimit += intervaIncrement;
        }

        /* Calculate theoretical frequencies */
        NormalDistribution normal = new NormalDistribution(mu, sigma);
        ArrayList<Double> pTheoretcal = new ArrayList<Double>();
        pTheoretcal.add(normal.cumulativeProbability(intervalLimits.get(0))); // (-inty, a_0]

        for (int i = 1; i < k - 1; i++) {
            double p_i = normal.cumulativeProbability(intervalLimits.get(i)) - 
                         normal.cumulativeProbability(intervalLimits.get(i - 1));
            pTheoretcal.add(p_i);
        }
        
        pTheoretcal.add(1. - normal.cumulativeProbability(intervalLimits.get(k - 2))); // (a_k-1, infty)
 
        /* Calculate sample frequencies */
        Frequency freq = new Frequency();
        for (Double d : sample) {
            freq.addValue(d);
        }

        ArrayList<Long> pEmpirical = new ArrayList<Long>();
        pEmpirical.add(freq.getCumFreq(intervalLimits.get(0))); // (-inty, a_0]

        for (int i = 1; i < k - 1; i++) {
            long p_i = freq.getCumFreq(intervalLimits.get(i)) -
            freq.getCumFreq(intervalLimits.get(i - 1));
            pEmpirical.add(p_i);
        }

        pEmpirical.add(n - freq.getCumFreq(intervalLimits.get(k - 2))); // (a_k-1, infty)

        /* Calculate ChiSquared coefficient */
        ChiSquaredDistribution chi2 = new ChiSquaredDistribution(k - 1);
        double chi2_q_theor = chi2.inverseCumulativeProbability(1. - alpha);
        double chi2_q_sample = 0.;

        for (int i = 0; i < k; i++) {
            chi2_q_sample += Math.pow(pEmpirical.get(i) - (double) n * pTheoretcal.get(i), 2.) /
                             (double) n * pTheoretcal.get(i);
        }
        
        return chi2_q_sample < chi2_q_theor;
    }
}

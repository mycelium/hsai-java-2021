package ru.spbstu.telematics.java.hsai_java_lab.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.stat.Frequency;

import ru.spbstu.telematics.java.hsai_java_lab.statistics.data.SampleStatisticsData;
import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValueSample;

public class SampleNormality {
    private double alpha;
    private static final int minSampleSize = 50;

    public SampleNormality(double alpha) {
        if ((alpha < 0.) || (alpha > 1.)) {
            throw new OutOfRangeException(alpha, 0., 1.);
        }

        this.alpha = alpha;
    }

    public void setAlpha(double alpha) {
        if ((alpha < 0.) || (alpha > 1.)) {
            throw new OutOfRangeException(alpha, 0., 1.);
        }

        this.alpha = alpha;
    }

    /**
     * Creates an array of samples statistic data
     * 
     * @param samples Array of random value samples
     * @return Array of samples statistic data
     * @throws NullPointerException if {@code samples} is {@code null}
     */
    public ArrayList<SampleStatisticsData> getStatistics(ArrayList<RandomValueSample> samples) {
        if (samples == null) {
            throw new NullPointerException("Samples array is null");
        }

        ArrayList<SampleStatisticsData> samplesData = new ArrayList<SampleStatisticsData>();

        for (RandomValueSample s : samples) {
            SampleStatisticsData newSampleData = new SampleStatisticsData(s.getName());

            try {
                Boolean normality = isSampleNormal(s.getSample());
                newSampleData.addStatistic("Normality", normality);
            }
            catch (IllegalArgumentException e) {
                newSampleData.addStatistic("Normality", "Undefined");
            }
            
            samplesData.add(newSampleData);
        }

        return samplesData;
    }
    
    /**
     * Implements the Chi Squared criterion to find out
     * if the given sample has normal distribution with respect to
     * the alpha Student coefficient that was set previously;
     * 
     * @param sample Array of floating point values
     * @return {@code true} if sample is distributed normally;
     *         {@code false} otherwice
     */
    private boolean isSampleNormal(ArrayList<Double> sample) {
        if (sample == null) {
            throw new NullPointerException("Sample is null");
        }

        if (sample.isEmpty()) {
            throw new IllegalArgumentException("Sample is empty");
        }

        if (sample.size() < minSampleSize) {
            throw new IllegalArgumentException("Sample is too small to investigate");
        }

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
        double min = Collections.min(sample);
        double max = Collections.max(sample);
        double sampleInterval = max - min; // max - min
        double intervaIncrement = sampleInterval / (double) k;
        double intervaHighLimit = min + intervaIncrement;

        for (int i = 0; i < k - 1; i++) {
            intervalLimits.add(intervaHighLimit);
            intervaHighLimit += intervaIncrement;
        }

        /* Calculate theoretical frequencies */
        NormalDistribution normal = new NormalDistribution(mu, sigma);
        ArrayList<Double> pTheoretical = new ArrayList<Double>();
        double pTheorPrev = 0;
        double pTheorNext;

        for (int i = 0; i < k - 1 ; i++) {
            pTheorNext = normal.cumulativeProbability(intervalLimits.get(i));
            pTheoretical.add(pTheorNext - pTheorPrev);
            pTheorPrev = pTheorNext;
        }
        
        pTheoretical.add(1. - normal.cumulativeProbability(intervalLimits.get(k - 2))); // (a_k-1, infty)
 
        /* Calculate sample frequencies */
        Frequency freq = new Frequency();
        for (Double d : sample) {
            freq.addValue(d);
        }

        ArrayList<Long> pEmpirical = new ArrayList<Long>();

        long pEmpirPrev = 0;
        long pEmpirNext;

        for (int i = 0; i < k - 1; i++) {
            pEmpirNext = freq.getCumFreq(intervalLimits.get(i));
            pEmpirical.add(pEmpirNext - pEmpirPrev);
            pEmpirPrev = pEmpirNext;
        }

        pEmpirical.add(n - freq.getCumFreq(intervalLimits.get(k - 2))); // (a_k-1, infty)

        /* Calculate ChiSquared coefficient */
        ChiSquaredDistribution chi2 = new ChiSquaredDistribution(k - 1);
        double chi2_q_theor = chi2.inverseCumulativeProbability(1. - alpha);
        double chi2_q_sample = 0.;

        for (int i = 0; i < k; i++) {
            chi2_q_sample += Math.pow(pEmpirical.get(i) - (double) n * pTheoretical.get(i), 2.) /
                             (double) n * pTheoretical.get(i);
        }
        
        return chi2_q_sample < chi2_q_theor;
    }
}

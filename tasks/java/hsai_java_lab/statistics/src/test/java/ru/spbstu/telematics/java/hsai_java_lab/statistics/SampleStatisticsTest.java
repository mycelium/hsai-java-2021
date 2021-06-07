package ru.spbstu.telematics.java.hsai_java_lab.statistics;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SampleStatisticsTest {
    ArrayList<Double> sample1;
    ArrayList<Double> sample2;
    ArrayList<Double> emptySample;

    @Before
    public void setUp() {
        sample1 = new ArrayList<Double>();
        sample2 = new ArrayList<Double>();
        emptySample = new ArrayList<Double>();

        sample1.add(12.5);
        sample1.add(-23.0);
        sample1.add(0.5);
        sample1.add(8.2);
        sample1.add(18.7);
        sample1.add(3.5);

        sample2.add(18.7);
        sample2.add(12.5);
        sample2.add(-23.0);
        sample2.add(0.5);
        sample2.add(120.8);
        sample2.add(8.2);
        sample2.add(3.5);
    }

    @Test
    public void meanTest() {
        double mean1 = SampleStatistics.mean(sample1);
        double mean2 = SampleStatistics.mean(sample2);

        assertEquals(3.4, mean1, Double.MIN_NORMAL);
        assertEquals(20.17142857142857, mean2, Double.MIN_NORMAL);
    }

    @Test
    public void medianTest() {
        double median1 = SampleStatistics.median(sample1);
        double median2 = SampleStatistics.median(sample2);

        assertEquals(5.85, median1, Double.MIN_NORMAL);
        assertEquals(8.2, median2, Double.MIN_NORMAL);
    }

    @Test
    public void minTest() {
        double min1 = SampleStatistics.min(sample1);
        double min2 = SampleStatistics.min(sample2);

        assertEquals(-23.0, min1, Double.MIN_NORMAL);
        assertEquals(-23.0, min2, Double.MIN_NORMAL);
    }

    @Test
    public void maxTest() {
        double max1 = SampleStatistics.max(sample1);
        double max2 = SampleStatistics.max(sample2);

        assertEquals(18.7, max1, Double.MIN_NORMAL);
        assertEquals(120.8, max2, Double.MIN_NORMAL);
    }

    @Test
    public void emptySampleTest() {
        int exceptionCount = 0;

        try {
            SampleStatistics.mean(emptySample);
        }
        catch (IllegalArgumentException e) {
            exceptionCount++;
        }

        try {
            SampleStatistics.median(emptySample);
        }
        catch (IllegalArgumentException e) {
            exceptionCount++;
        }

        try {
            SampleStatistics.min(emptySample);
        }
        catch (IllegalArgumentException e) {
            exceptionCount++;
        }

        try {
            SampleStatistics.max(emptySample);
        }
        catch (IllegalArgumentException e) {
            exceptionCount++;
        }

        assertEquals(4, exceptionCount);
    }

    @Test
    public void nullSampleTest() {
        int exceptionCount = 0;

        try {
            SampleStatistics.mean(null);
        }
        catch (NullPointerException e) {
            exceptionCount++;
        }

        try {
            SampleStatistics.median(null);
        }
        catch (NullPointerException e) {
            exceptionCount++;
        }

        try {
            SampleStatistics.min(null);
        }
        catch (NullPointerException e) {
            exceptionCount++;
        }

        try {
            SampleStatistics.max(null);
        }
        catch (NullPointerException e) {
            exceptionCount++;
        }

        assertEquals(4, exceptionCount);
    }
    
}

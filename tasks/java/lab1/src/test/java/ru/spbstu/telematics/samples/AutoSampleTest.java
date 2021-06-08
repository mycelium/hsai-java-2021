package ru.spbstu.telematics.samples;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutoSampleTest {

    @DataProvider(name = "normal")
    public Object[][] normalSamples() {
        return new Object[][] {
                new Object[] {AutoSamplesFactory.getNormal("Normal1", 5., 2.)},
                new Object[] {AutoSamplesFactory.getNormal("Normal2", 1., 0.)},
                new Object[] {AutoSamplesFactory.getNormal("Normal3", 100., -5.)}
        };
    }

    @DataProvider(name = "poisson")
    public Object[][] poissonSamples() {
        return new Object[][] {
                new Object[] {AutoSamplesFactory.getPoisson("Poisson1", 10)},
                new Object[] {AutoSamplesFactory.getPoisson("Poisson2", 1)},
        };
    }

    @DataProvider(name = "uniform")
    public Object[][] uniformSamples() {
        return new Object[][] {
                new Object[] {AutoSamplesFactory.getUniform("Uniform1", 5., 10.)},
                new Object[] {AutoSamplesFactory.getUniform("Uniform2", -14., 5.)},
                new Object[] {AutoSamplesFactory.getUniform("Uniform3", -14., -10.)},
                new Object[] {AutoSamplesFactory.getUniform("Uniform4", 5., 5.)}
        };
    }

    @DataProvider(name = "uniformWithBounds")
    public Object[][] uniformSamplesWithBounds() {
        return new Object[][] {
                new Object[] {AutoSamplesFactory.getUniform("Uniform1", 5., 10.), 5., 10.},
                new Object[] {AutoSamplesFactory.getUniform("Uniform2", -14., 5.), -14., 5.},
                new Object[] {AutoSamplesFactory.getUniform("Uniform3", -14., -10.), -14., -10.},
                new Object[] {AutoSamplesFactory.getUniform("Uniform4", 5., 5.), 5., 5.}
        };
    }

    @DataProvider(name = "all")
    public Object[][] allSamples() {
        List<Object[]> result = new ArrayList<>();
        result.addAll(Arrays.asList(normalSamples()));
        result.addAll(Arrays.asList(uniformSamples()));
        result.addAll(Arrays.asList(poissonSamples()));
        return result.toArray(new Object[result.size()][]);
    }

    @Test(dataProvider = "all")
    public void notNegativeGenerationTest(AutoSample<?> sample) {
        int size = 0;
        int first = 1;
        int second = 0;
        int third = 5;
        Assert.assertEquals(sample.size(), 0);
        generateAndCheck(sample, first, size += first);
        generateAndCheck(sample, second, size += second);
        generateAndCheck(sample, third, size += third);
    }

    @Test(dataProvider = "all",
            expectedExceptions = { IllegalArgumentException.class })
    public void negativeGenerationTest(AutoSample<?> sample) {
        Assert.assertEquals(sample.size(), 0);
        sample.generate(-1);
    }

    @Test(dataProvider = "uniformWithBounds")
    public void boundUniformTest(AutoSample<Double> sample, double leftBound, double rightBound) {
        sample.generate(1000);
        int size = 0;
        for (Double value : sample) {
            size++;
            Assert.assertTrue(leftBound <= value && value <= rightBound);
        }
        Assert.assertEquals(sample.size(), size);
    }

    private void generateAndCheck(AutoSample<?> sample, int generate, int size) {
        sample.generate(generate);
        Assert.assertEquals(sample.size(), size);
    }

}

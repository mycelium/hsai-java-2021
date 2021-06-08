package ru.spbstu.telematics.distributions;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ru.spbstu.telematics.util.Cartesian.cartesianProduct;

public class NormalTest {

    private final Object[] varianceCorrect =
            new Object[] {10., 0.1};

    private final Object[] meanCorrect =
            new Object[] {10., 0, -10.};

    private final Object[] varianceNotPositive =
            new Object[] {0., -10, -0.1};

    @DataProvider(name = "correct")
    public Object[][] correctInput() {
        return cartesianProduct(varianceCorrect, meanCorrect);
    }

    @DataProvider(name = "notPositiveVariance")
    public Object[][] notPositiveVariance() {
        return cartesianProduct(varianceNotPositive, meanCorrect);
    }

    @Test(dataProvider = "correct")
    public void CorrectInputTest(double variance, double mean) {
        new Normal(variance, mean);
    }

    @Test(dataProvider = "notPositiveVariance",
            expectedExceptions = { IllegalArgumentException.class },
            expectedExceptionsMessageRegExp = "^Variance must be > 0, but variance = -?\\d+.\\d+$")
    public void NotPositiveVarianceTest(double variance, double mean) {
        new Normal(variance, mean);
    }

}

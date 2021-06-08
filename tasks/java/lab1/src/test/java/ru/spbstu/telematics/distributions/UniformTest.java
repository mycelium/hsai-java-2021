package ru.spbstu.telematics.distributions;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static ru.spbstu.telematics.util.Cartesian.cartesianProduct;

public class UniformTest {

    private final Object[] leftBound =
            new Object[] {10., 0.1, -30., 0};

    private final Object[] rightBound =
            new Object[] {10.1, 15, 30};

    private final Object[] varianceNotPositive =
            new Object[] {0., -10, -0.1};

    @DataProvider(name = "correct")
    public Object[][] correctInput() {
        return cartesianProduct(leftBound, rightBound);
    }

    @DataProvider(name = "leftIsBigger")
    public Object[][] leftIsBiggerInput() {
        return cartesianProduct(rightBound, leftBound);
    }

    @Test(dataProvider = "correct")
    public void CorrectInputTest(double leftBound, double rightBound) {
        new Uniform(leftBound, rightBound);
    }

    @Test(dataProvider = "leftIsBigger",
            expectedExceptions = { IllegalArgumentException.class },
            expectedExceptionsMessageRegExp = "Left bound is bigger than right bound: -?\\d+.\\d+ > -?\\d+.\\d+$")
    public void leftIsBiggerInputTest(double leftBound, double rightBound) {
        new Uniform(leftBound, rightBound);
    }

    @Test
    public void LeftEqualsRightTest() {
        var value = (Double) Arrays.stream(leftBound).findAny().get();
        new Uniform(value, value);
    }

}

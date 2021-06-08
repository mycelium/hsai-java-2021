package ru.spbstu.telematics.distributions;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PoissonTest {

    private final Object[] varianceCorrect =
            new Object[] {1, 100};

    private final Object[] varianceIncorrect =
            new Object[] {0, -1, -15};


    @DataProvider(name = "positive")
    public Object[] correctInput() {
        return varianceCorrect;
    }

    @DataProvider(name = "notPositive")
    public Object[] leftIsBiggerInput() {
        return varianceIncorrect;
    }

    @Test(dataProvider = "positive")
    public void positiveInputTest(int variance) {
        new Poisson(variance);
    }

    @Test(dataProvider = "notPositive",
            expectedExceptions = { IllegalArgumentException.class },
            expectedExceptionsMessageRegExp = "Mean must be > 0, but mean = -?\\d+$")
    public void notPositiveInputTest(int variance) {
        new Poisson(variance);
    }


}

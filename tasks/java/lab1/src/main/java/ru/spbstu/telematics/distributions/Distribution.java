package ru.spbstu.telematics.distributions;

import java.util.Random;

public abstract class Distribution<T> implements Distributed<T> {

    protected static final Random random = new Random();

    protected void checkNotInfinityOrNan(Double d) {
        if (d.isNaN() || d.isInfinite()) {
            throw new IllegalArgumentException("Double value is " + d.toString());
        }
    }

}

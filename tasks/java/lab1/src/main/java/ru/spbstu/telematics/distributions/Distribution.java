package ru.spbstu.telematics.distributions;

import java.util.Random;

public abstract class Distribution<T> implements Distributed<T> {

    protected static final Random random = new Random();

}

package ru.spbstu.telematics.distributions;

import java.util.Random;

public abstract class Distribution<T> implements Distributed<T> {

    protected String name;
    protected static final Random random = new Random();

    protected Distribution(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}

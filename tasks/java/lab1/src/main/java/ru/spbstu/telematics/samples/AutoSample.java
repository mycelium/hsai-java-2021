package ru.spbstu.telematics.samples;

import ru.spbstu.telematics.distributions.Distributed;

import java.util.ArrayList;

public class AutoSample<T> extends ArrayList<T> {

    private String name;
    private Distributed<T> distribution;

    public AutoSample(int initialCapacity, String name, Distributed<T> distribution) {
        super(initialCapacity);
        generate(initialCapacity);
        this.name = name;
        this.distribution = distribution;
    }

    public AutoSample(String name, Distributed<T> distribution) {
        this.name = name;
        this.distribution = distribution;
    }

    public void generate(int size) {
        this.addAll(distribution.getRandoms(size));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

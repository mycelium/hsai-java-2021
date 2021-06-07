package ru.spbstu.telematics.distributions;

import java.util.ArrayList;
import java.util.List;

public interface Distributed<T> {

    T getRandom();

    default List<T> getRandoms(int size) {
        List<T> sample = new ArrayList<>(size);
        for(int i = 0; i < size; i++) {
            sample.add(getRandom());
        }
        return sample;
    }

}

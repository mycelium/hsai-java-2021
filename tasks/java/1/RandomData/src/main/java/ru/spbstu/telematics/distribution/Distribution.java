package ru.spbstu.telematics.distribution;

import java.util.ArrayList;

public interface Distribution {
    Double generateOne();
    ArrayList<Double> generate();
}

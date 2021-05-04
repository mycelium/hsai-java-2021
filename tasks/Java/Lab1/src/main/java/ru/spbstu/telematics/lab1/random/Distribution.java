package ru.spbstu.telematics.lab1.random;

import java.util.ArrayList;

/**
 * Generates random numbers. Distribution can be discrete or continuous.
 */
public interface Distribution {
    /**
     * @return random float value.
     */
    float generateFloat();

    /**
     * @return random int value. For continuous distribution returns 0.
     */
    int generateInt();

    /**
     * Generates random selection of given size.
     * @param size size of selection.
     * @return selection of float values.
     */
    ArrayList<Float> selectionFloat(int size);

    /**
     * Generates random selection of given size.
     * @param size size of selection.
     * @return selection of int values. For continuous distribution returns null.
     */
    ArrayList<Integer> selectionInt(int size);

    /**
     * @return true, if distribution is discrete.
     */
    boolean isDiscrete();
}

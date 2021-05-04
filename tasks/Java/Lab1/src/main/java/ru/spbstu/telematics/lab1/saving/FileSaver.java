package ru.spbstu.telematics.lab1.saving;

import ru.spbstu.telematics.lab1.RandomData;

/**
 * File savers for random data table.
 */
public interface FileSaver {
    /**
     * Saves 'size' rows of random data table.
     * @param data random data generator.
     * @param size number of rows in generated table.
     * @return absolute filepath to saved table.
     */
    String save(RandomData data, int size);
    SavingType type();
}

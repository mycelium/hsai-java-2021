package ru.spbstu.telematics.java.hsai_java_lab.storage;

import java.util.ArrayList;

import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValue;

public interface Storage {
    public enum StorageType {
        DATABASE,
        CSV
    }

    /**
     * Stores the table of the random values. where each column
     * represents one random value with the given distribution
     * 
     * @param table Instance of the random values table
     * @param name Name of the storage file
     * @param rowNumber Desired number of rows in the table
     * @return Path to the file, where the table is stored
     * @throws NullPointerException if table is null
     */
    public String saveTable(ArrayList<RandomValue> table, String name, int rowNumber) throws NullPointerException, Exception;
}

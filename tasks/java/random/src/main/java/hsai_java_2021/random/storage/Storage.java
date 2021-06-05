package hsai_java_2021.random.storage;

import java.util.ArrayList;

import hsai_java_2021.random.RandomValue;

public interface Storage {
    public enum StorageType {
        DATABASE,
        CSV
    }

    /**
     * Stores the table of the random values. where each column
     * represents one random value with the given distribution
     * 
     * @param table Array of the random values
     * @param rowNumber Desired number of rows in the table
     * @return Path to the file, where the table is stored
     */
    public String saveTable(ArrayList<RandomValue> table, int rowNumber);
}

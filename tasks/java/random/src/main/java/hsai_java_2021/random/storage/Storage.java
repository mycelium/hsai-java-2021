package hsai_java_2021.random.storage;

import java.util.ArrayList;

import hsai_java_2021.random.RandomValue;

public interface Storage {
    public enum StorageType {
        DATABASE,
        CSV
    }

    public String saveTable(ArrayList<RandomValue> table, int rowNumber);
}

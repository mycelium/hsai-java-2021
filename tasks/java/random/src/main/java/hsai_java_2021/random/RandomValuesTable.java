package hsai_java_2021.random;

import java.util.ArrayList;

import hsai_java_2021.random.storage.CsvStorage;
import hsai_java_2021.random.storage.DatabaseStorage;
import hsai_java_2021.random.storage.Storage;
import hsai_java_2021.random.storage.Storage.StorageType;

public class RandomValuesTable {
    private ArrayList<RandomValue> table;
    private int rowNumber;
    private Storage storage;

    public RandomValuesTable(ArrayList<RandomValue> values, int sampleSize, StorageType storageType) {
        this.table = new ArrayList<RandomValue>(values);
        this.rowNumber = sampleSize > 0 ? sampleSize : 0;

        for (RandomValue v : table) {
            v.generateSample(rowNumber);
        }

        switch (storageType) {
            case CSV:
                this.storage = new CsvStorage();
                break;
            case DATABASE:
                this.storage = new DatabaseStorage();
                break;
            default:
                break;
        }
    }

    public String save() {
        String filePath = storage.saveTable(table, rowNumber);
        if (filePath == null) {
            System.err.println("Failed to save table to CSV file");
        }

        return filePath;
    }
}

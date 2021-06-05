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

    /**
     * Module input interface. Represents the table of the
     * random values with different distributions, where each row
     * represents one random value
     * 
     * @param values Array of random values
     * @param sampleSize Number of rows in the table
     * @param storageType Type of table storage: CSV or Database
     */
    public RandomValuesTable(ArrayList<RandomValue> values, int sampleSize, StorageType storageType) {
        this.table = new ArrayList<RandomValue>(values);
        this.rowNumber = sampleSize < 0 ? 0 : sampleSize;

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

    /**
     * Module output inerface. Saves the table into the CSV or Database
     * 
     * @return Path to the output file, where the table is stored
     */
    public String save() {
        String filePath = storage.saveTable(table, rowNumber);
        if (filePath == null) {
            System.err.println("Failed to save table to CSV file");
        }

        return filePath;
    }
}

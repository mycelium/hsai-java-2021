package ru.spbstu.telematics.java.hsai_java_lab.value;

import java.util.ArrayList;

import ru.spbstu.telematics.java.hsai_java_lab.storage.CsvStorage;
import ru.spbstu.telematics.java.hsai_java_lab.storage.DatabaseStorage;
import ru.spbstu.telematics.java.hsai_java_lab.storage.Storage;
import ru.spbstu.telematics.java.hsai_java_lab.storage.Storage.StorageType;

public class RandomValueTable {
    private String name;
    private ArrayList<RandomValue> table;
    private int rowNumber;
    private Storage storage;

    /**
     * Module input interface. Represents the table of the
     * random values with different distributions, where each row
     * represents one random value
     * 
     * @param values Array of random values
     * @param name Name of the table
     * @param sampleSize Number of rows in the table
     * @param storageType Type of table storage: CSV or Database
     */
    public RandomValueTable(ArrayList<RandomValue> values, String name, int sampleSize, StorageType storageType) {
        try {
            this.table = new ArrayList<RandomValue>(values);
        }
        catch (NullPointerException e) {
            throw e;
        }
        
        this.name = (name == null) ? "RandomValueTable" : name;
        this.rowNumber = (sampleSize < 0) ? 0 : sampleSize;

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
     * @return Path to the output file, where the table is stored;
     *         Null if method failed to store table 
     */
    public String save() throws NullPointerException, Exception{
        try {
            String filePath = storage.saveTable(table, name, rowNumber);
            return filePath;
        }
        catch (NullPointerException e){
            System.err.println("Failed to save table");
            throw e;
        }
        catch (Exception e) {
            System.err.println("Failed to save table");
            throw e;
        }
    }
}

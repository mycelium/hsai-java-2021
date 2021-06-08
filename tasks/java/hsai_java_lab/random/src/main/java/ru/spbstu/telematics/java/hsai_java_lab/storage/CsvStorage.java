package ru.spbstu.telematics.java.hsai_java_lab.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValue;
import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValueSample;

public class CsvStorage implements Storage {
    private final String propertyPath = "../resources/storage.properties";

    private static final Logger logger = LoggerFactory.getLogger(CsvStorage.class);

    @Override
    public String saveTable(ArrayList<RandomValue> table, String name, int rowNumber) throws StorageException {
        if (table == null) {
            logger.error("Random Value Table is null");
            throw new NullPointerException("Random Value Table is null");
        }

        if (rowNumber < 0) {
            logger.error("Table row number is less then 0");
            throw new IllegalArgumentException("Table row number is less then 0");
        }

        String tableName = (name == null) ? "RandomValueTable" : name;
        int columnNumber = table.size();

        /* Create header row */
        ArrayList<String> names = new ArrayList<String>(table.size());
        for (int i = 0; i < columnNumber; i++) {
            RandomValue value = table.get(i);
            String valueName = value.getName();

            if (valueName == null) {
                valueName = "Distribution" + i;
            }
            else {
                valueName = valueName.replace("\n", " ");
                valueName = valueName.replace("\"", "`");
                valueName = valueName.replace(",", ".");
            }
            names.add(valueName);
        }

        String header = String.join(",", names);
        logger.info("Table header created");

        /* Open/Create File to write data */
        String filePath;

        try (InputStream istream = new FileInputStream(propertyPath)) {
            Properties storageProp = new Properties();
            storageProp.load(istream);
            filePath = storageProp.getProperty("csv.folder") + "/" + tableName + ".csv";
        }
        catch (IOException e) {
            logger.error("Failed to configure CVS file path" + e.getMessage());
            throw new StorageException("Failed to open Storage Properties file: " + e.getMessage(), StorageType.CSV);
        }
        logger.info("CVS file path configured");

        /* Populate File with data */
        ArrayList<String> tableRows = new ArrayList<String>(table.size());

        try {
            File fout = new File(filePath);
            FileWriter foutWriter = new FileWriter(fout);

            foutWriter.write(header + "\n");
            
            ArrayList<String> row = new ArrayList<String>();
            for (int i = 0; i < rowNumber; i++) {
                row.clear();

                for (int j = 0; j < columnNumber; j++) {
                    row.add(String.format("%10.5f", table.get(j).generate()).replace(",", "."));
                }

                foutWriter.write(String.join(",", row) + "\n");
            }
                
            foutWriter.flush();
            foutWriter.close();
            
            filePath = fout.getAbsolutePath();
        }
        catch (IOException e) {
            logger.error("Failed to save table to CSV output file: " + e.getMessage());
            throw new StorageException("Failed to save table to CSV output file: " + e.getMessage(), StorageType.CSV);
        }

        logger.info("Table stored to the CVS file " + filePath);
        
        return filePath;
    }    
}

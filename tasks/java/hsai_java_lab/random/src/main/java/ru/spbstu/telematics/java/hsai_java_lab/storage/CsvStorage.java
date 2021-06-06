package ru.spbstu.telematics.java.hsai_java_lab.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValue;

public class CsvStorage implements Storage {
    @Override
    public String saveTable(ArrayList<RandomValue> table, String name, int rowNumber)
        throws NullPointerException, Exception
    {
        if (table == null) {
            throw new NullPointerException("Table is null");
        }

        if (rowNumber < 0) {
            throw new Exception("Table row number is < 0");
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

        /* Create array of data rows */
        ArrayList<String> tableRows = new ArrayList<String>(table.size());
        for (int i = 0; i < rowNumber; i++) {
            ArrayList<String> row = new ArrayList<String>();

            for (int j = 0; j < columnNumber; j++) {
                row.add(String.format("%10.5f", table.get(j).generate()).replace(",", "."));
            }

            tableRows.add(String.join(",", row));
        }

        String filePath;

        try (InputStream istream = new FileInputStream("src/main/resources/storage.properties")) {
            Properties storageProp = new Properties();
            storageProp.load(istream);
            filePath = storageProp.getProperty("csv.folder") + "/" + tableName + ".csv";
        }
        catch (IOException e) {
            throw new Exception("Failed to open Storage Properties file");
        }

        try {
            File fout = new File(filePath);
            FileWriter foutWriter = new FileWriter(fout);

            foutWriter.write(header + "\n");

            for (String s : tableRows) {
                foutWriter.write(s + "\n");
            }

            foutWriter.flush();
            filePath = fout.getAbsolutePath();
        }
        catch (IOException e) {
            throw new Exception("Failed to open CSV output file");
        }
        
        return filePath;
    }    
}

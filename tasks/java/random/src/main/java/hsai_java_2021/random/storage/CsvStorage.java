package hsai_java_2021.random.storage;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import hsai_java_2021.random.RandomValue;

public class CsvStorage implements Storage {
    private final String fileName = "random_values.csv";

    @Override
    public String saveTable(ArrayList<RandomValue> table, int rowNumber) {
        if (table == null) {
            System.err.println("Table is null");
            return null;
        }

        if (rowNumber < 0) {
            System.err.println("Table row number is < 0");
            return null;
        }

        int columnNumber = table.size();

        /* Create header row */
        ArrayList<String> names = new ArrayList<String>(table.size());
        for (int i = 0; i < columnNumber; i++) {
            RandomValue value = table.get(i);
            String name = value.getName();

            if (name == null) {
                name = "Distribution" + i;
            }
            else {
                name = name.replace("\n", " ");
                name = name.replace("\"", "`");
                name = name.replace(",", ".");
            }
            names.add(name);
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

        String filePath = null;

        try (InputStream istream = new FileInputStream("src/main/resources/storage.properties")) {
            Properties storageProp = new Properties();
            storageProp.load(istream);
            filePath = storageProp.getProperty("csv.folder") + "/" + fileName;
        }
        catch (IOException e) {
            System.err.println("Failed to open Storage Properties file");
            e.printStackTrace();
            return null;
        }

        try (FileWriter fout = new FileWriter(filePath)) {
            fout.write(header + "\n");

            for (String s : tableRows) {
                fout.write(s + "\n");
            }

            fout.flush();
        }
        catch (IOException e) {
            System.err.println("Failed to open CSV output file");
            e.printStackTrace();
            return null;
        }
        
        return filePath;
    }    
}

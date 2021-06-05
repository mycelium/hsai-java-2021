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
    public String saveTable(ArrayList<RandomValue> table) {
        /* Create header row */
        ArrayList<String> names = new ArrayList<String>(table.size());
        for (RandomValue v : table) {
            String name = v.getName();

            if (name == null) {
                name = "Noname";
            }
            else {
                name = name.replace("\n", " ");
                name = name.replace("\"", "`");
                name = name.replace(",", ".");
            }
            names.add(name);
        }

        String header = String.join(",", names);

        /* Check if all columns have equal size */
        int rowNumber = table.get(0).getSample().size();
        for (RandomValue v : table) {
            if (v.getSample().size() != rowNumber) {
                System.err.println("Inconstant number of rows");
                return null;
            }
        }

        /* Create array of data rows */
        ArrayList<String> tableRows = new ArrayList<String>(table.size());
        for (int i = 0; i < rowNumber; i++) {
            ArrayList<String> row = new ArrayList<String>();

            for (int j = 0; j < table.size(); j++) {
                row.add(table.get(j).getSample().get(i).toString());
            }

            tableRows.add(String.join(",", row));
        }

        String filePath;

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

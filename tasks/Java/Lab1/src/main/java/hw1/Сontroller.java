package hw1;

import hw1.storage.CSV;
import hw1.storage.Database;
import hw1.storage.Storage;

import java.io.IOException;


public class Сontroller {
    String fileName;
    DataTable dataTable;
    Storage storage;

    public Сontroller(int size) {
        dataTable = new DataTable(size);
    }

    public String create(String line) throws IOException {
        String[] subStr = line.split(" ");
        for (int i = 0; i < subStr.length; i++) {
            if(subStr[i].equals("normal")){
                dataTable.generate("normal", Double.parseDouble(subStr[i+1]), Double.parseDouble(subStr[i+2]));
                i += 2;
            }
            else if(subStr[i].equals("uniform")) {
                dataTable.generate("uniform", Double.parseDouble(subStr[i+1]), Double.parseDouble(subStr[i+2]));
                i += 2;
            }
            else if(subStr[i].equals("poisson")) {
                dataTable.generate("poisson", Double.parseDouble(subStr[i+1]));
                i += 1;
            }
            else if(subStr[i].equals("CSV")){
                fileName = "C:/User/Idea_labs/Java/Lab1/results/result.csv";
                storage = new CSV(fileName);
            }
            else if(subStr[i].equals("DBF")){
                fileName = "C:/User/Idea_labs/Java/Lab1/results/result.dbf";
                storage = new Database(fileName);
            }
        }
        return storage.storage(dataTable.dataTable);
    }
}

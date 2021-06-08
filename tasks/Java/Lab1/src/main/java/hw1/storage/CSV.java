package hw1.storage;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CSV implements Storage{
    String fileName;

    public CSV(String fileName){
        this.fileName = fileName;
    }

    @Override
    public String storage(HashMap<String, ArrayList<Double>> dataTable)  throws IOException {
        if (dataTable == null) {
            System.err.println("DataTable is null");
            return null;
        }

        FileWriter fileWriter = new FileWriter(fileName);
        ArrayList<String> listKey = new ArrayList<>();
        for(String value : dataTable.keySet())
            listKey.add(value);
        try (CSVPrinter printer = new CSVPrinter(fileWriter, CSVFormat.EXCEL.withHeader(listKey.toString()))) {
            int size = 0;
            for(ArrayList<Double> value : dataTable.values())
                size = value.size();
            for(int i = 0;  i < size; i++){
                ArrayList<Double> list = new ArrayList<>();
                for (ArrayList<Double> value : dataTable.values()) {
                    list.add(value.get(i));
                }
                printer.printRecord(list.toArray());
            }
        }

        return fileName;
    }
}

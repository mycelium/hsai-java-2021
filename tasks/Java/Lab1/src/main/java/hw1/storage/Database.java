package hw1.storage;

import com.linuxense.javadbf.*;

import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.util.HashMap;

public class Database implements Storage{
    String fileName;

    public Database(String fileName){
        this.fileName = fileName;
    }

    @Override
    public String storage(HashMap<String, ArrayList<Double>> dataTable) throws IOException {
        if (dataTable == null) {
            System.err.println("DataTable is null");
            return null;
        }

        DBFField[] fields = new DBFField[dataTable.size()];
        int iterField = 0;
        for(String value : dataTable.keySet()) {
            fields[iterField] = new DBFField(value.substring(0, 9), DBFDataType.FLOATING_POINT);
            fields[iterField].setLength(10);
            iterField++;
        }

        try (DBFWriter writer = new DBFWriter(new FileOutputStream(fileName))) {
            writer.setFields(fields);
            int size = 0;
            for(ArrayList<Double> value : dataTable.values())
                size = value.size();
            for(int i = 0;  i < size; i++){
                Object[] objects = new Object[iterField];
                int j = 0;
                for (ArrayList<Double> value : dataTable.values()) {
                    objects[j++] = value.get(i);
                }
                writer.addRecord(objects);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}

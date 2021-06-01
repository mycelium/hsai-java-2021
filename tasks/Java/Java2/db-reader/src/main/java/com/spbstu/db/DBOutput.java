package com.spbstu.db;

import com.linuxense.javadbf.DBFDataType;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DBOutput {
    public DBOutput(String filename) {
        this.filename = filename;
    }

    String filename;

    public String save(ArrayList<Double> list) throws IOException {
        DBFField[] fields = new DBFField[2];
        fields[0] = new DBFField("value", DBFDataType.FLOATING_POINT);
        fields[0].setLength(20);
        try (DBFWriter writer = new DBFWriter(new FileOutputStream(filename + ".dbf"))) {
            writer.setFields(fields);
            for (Double value : list) {
                Object[] objects = new Object[1];
                objects[0] = value;
                writer.addRecord(objects);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename + ".dbf";
    }
}

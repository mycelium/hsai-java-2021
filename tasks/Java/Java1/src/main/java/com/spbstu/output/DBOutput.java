package com.spbstu.output;

import com.linuxense.javadbf.DBFDataType;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DBOutput implements Saving {
    public DBOutput(String filename) {
        this.filename = filename;
    }

    String filename;

    @Override
    public String save(ArrayList<Double> list) throws IOException {
        DBFField[] fields = new DBFField[2];
        fields[0] = new DBFField("value", DBFDataType.FLOATING_POINT);
        fields[0].setLength(20);
        fields[1] = new DBFField("names", DBFDataType.CHARACTER);
        fields[1].setLength(20);
        try (DBFWriter writer = new DBFWriter(new FileOutputStream(filename + ".dbf"))) {
            writer.setFields(fields);
            for (Double value : list) {
                Object[] objects = new Object[2];
                objects[0] = value;
                objects[1]="A";
                writer.addRecord(objects);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename + ".dbf";
    }
}

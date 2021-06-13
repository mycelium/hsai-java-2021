package output;

import com.linuxense.javadbf.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DBOutputter {
    final static int fieldSize = 10;

    public static String output(ArrayList<Double> data, String fname) {
        if (data == null) throw new IllegalArgumentException("Data cannot be null");
        Path filename = Paths.get(fname);
        DBFField field = new DBFField();
        field.setName("val:");

        field.setType(DBFDataType.NUMERIC);
        field.setLength(fieldSize);
        field.setDecimalCount(4);

        DBFField[] fs = new DBFField[1];
        fs[0] = field;

        try (DBFWriter writer = new DBFWriter(new FileOutputStream(filename.toString()))) {
            writer.setFields(field);
            for (int i = 0; i < data.size(); i++) {
                writer.addRecord(data.subList(i, i).toArray());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename.toAbsolutePath().toString();
    }
}
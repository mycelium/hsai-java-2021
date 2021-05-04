package ru.spbstu.telematics.lab1.saving;

import ru.spbstu.telematics.lab1.RandomData;
import com.linuxense.javadbf.*;
import ru.spbstu.telematics.lab1.Variable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * File saver for '.dbf' tables. Using JavaDBF lib.<br>
 * See: https://github.com/albfernandez/javadbf <br>
 * .dbf table can have column names with length < 10,
 * if length more is needed, names of columns are saved into
 * separate file with name '[table filename].names'.
 */
class DBFSaver implements FileSaver {

    /**
     * Size of numeric value in table.
     */
    final static int fieldSize = 10;

    @Override
    public String save(RandomData data, int size) {
        if (data == null) throw new IllegalArgumentException("Data cannot be null");
        Path filename = Saving.getDirectory(type()).resolve(Saving.getFileName(data, type()));
        ArrayList<Variable> variables = data.getVariables();
        DBFField[] fields = new DBFField[variables.size()];
        List<String> names = data.columnNames();
        boolean longNames = false;
        for (String name: names) {
            if (name.length() >= 10) {
                longNames = true;
                break;
            }
        }
        for (int i = 0; i < variables.size(); i++) {
            fields[i] = new DBFField();
            if (longNames) {
                fields[i].setName("$" + i);
            }
            else {
                fields[i].setName(names.get(i));
            }
            fields[i].setType(DBFDataType.NUMERIC);
            fields[i].setLength(fieldSize);
            if (!variables.get(i).getDistribution().isDiscrete()) {
                fields[i].setDecimalCount(4);
            }
        }
        try (DBFWriter writer = new DBFWriter(new FileOutputStream(filename.toString()))) {
            writer.setFields(fields);
            for (int i = 0; i < size; i++) {
                writer.addRecord(data.generateRow().toArray());
            }
            if (longNames) {
                Files.write(Paths.get(filename + ".names"),
                        String.join("\n", names).getBytes(),
                        StandardOpenOption.CREATE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename.toAbsolutePath().toString();
    }

    @Override
    public SavingType type() {
        return SavingType.Database;
    }
}

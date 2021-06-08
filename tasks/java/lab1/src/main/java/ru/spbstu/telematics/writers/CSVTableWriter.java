package ru.spbstu.telematics.writers;

import ru.spbstu.telematics.table.Table;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class CSVTableWriter extends FileTableWriter {

    public CSVTableWriter(String file) throws IOException {
        super(file);
        createFileWithExtension(file, ".csv");
    }

    @Override
    public void write(Table table) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(Files.newOutputStream(outPath)));
        writeLine(table.getSamplesName(), dataOutputStream);
        for (List<?> line : table.getTableValues()) {
            writeLine(line, dataOutputStream);
        }
        dataOutputStream.close();
    }

    private <T> void writeLine(List<T> line, DataOutputStream dataOutputStream) throws IOException {
        String prefix = "";
        for (T elem : line) {
            dataOutputStream.writeBytes(prefix);
            prefix = ",";
            String writeElem = elem.toString();
            if (elem instanceof String) {
                writeElem = "\"" + writeElem + "\"";
            }
            dataOutputStream.writeBytes(writeElem);
        }
        dataOutputStream.writeBytes("\n");
    }


}

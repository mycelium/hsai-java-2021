package ru.spbstu.telematics.writers;

import com.opencsv.CSVWriter;
import ru.spbstu.telematics.table.Table;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class CVSTableWriter extends FileTableWriter{

    @Override
    public void write(Table table, String filePath) throws IOException {
        Path out = createFileWithExtension(filePath, ".cvs");
        CSVWriter writer = new CSVWriter(new FileWriter(out.toString()));
        writer.writeNext(table.getSamplesName());
        writer.writeAll(table.getTableValues());
    }

}

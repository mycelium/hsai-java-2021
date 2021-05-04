package ru.spbstu.telematics.lab1.saving;

import ru.spbstu.telematics.lab1.RandomData;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * File saver for '.csv' tables.
 */
class CSVSaver implements FileSaver {

    /**
     * Convert 'val' to string using <code>toString()</code>, then
     * '\"' are escaped and outer quotes added if needed.
     * @param val object to convert.
     * @return string representation ready to be put in csv table.
     */
    private String processValue(Object val) {
        String string = val.toString();
        string = string.replaceAll("\"", "\\\"");
        if (string.contains(","))
            string = "\"" + string + "\"";
        return string;
    }

    /**
     * @param items array of object to form csv row.
     * @return string representation of csv row.
     */
    private String rowFrom(List<?> items) {
        return items.stream().map(this::processValue).collect(Collectors.joining(",")) + '\n';
    }

    @Override
    public String save(RandomData data, int size) {
        if (data == null) throw new IllegalArgumentException("Data cannot be null");
        Path filename = Saving.getDirectory(type()).resolve(Saving.getFileName(data, type()));
        try(FileWriter writer = new FileWriter(filename.toFile())) {
            writer.write(rowFrom(data.columnNames()));
            for (int i = 0; i < size; i++) {
                writer.write(rowFrom(data.generateRow()));
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename.toAbsolutePath().toString();
    }

    @Override
    public SavingType type() {
        return SavingType.CSV;
    }
}

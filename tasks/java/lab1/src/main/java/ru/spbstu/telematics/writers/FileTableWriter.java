package ru.spbstu.telematics.writers;

import ru.spbstu.telematics.table.Table;

public abstract class FileTableWriter {

    public abstract void write(Table table, String filePath);

}

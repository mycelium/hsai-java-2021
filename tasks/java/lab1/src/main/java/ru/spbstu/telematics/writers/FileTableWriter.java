package ru.spbstu.telematics.writers;

import ru.spbstu.telematics.table.Table;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

abstract class FileTableWriter {

    protected Path createFileWithExtension(String filePath, String extension) throws IOException {
        Path path = Path.of(filePath);
        if (!filePath.endsWith(extension)) {
            throw new IllegalArgumentException("File must have" + extension + "extension");
        }
        Files.createDirectories(path.getParent());
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        return path;
    }

    abstract void write(Table table, String filePath) throws Exception;

}

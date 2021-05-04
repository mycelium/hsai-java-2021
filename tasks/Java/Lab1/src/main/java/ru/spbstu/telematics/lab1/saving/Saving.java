package ru.spbstu.telematics.lab1.saving;

import ru.spbstu.telematics.lab1.RandomData;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 'Package object' for saving package.
 * Provides all saver implementations with filenames and output directories.
 */
public class Saving {

    /**
     * @param type type of saver implementation.
     * @return string representation of implementation.
     */
    static String typeName(SavingType type) {
        switch (type) {
            case CSV: return "CSV";
            case Database: return "DB";
        }
        return "Unknown";
    }

    /**
     * @param type type of saver implementation.
     * @return file extension for saver implementation.
     */
    static String extension(SavingType type) {
        switch (type) {
            case CSV: return ".csv";
            case Database: return ".dbf";
        }
        return ".txt";
    }

    static Path getDirectory(SavingType type) {
        return Paths.get("output");
    }

    static String getFileName(RandomData data, SavingType type) {
        return typeName(type) + "_" +
                data.getName() +
                extension(type);
    }

    public static FileSaver buildSaver(SavingType type) {
        switch (type) {
            case CSV: return new CSVSaver();
            case Database: return new DBFSaver();
        }
        return new CSVSaver();
    }

    /**
     * Saves given number of rows of random data with given saving type.
     * @param data data to save.
     * @param size number of row.
     * @param type type of saver implementation.
     * @return absolute path to file.
     */
    public static String save(RandomData data, int size, SavingType type) {
        return buildSaver(type).save(data, size);
    }
}

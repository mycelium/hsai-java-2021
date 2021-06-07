package ru.spbstu.telematics.java.hsai_java_lab.storage;

import ru.spbstu.telematics.java.hsai_java_lab.storage.Storage.StorageType;

public class StorageException extends Exception {
    private StorageType type;

    public StorageException (String message, StorageType type) {
        super(message);
        this.type = type;
    }

    public StorageType getStorageType() {
        return type;
    }
}

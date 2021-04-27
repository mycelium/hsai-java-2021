package ru.spbstu.hsai.pichandler.image;

import java.io.Serializable;

public class RawImage implements Serializable {
    public RawImage(byte[] file) {
        super();
        this.file = file;
    }

    byte[] file;

    public byte[] getBytes() {
        return file;
    }

    public void setBytes(byte[] file) {
        this.file = file;
    }
    
    
    
    
}

package ru.spbstu.hsai.pichandler.image;

import java.io.Serializable;

public class RawImage implements Serializable {
    public RawImage(Byte[] file) {
        super();
        this.file = file;
    }

    Byte[] file;
    
    
}

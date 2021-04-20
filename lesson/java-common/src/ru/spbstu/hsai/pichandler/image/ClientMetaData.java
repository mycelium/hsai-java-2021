package ru.spbstu.hsai.pichandler.image;

import java.io.Serializable;

public class ClientMetaData implements Serializable {
    public ClientMetaData(String imageName) {
        super();
        this.imageName = imageName;
    }

    private String imageName;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    
}

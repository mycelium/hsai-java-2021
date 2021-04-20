package ru.spbstu.hsai.pichandler.image;

import java.io.Serializable;

public class Image implements Serializable {
    private RawImage rawImage;
    public RawImage getRawImage() {
        return rawImage;
    }


    public void setRawImage(RawImage rawImage) {
        this.rawImage = rawImage;
    }


    public ClientMetaData getMetaData() {
        return metaData;
    }


    public void setMetaData(ClientMetaData metaData) {
        this.metaData = metaData;
    }


    private ClientMetaData metaData;
    
    
    public Image(RawImage rawImage, ClientMetaData metaData) {
        super();
        this.rawImage = rawImage;
        this.metaData = metaData;
    }
    
    
}

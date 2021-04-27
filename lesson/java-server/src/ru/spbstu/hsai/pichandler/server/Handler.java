package ru.spbstu.hsai.pichandler.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import ru.spbstu.hsai.pichandler.image.Image;

public class Handler implements Runnable {

    InputStream input;
    OutputStream output;
    
    final Path storage = Path.of("storage");

    public Handler(InputStream input, OutputStream output) {
        super();
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {
        try {

            ObjectInputStream objectIS = new ObjectInputStream(input);
            PrintWriter writer = new PrintWriter(output);
            Object inputObj = objectIS.readObject();
            if (!(inputObj instanceof Image)) {
                writer.println("You sent not Image");
                //TODO: допилить ветку
            } else {
                Image image = (Image) inputObj;
                //writer.println("Image name: " + image.getMetaData().getImageName());
                String name = System.currentTimeMillis() + "_" + image.getMetaData().getImageName();
                Path filePath = storage.resolve(name);
                Files.write(filePath, image.getRawImage().getBytes(), StandardOpenOption.CREATE);
                
                Thread.sleep(10000);
            }
            System.out.println(Thread.currentThread().getName() + " has handled everything well!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

package ru.spbstu.hsai.pichandler.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import ru.spbstu.hsai.pichandler.image.ClientMetaData;
import ru.spbstu.hsai.pichandler.image.Image;
import ru.spbstu.hsai.pichandler.image.RawImage;

public class Main {
    final static int port = 80;

    public static void main(String[] args) {
        String filename = "kotik1.png";
        String directory = "input/";
        try (Socket socket = new Socket("127.0.0.1", port)) {
            ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());
            File file = new File(directory + filename);
            if (file.isFile()) {
                FileInputStream fis = new FileInputStream(file);
                Image image = new Image(new RawImage(fis.readAllBytes()), 
                        new ClientMetaData(filename));
                objectOS.writeObject(image);
                objectOS.flush();
            }
            else {
                // Ругаться
            }
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

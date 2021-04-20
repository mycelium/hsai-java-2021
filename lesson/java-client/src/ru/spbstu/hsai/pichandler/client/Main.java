package ru.spbstu.hsai.pichandler.client;

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
        try (Socket socket = new Socket("127.0.0.1", port)) {
            ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());
            Image image = new Image(new RawImage(new Byte[] {1, 2, 3}), 
                    new ClientMetaData("Moi kotik 1"));
            objectOS.writeObject(image);
            objectOS.flush();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

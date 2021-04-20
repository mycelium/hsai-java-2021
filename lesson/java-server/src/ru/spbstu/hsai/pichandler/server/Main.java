package ru.spbstu.hsai.pichandler.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import ru.spbstu.hsai.pichandler.image.Image;




public class Main {
    
    final static int port = 80;
    
    public static void main(String[] args) {
        Socket accepted;
        try (ServerSocket socket = new ServerSocket(port)) {
            System.out.println("Waiting for connection....");
            accepted = socket.accept();
            System.out.println("Connection established!");
            InputStream inputStream = accepted.getInputStream();
            ObjectInputStream objectIS = new ObjectInputStream(inputStream);
            Object inputObj = objectIS.readObject();
            if (!(inputObj instanceof Image)) {
                System.out.println("You sent not Image");   
                //TODO: допилить ветку
            }
            else {
                Image image = (Image) inputObj;
                System.out.println("Image name: " + 
                        image.getMetaData().getImageName());
                
            }
            String res = new String(inputStream.readAllBytes());
            System.out.println("Received: " + res);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            // TODO My catch block, pls complete later
            e.printStackTrace();
        };
    }
}

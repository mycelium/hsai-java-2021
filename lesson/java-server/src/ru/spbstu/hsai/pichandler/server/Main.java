package ru.spbstu.hsai.pichandler.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    final static int port = 80;

    public static void main(String[] args) throws InterruptedException {
        Socket accepted;
        ExecutorService pool = Executors.newFixedThreadPool(10);
        try (ServerSocket socket = new ServerSocket(port)) {
            while (true) {
                System.out.println("Waiting for connection....");
                accepted = socket.accept();
                System.out.println("Connection established!");
                Handler handler = new Handler(accepted.getInputStream(),
                                              accepted.getOutputStream());
                pool.execute(handler);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

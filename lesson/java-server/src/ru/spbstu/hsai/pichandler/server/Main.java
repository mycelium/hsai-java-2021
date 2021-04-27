package ru.spbstu.hsai.pichandler.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Main {

    final static int port = 80;
    static Logger logger = LoggerFactory.getLogger(Main.class);
    
    public static void main(String[] args) throws InterruptedException {
        Socket accepted;
        ExecutorService pool = Executors.newFixedThreadPool(10);
        try (ServerSocket socket = new ServerSocket(port)) {
            while (true) {
                logger.info("Waiting for connection....");
                accepted = socket.accept();
                logger.info("Connection established!");
                Handler handler = new Handler(accepted.getInputStream(),
                                              accepted.getOutputStream());
                pool.execute(handler);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}

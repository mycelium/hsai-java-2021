package ru.spbstu.hsai.pichandler.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.spbstu.hsai.pichandler.server.config.PichandlerProperties;
import ru.spbstu.hsai.pichandler.server.image.manager.Handler;

public class Main {

    private static final String SERVER_REQUESTPROCESSING_POOL = "server.requestprocessing.pool";
    private static final String SERVER_SOCKET_PORT = "server.socket.port";

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        int port = PichandlerProperties.getInstance().getIntPropertyOrElse(SERVER_SOCKET_PORT, 8080);
        int serverSocketRequestProcessingPoolSize = PichandlerProperties.getInstance().getIntPropertyOrElse(SERVER_REQUESTPROCESSING_POOL, 8);
        Socket accepted;
        ExecutorService pool = Executors.newFixedThreadPool(serverSocketRequestProcessingPoolSize);

        try (ServerSocket socket = new ServerSocket(port)) {
            while (true) {
                logger.info("Waiting for connection....");
                accepted = socket.accept();
                logger.info("Connection established!");
                Handler handler = new Handler(accepted.getInputStream(), accepted.getOutputStream());
                pool.execute(handler);
            }

        } catch (IOException e) {
            logger.error("Error opening socket:", e);
        }
    }
}

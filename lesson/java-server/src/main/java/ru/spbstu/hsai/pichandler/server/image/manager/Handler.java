package ru.spbstu.hsai.pichandler.server.image.manager;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.spbstu.hsai.pichandler.image.Image;
import ru.spbstu.hsai.pichandler.server.config.PichandlerProperties;

public class Handler implements Runnable {

    private static final String SERVER_STORAGE_DIR = "server.storage.dir";
    private final Path storage = Path.of(PichandlerProperties.getInstance().getPropertyOrElse(SERVER_STORAGE_DIR, "storage"));
    private InputStream input;
    private OutputStream output;

    private static Logger logger = LoggerFactory.getLogger(Handler.class);

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
            logger.info(Thread.currentThread().getName() + " has handled everything well!");
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }
}

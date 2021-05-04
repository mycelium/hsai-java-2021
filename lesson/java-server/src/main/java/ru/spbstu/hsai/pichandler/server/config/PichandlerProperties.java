package ru.spbstu.hsai.pichandler.server.config;

import java.io.IOException;
import java.util.Properties;

import ru.spbstu.hsai.pichandler.server.Main;

public class PichandlerProperties {

    private static PichandlerProperties instance;
    private static final Object monitor = new Object();

    private Properties props = new Properties();

    private PichandlerProperties() {
        try {
            props.load(Main.class.getResourceAsStream("/pichandler.properties"));
        } catch (IOException e) {
            System.exit(-1);
        }
    }

    public String getProperty(String key) {
        return props.getProperty(key);
    }

    public String getPropertyOrElse(String key, String defaultValue) {
        return props.contains(key) ? props.getProperty(key) : defaultValue;
    }

    public int getIntProperty(String key) {
        return Integer.valueOf(props.getProperty(key));
    }

    public int getIntPropertyOrElse(String key, int defaultValue) {
        try {
            return Integer.valueOf(props.getProperty(key));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static PichandlerProperties getInstance() {
        if (instance == null) {
            synchronized (monitor) {
                if (instance == null) {
                    instance = new PichandlerProperties();
                }
            }
        }
        return instance;
    }
}

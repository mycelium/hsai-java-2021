package ru.spbstu.telematics.java.hsai_java_lab.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.spbstu.telematics.java.hsai_java_lab.storage.DatabaseReader;
import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValueTable;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        InputParser parser = new InputParser();
        RandomValueTable table = parser.interactiveMode();
        logger.info("Input information is collected");

        MainBusinessProcess mainProcess;
        try {
            mainProcess = new MainBusinessProcess(table);
        }
        catch (NullPointerException e) {
            logger.info("<im process failed>");
            return;
        }

        int status = mainProcess.runProcess();
        if (status == 0) {
            logger.info("Program finished with code 0");
        }
        else {
            logger.error("Program finished with code 1");
        }
    }
}

package ru.spbstu.telematics.java.hsai_java_lab.business;

import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValueTable;

public class Main {
    public static void main(String[] args) {
        InputParser parser = new InputParser();
        RandomValueTable table = parser.interactiveMode();

        MainBusinessProcess mainProcess;
        try {
            mainProcess = new MainBusinessProcess(table);
        }
        catch (NullPointerException e) {
            return;
        }

        int status = mainProcess.runProcess();
    }
}

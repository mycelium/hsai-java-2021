package ru.spbstu.telematics;

import jdk.internal.org.jline.reader.SyntaxError;

public class ArgParser {
    enum Source {
        CSV,
        DB
    }
    enum Action {
        JSON,
        NORMAL,
        PLOT,
        HIST
    }
    Source source;
    Action action;
    String filename;
    int column;

    ArgParser(String[] args){
        process(0, 0, args);
    }

    void process(int state, int current, String[] args) {
        String s = args[current];
        if (state == 0) {
            if (s.equals("--csv")) {
                filename = args[current + 1];
                source = Source.CSV;
                process(1, current + 2, args);
            } else if (s.equals("--db")) {
                source = Source.DB;
                process(1, current + 1, args);
            } else throw new IllegalArgumentException("Unexpected argument: " + s);
        } else if (state == 1) {
            if (s.equals("--norm")) {
                action = Action.NORMAL;
                process(2, current + 1, args);
            } else if (s.equals("--json")) {
                action = Action.JSON;
                process(2, current + 1, args);
            } else if (s.equals("--plot")) {
                action = Action.PLOT;
                process(2, current + 1, args);
            } else if (s.equals("--hist")) {
                action = Action.HIST;
                process(2, current + 1, args);
            } else throw new IllegalArgumentException("Unexpected argument: " + s);
        } else column = Integer.parseInt(s);
    }
}

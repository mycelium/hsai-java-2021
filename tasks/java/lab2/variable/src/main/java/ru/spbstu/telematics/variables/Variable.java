package ru.spbstu.telematics.variables;

import java.util.ArrayList;

public class Variable<T> extends ArrayList<T> {

    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


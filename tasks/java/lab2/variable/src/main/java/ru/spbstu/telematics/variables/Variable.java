package ru.spbstu.telematics.variables;

import java.util.ArrayList;
import java.util.Collection;

public class Variable<T> extends ArrayList<T> {

    public Variable(Collection<? extends T> c) {
        super(c);
    }

    public Variable(String name) {
        this.name = name;
    }

    public Variable() {
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


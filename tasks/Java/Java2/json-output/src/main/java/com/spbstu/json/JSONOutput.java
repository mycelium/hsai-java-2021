package com.spbstu.json;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONOutput implements com.spbstu.data.Output{

    public JSONOutput(ArrayList<Double> data, String filename) {
        this.data = data;
        this.filename = filename;
    }

    ArrayList<Double> data;
    String filename;
    public void output(ArrayList<Double> data,String filename) throws IOException {
        Gson gson=new Gson();
        System.out.println(gson.toJson(data));
        FileWriter writer = new FileWriter(filename);
        gson.toJson(data, writer);
        writer.close();
    }

    @Override
    public void output() {
        Gson gson=new Gson();
        System.out.println(gson.toJson(data));
        FileWriter writer = null;
        try {
            writer = new FileWriter(filename);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        gson.toJson(data, writer);
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}

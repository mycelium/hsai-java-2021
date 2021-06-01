package com.spbstu;

import com.spbstu.json.JSONOutput;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JSONOutputTest {
    @Test
    public void OutputTest() throws IOException {
        ArrayList<Double> list = new ArrayList<>();
        for(int i=0;i<8;i++){
            list.add(Double.valueOf(i));
        }
        JSONOutput jsonOutput=new JSONOutput(list,"TestOutput.json");
        jsonOutput.output();
        Assert.assertTrue(Files.exists(Paths.get("TestOutput.json")));
        Files.delete(Paths.get("TestOutput.json"));
    }

}

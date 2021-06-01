package ru.spbstu.telematics;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {
    String url1, url2;
    String path = "./Output/";

    @Test
    public void testPoisson() {
        String name1 = "poissonCSV";
        String name2 = "poissonDB";
        RandomData data = new RandomData("poisson", name1, 100, "csv");
        url1 = data.generate();
        Assert.assertEquals(url1, path + name1 + ".csv");
        RandomData data1 = new RandomData("poisson", name2, 100, "db");
        url2 = data1.generate();
        Assert.assertEquals(url2, path + name2 + ".db");
    }

    @Test
    public void testUniform() {
        String name1 = "uniformCSV";
        String name2 = "uniformDB";
        RandomData data = new RandomData("uniform", "uniformCSV", 100, "csv");
        url1 = data.generate();
        Assert.assertEquals(url1, path + name1 + ".csv");
        RandomData data1 = new RandomData("uniform", "uniformDB", 100, "db");
        url2 = data1.generate();
        Assert.assertEquals(url2, path + name2 + ".db");
    }

    @Test
    public void testNormal() {
        String name1 = "normalCSV";
        String name2 = "normalDB";
        RandomData data = new RandomData("normal", "normalCSV", 100, "csv");
        url1 = data.generate();
        Assert.assertEquals(url1, path + name1 + ".csv");
        RandomData data1 = new RandomData("normal", "normalDB", 100, "db");
        url2 = data1.generate();
        Assert.assertEquals(url2, path + name2 + ".db");
    }
}

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
        String name1 = "poisson100k";
        String name2 = "poisson100k";
        RandomData data = new RandomData("poisson", name1, 100000, "csv");
        url1 = data.generate();
        //Assert.assertEquals(url1, path + name1 + ".csv");
        RandomData data1 = new RandomData("poisson", name2, 100000, "db");
        url2 = data1.generate();
        //Assert.assertEquals(url2, path + name2 + ".db");
    }

    @Test
    public void testUniform() {
        String name1 = "uniform100k";
        String name2 = "uniform100k";
        RandomData data = new RandomData("uniform", "uniform100k", 100000, "csv");
        url1 = data.generate();
        Assert.assertEquals(url1, path + name1 + ".csv");
        RandomData data1 = new RandomData("uniform", "uniform100k", 100000, "db");
        url2 = data1.generate();
        Assert.assertEquals(url2, path + name2 + ".db");
    }

    @Test
    public void testNormal() {
        String name1 = "normal100k";
        String name2 = "normal100k";
        RandomData data = new RandomData("normal", "normal100k", 100000, "csv");
        url1 = data.generate();
        Assert.assertEquals(url1, path + name1 + ".csv");
        RandomData data1 = new RandomData("normal", "normal100k", 100000, "db");
        url2 = data1.generate();
        Assert.assertEquals(url2, path + name2 + ".db");
    }
}

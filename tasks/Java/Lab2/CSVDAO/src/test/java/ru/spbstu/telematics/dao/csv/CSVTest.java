package ru.spbstu.telematics.dao.csv;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;


public class CSVTest
{
    @Test
    public void testParsing() {
        String filename = "I:\\Uni\\6-Sem\\Java\\Labs\\tasks\\Java\\Lab2\\CSVDAO\\src\\test\\resources\\ru.spbstu.telematics.dao.csv\\CSV_data1.csv";
        try {
            CSVDAO csvdao = new CSVDAO(filename);
            System.out.println(Arrays.toString(csvdao.getNames()));
        } catch (IOException e) {
            assertTrue(false);
        }
    }
}

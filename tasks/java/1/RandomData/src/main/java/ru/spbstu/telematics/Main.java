package ru.spbstu.telematics;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main( String[] args ) {
        RandomData data = new RandomData("poisson","poisson100k",100000,"csv");
        System.out.println(data.generate());

        //RandomData data1 = new RandomData("uniform","data2",100,"db");
        //System.out.println(data1.generate());
    }
}

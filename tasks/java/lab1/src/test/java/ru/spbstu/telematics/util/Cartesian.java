package ru.spbstu.telematics.util;

public class Cartesian {

    public static Object[][] cartesianProduct(Object[] set1, Object[] set2) {
        Object[][] res = new Object[set1.length * set2.length][2]; //2 for pairs
        for (int indexLeft = 0; indexLeft < set1.length; indexLeft++) {
            for (int indexRight = 0; indexRight < set2.length; indexRight++) {
                Object[] re = res[indexLeft * set2.length + indexRight];
                re[0] = set1[indexLeft];
                re[1] = set2[indexRight];
            }
        }
        for (Object[] pair : res) {
            System.out.println(pair[0].toString() + " : " + pair[1].toString());
        }
        return res;
    }

}

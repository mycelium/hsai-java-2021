package ru.spbstu.telematics.utils;

import ru.spbstu.telematics.dao.DAO;

import java.util.Random;


public class DAOStub implements DAO {

    int n = 70000;

    @Override
    public double[] getColumn(int i) {
        Random random = new Random();
        double[] res = new double[n];
        for (int j = 0; j < res.length; j++) {
            res[j] = random.nextGaussian();
        }
        return res;
    }

    @Override
    public String[] getNames() {
        return new String[] {"var1", "var2", "var3", "var4", "var5", "var6", "var7", "var8"};
    }

    @Override
    public double[][] getAll() {
        double[][] res = new double[n][8];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 8; j++) {
                res[i][j] = random.nextGaussian();
            }
        }
        return res;
    }

    @Override
    public String getTitle() {
        return "Title";
    }
}

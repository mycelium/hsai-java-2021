package ru.spbstu.telematics.dao;

public interface DAO {
    double[] getColumn(int i);
    String[] getNames();
    double[][] getAll();
    String getTitle();
}

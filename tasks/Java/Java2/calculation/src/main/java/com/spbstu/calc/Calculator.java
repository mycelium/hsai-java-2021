package com.spbstu.calc;

import com.spbstu.data.TableData;

import java.util.ArrayList;

public class Calculator implements com.spbstu.data.Calculator {
    @Override
    public ArrayList<Double> calculate(TableData tableData) {
        return calculateAll(tableData,0);
    }

    public ArrayList<Double> calculateAll(TableData tableData, int column) {
        ArrayList<Double> calculatingColumn = tableData.getColumn(column);
        ArrayList<Double> data = new ArrayList<>();
        data.add(calculateMean(tableData, column));
        data.add(calculateMedian(tableData, column));
        data.add(calculateMin(tableData, column));
        data.add(calculateMax(tableData, column));
        return data;
    }

    public Double calculateMean(TableData tableData, int column) {
        ArrayList<Double> calculatingColumn = tableData.getColumn(column);
        Double mean = 0.0;
        for (Double d : calculatingColumn) {
            mean += d;
        }
        return mean;
    }

    public Double calculateMedian(TableData tableData, int column) {
        ArrayList<Double> calculatingColumn = tableData.getColumn(column);
        Double median;
        if (calculatingColumn.size() % 2 == 0) {
            median = (calculatingColumn.get(calculatingColumn.size() / 2) + calculatingColumn.get((calculatingColumn.size() + 1) / 2)) / 2;
        } else {
            median = calculatingColumn.get((calculatingColumn.size() + 1) / 2);
        }
        return median;
    }

    public Double calculateMin(TableData tableData, int column) {
        ArrayList<Double> calculatingColumn = tableData.getColumn(column);
        Double min = Double.MAX_VALUE;
        for (Double d : calculatingColumn) {
            if (min > d) {
                min = d;
            }
        }
        return min;
    }

    public Double calculateMax(TableData tableData, int column) {
        ArrayList<Double> calculatingColumn = tableData.getColumn(column);
        Double max = Double.MIN_NORMAL;
        for (Double d : calculatingColumn) {
            if (max < d) {
                max = d;
            }
        }
        return max;
    }


}

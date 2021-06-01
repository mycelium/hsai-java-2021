package com.spbstu.reader;

import com.spbstu.data.RowData;
import com.spbstu.data.TableData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVReader implements com.spbstu.data.Reader {
    public CSVReader(String filename) {
        this.filename = filename;
    }

    String filename;

    public TableData reader() throws IOException {
        Reader reader = new FileReader(filename + ".csv");
        CSVParser csvRecords = new CSVParser(reader, CSVFormat.DEFAULT);
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<RowData> table = new ArrayList<>();
        List<CSVRecord> records = csvRecords.getRecords();
        Iterator<CSVRecord> iterator = records.iterator();
        CSVRecord header = iterator.next();
        for (String d : header) {
            nameList.add(d);
        }

        while (iterator.hasNext()) {
            CSVRecord row = iterator.next();
            RowData rowData = new RowData();
            ArrayList<Double> listRowData = new ArrayList<>();
            for (String s : row) {
                listRowData.add(Double.parseDouble(s));
            }
            rowData.setRow(listRowData);
            table.add(rowData);
        }
        TableData tableData = new TableData(nameList, table);
        return tableData;
    }

    @Override
    public TableData read() {
        try {
            Reader reader = new FileReader(filename + ".csv");
            CSVParser csvRecords = new CSVParser(reader, CSVFormat.DEFAULT);
            ArrayList<String> nameList = new ArrayList<>();
            ArrayList<RowData> table = new ArrayList<>();
            List<CSVRecord> records = csvRecords.getRecords();
            Iterator<CSVRecord> iterator = records.iterator();
            CSVRecord header = iterator.next();
            for (String d : header) {
                nameList.add(d);
            }

            while (iterator.hasNext()) {
                CSVRecord row = iterator.next();
                RowData rowData = new RowData();
                ArrayList<Double> listRowData = new ArrayList<>();
                for (String s : row) {
                    listRowData.add(Double.parseDouble(s));
                }
                rowData.setRow(listRowData);
                table.add(rowData);
            }
            TableData tableData = new TableData(nameList, table);
            return tableData;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

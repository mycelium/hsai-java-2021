package com.spbstu.data;

import java.util.ArrayList;

public class TableData {
    ArrayList<String> columnName;

    public ArrayList<RowData> getTable() {
        return table;
    }

    ArrayList<RowData> table;

    public TableData(ArrayList<String> columnName, ArrayList<RowData> table) {
        this.columnName = columnName;
        this.table = table;
    }
    public ArrayList<Double> getColumn(int num){
        ArrayList<Double> list=new ArrayList<>();
        for(RowData rowData: table){
            for(int i=0;i<rowData.getRowSize();i++){
                if(i==num){
                    list.add(rowData.getRow().get(i));
                }
            }
        }
        return list;
    }


}

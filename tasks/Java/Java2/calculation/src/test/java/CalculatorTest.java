import com.spbstu.calc.Calculator;
import com.spbstu.data.RowData;
import com.spbstu.data.TableData;
import com.spbstu.reader.CSVReader;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CalculatorTest {
    @Test
    public void CalculateTest(){
        ArrayList<Double> listOut = new ArrayList<>();
        ArrayList<RowData> rowList=new ArrayList<>();
        for(int i=0;i<8;i++){
            ArrayList<Double> timeList = new ArrayList<>();
            RowData rowData = new RowData();
            timeList.add(Double.valueOf(i));
            rowData.setRow(timeList);
            rowList.add(rowData);
            listOut.add(Double.valueOf(i));
        }
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("value");
        TableData tableData = new TableData(nameList, rowList);
        Calculator calculator=new Calculator();
        ArrayList<Double> calculatedData = calculator.calculate(tableData);
        ArrayList<Double> neededTable = new ArrayList<>();
        ArrayList<Double> calculatingColumn = tableData.getColumn(0);
        Double mean = 0.0;
        for (Double d : calculatingColumn) {
            mean += d;
        }
        neededTable.add(mean);
        Double median;
        if (calculatingColumn.size() % 2 == 0) {
            median = (calculatingColumn.get(calculatingColumn.size() / 2) + calculatingColumn.get((calculatingColumn.size() + 1) / 2)) / 2;
        } else {
            median = calculatingColumn.get((calculatingColumn.size() + 1) / 2);
        }
        neededTable.add(median);
        Double min = Double.MAX_VALUE;
        for (Double d : calculatingColumn) {
            if (min > d) {
                min = d;
            }
        }
        neededTable.add(min);
        Double max = Double.MIN_NORMAL;
        for (Double d : calculatingColumn) {
            if (max < d) {
                max = d;
            }
        }
        neededTable.add(max);
        Assert.assertTrue(neededTable.equals(calculatedData));
    }
}

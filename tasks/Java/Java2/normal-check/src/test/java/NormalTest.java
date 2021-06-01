import com.spbstu.data.RowData;
import com.spbstu.data.TableData;
import com.spbstu.normal.NormalCalculator;
import org.junit.Test;

import java.util.ArrayList;

public class NormalTest {
    @Test
    public void NormTest(){
        NormalCalculator normalCalculator=new NormalCalculator();
        ArrayList<RowData> rowList=new ArrayList<>();
        for(int i=0;i<1000;i++){
            ArrayList<Double> timeList = new ArrayList<>();
            RowData rowData = new RowData();
            timeList.add(Double.valueOf(i));
            rowData.setRow(timeList);
            rowList.add(rowData);
        }
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("value");
        TableData tableData = new TableData(nameList, rowList);
        System.out.println(normalCalculator.calculate(tableData));
    }
}

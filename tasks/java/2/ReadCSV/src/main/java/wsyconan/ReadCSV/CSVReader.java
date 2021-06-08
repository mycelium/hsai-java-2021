package wsyconan.ReadCSV;

import java.io.*;
import java.util.ArrayList;

public class CSVReader {
    String filePath;
    File file;

    public CSVReader(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Double> readFile() {
        ArrayList<Double> arrayList = new ArrayList<>();
        try {
            file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String[] array = br.readLine().split(",");
            for (String str : array) {
                arrayList.add(Double.valueOf(str));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}

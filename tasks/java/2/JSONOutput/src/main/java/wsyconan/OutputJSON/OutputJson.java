package wsyconan.OutputJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutputJson {
    Output output;
    Gson gson;
    String path;
    String fileName;

    public OutputJson(double min, double max, double median, boolean normality,
                      double average, String filename, int dataSize, String path) {
        output = new Output(min, max, median, normality,
                average, filename, dataSize);
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.path = path;
    }

    public void write() {
        String json = gson.toJson(output);
        File file = new File(path + "/" + output.filename + ".json");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {

        }

    }

    class Output {
        String filename;
        int dataSize;
        double min, max, median, average;
        boolean normality;

        public Output(double min, double max, double median, boolean normality,
                      double average, String filename, int dataSize) {
            this.min = min;
            this.max = max;
            this.median = median;
            this.average = average;
            this.filename = filename.substring(0, filename.lastIndexOf("."));;
            this.dataSize = dataSize;
            this.normality = normality;
        }
    }

}

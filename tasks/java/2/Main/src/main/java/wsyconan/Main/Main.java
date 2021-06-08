package wsyconan.Main;

import wsyconan.NormalityTest.NormTest;
import wsyconan.OutputImage.OutputImage;
import wsyconan.OutputJSON.OutputJson;
import wsyconan.ReadCSV.CSVReader;
import wsyconan.ReadSQLite.DBReader;
import wsyconan.Statistcs.Statistics;
import wsyconan.log.MyLogger;

import java.util.ArrayList;


public class Main {
    String filePath;
    String outputPath;
    String fileName;
    ArrayList<Double> data;
    CSVReader csvReader;
    DBReader dbReader;
    Statistics analyzer;
    MyLogger myLogger;
    NormTest normTester;
    OutputJson outputJson;
    OutputImage outputImage;

    double max, min, median, average;
    boolean normality;

    public Main(String filePath, String outputPath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.outputPath = outputPath;
        myLogger = new MyLogger();
    }

    public void run() {
        // Read data
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        if (extension.equals("csv")) {
            csvReader = new CSVReader(filePath + fileName);
            data = csvReader.readFile();
        } else {
            dbReader = new DBReader(filePath + fileName);
            data = dbReader.readFile();
        }
        myLogger.info("Read data successfully.");
        // Analyze data
        analyzer = new Statistics(data);
        normTester = new NormTest(data);
        analyze();
        myLogger.info("Analyze data successfully");
        // Output data
        output();
        myLogger.info("Output successfully");
    }

    private void analyze() {
        this.max = analyzer.getMax();
        this.min = analyzer.getMin();
        this.average = analyzer.getAverage();
        this.median = analyzer.getMedian();
        normality = normTester.analyze();
    }

    private void output() {
        outputJson = new OutputJson(min, max, median, normality, average, fileName, data.size(), outputPath);
        outputJson.write();
        outputImage = new OutputImage(data, outputPath, fileName);
        outputImage.draw();
    }
}

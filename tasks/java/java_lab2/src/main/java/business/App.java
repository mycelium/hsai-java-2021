package business;

import input.*;
import output.*;
import stats.*;

import java.io.IOException;
import java.util.List;

import static stats.StatsAnalyzer.makePresenter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // Добавить какой-нибудь файл CSV input.csv с двумя колоннами и 100 столбцами
        CSVReader rdr = new CSVReader("input.csv");
        List<Variable<Double>> data = null;
        try {
            data = rdr.readAllDistribution();
        } catch (IOException e) {
            e.printStackTrace();
        }

        double[][] vals = new double[100][2];
        for (int i = 0; i < data.size(); i++) {
            Variable<Double> val = data.get(i);

            for (int j = 0; j < data.size(); j++) {
                vals[i][j] = val.get(j);
            }
        }

        System.out.println("0 var: " + StatsChecker.checkChiSquare(vals, 0));
        System.out.println("1 var: " + StatsChecker.checkChiSquare(vals, 1));

        ResultsPresenter rp = makePresenter(vals, 0);
        JsonOutputter.output("results.json", rp);

        GraphicsOutputter.output("go.png", vals, 0);
    }
}

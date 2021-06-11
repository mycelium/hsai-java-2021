package stats;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;

public class StatsChecker {
    final static double ALPHA = 0.05;

    public static boolean checkChiSquare(double[][] vals, int numVar) {
        // проверяет, что по компоненте numVar имеем нормальное распределение
        try {
            StatsAnalyzer.checkParams(vals, numVar);
        }
        catch (IndexOutOfBoundsException e) {
            throw e;
        }
        catch (Exception e) {
            // если значений нет, то условие нормальности выполняется
            return true;
        }

        double m = StatsAnalyzer.average(vals, numVar);
        double sigma = getAverageSquares(vals, numVar) - m * m;

        return checkChiSquare(vals, numVar, ALPHA, m, sigma);
    }

    private static double getAverageSquares(double[][] vals, int numVar) {
        double res = 0;
        for (double[] vectorVals: vals)
            res += vectorVals[numVar] * vectorVals[numVar];

        return res / vals.length;
    }

    private static boolean checkChiSquare(double[][] vals, int numVar, double alpha, double m, double sigma) {
        // Проверяет гипотезу хи-квадрат о нормальности распределения с параметрами m, sigma, уровень значимости -- alpha
        int k = (int) Math.floor(1 + 3.3 * Math.log10(vals.length)); // взяли целую часть

        ArrayList<Pair<Double, Double>> deltas = makeDeltas(k, StatsAnalyzer.min(vals, numVar), StatsAnalyzer.max(vals, numVar)); // интервалы
        NormalDistribution theoryFunction = new NormalDistribution(m, sigma); // теоретическая функция
        double chiVals = 0; // значение хи-квадрат для выборки

        for (Pair<Double, Double> delta : deltas) {
            int ni = countNi(vals, numVar, delta); // кол-во попаданий элементов выборки в интервал
            double npi = vals.length * theoryFunction.probability(delta.getFirst(), delta.getSecond()); // теоретическое кол-во попаданий элементов выборки в интервал

            chiVals += (ni - npi) * (ni - npi) / npi;
        }

        // теперь сравним chiVals и критическое значение chi2
        ChiSquaredDistribution chiTheor = new ChiSquaredDistribution(k - 1);
        return chiVals < chiTheor.inverseCumulativeProbability(alpha);
    }

    private static int countNi(double[][] vals, int numVar, Pair<Double, Double> delta) {
        int count = 0;
        for (double[] vect: vals)
            if (delta.getFirst() < vect[numVar] && vect[numVar] <= delta.getSecond())
                count++;

        return count;
    }

    private static ArrayList<Pair<Double, Double>> makeDeltas(int k, double min, double max) {
        // возвращает список k интервалов, составляющих разбиение вещественной оси
        ArrayList<Pair<Double, Double>> res = new ArrayList<Pair<Double, Double>>();

        double delta = (max - min) / (k - 3);
        res.add(new Pair<Double, Double>(Double.NEGATIVE_INFINITY, min));

        double t = min;
        for (int i = 0; i < k - 2; i++) {
            res.add(new Pair<Double, Double>(t, t + delta));
            t += delta;
        }

        res.add(new Pair<Double, Double>(max, Double.POSITIVE_INFINITY));

        return res;
    }


}

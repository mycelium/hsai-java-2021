package stats;

import java.util.function.BiFunction;

public class StatsAnalyzer {

    static void checkParams(double[][] vals, int numVar) throws Exception {
        if (vals.length == 0)
            throw new Exception("Empty vals");
        else if (vals[0].length < numVar)
            throw new IndexOutOfBoundsException("Incorrect n param: " + numVar);
    }

    public static double average(double[][] vals, int numVar) {
        try {
            checkParams(vals, numVar);
        }
        catch (IndexOutOfBoundsException e) {
            throw e;
        }
        catch (Exception e) {
            return 0;
        }

        double res = 0;
        for (double[] vectorVal: vals)
            res += vectorVal[numVar];

        return res / vals.length;
    }

    public static double median(double[][] vals, int numVar) {
        // нахождение медианы без сортировки массива (почти)
        try {
            checkParams(vals, numVar);
        }
        catch (IndexOutOfBoundsException e) {
            throw e;
        }
        catch (Exception e) {
            return 0;
        }

        int index = 0;
        int left = 0;
        int right = vals.length;
        int mid = (left+right)/2;

        index = partition(vals, left, right, numVar);
        while (index != mid) {
            if (index < mid)
                index = partition(vals, mid, right, numVar);
            else
                index = partition(vals, left, mid, numVar);
        }
        return vals[index][numVar];
    }

    private static int partition(double[][] a, int i, int j, int numVar ){
        int pivot = (i+j) / 2;
        double temp;
        while (i <= j) {
            while (a[i][numVar] < a[pivot][numVar])
                i++;
            while (a[j][numVar] > a[pivot][numVar])
                j--;
            if (i <= j){
                temp = a[i][numVar];
                a[i][numVar] = a [j][numVar];
                a[j][numVar] = temp;
                i++;
                j--;
            }
        }
        return pivot;
    }

    public static double min(double[][] vals, int numVar) {
        return range(vals, numVar,
                (Double a, Double b) -> { return b < a; },
                Double.NEGATIVE_INFINITY);
    }

    public static double max(double[][] vals, int numVar) {
        return range(vals, numVar,
                (Double a, Double b) -> { return b > a; },
                Double.POSITIVE_INFINITY);
    }

    private static double range(double[][] vals, int numVar, BiFunction<Double, Double, Boolean> ranger, double emptyError) {
        // ranger -- отношение порядка на множестве double
        try {
            checkParams(vals, numVar);
        }
        catch (IndexOutOfBoundsException e) {
            throw e;
        }
        catch (Exception e) {
            return emptyError;
        }

        double res = vals[0][numVar];
        for (double[] vectorVars: vals)
            if (ranger.apply(vectorVars[numVar], res))
                res = vectorVars[numVar];

        return res;
    }

    public static ResultsPresenter makePresenter(double[][] vals, int numVar) {
        return new ResultsPresenter(average(vals, numVar), median(vals, numVar), min(vals, numVar), max(vals, numVar));
    }
}

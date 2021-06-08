package wsyconan.Statistcs;

import java.util.ArrayList;
import java.util.Collections;

public class Statistics {
    ArrayList<Double> data;

    public Statistics(ArrayList<Double> data){
        this.data = data;
    }

    public Double getMin() {
        return Collections.min(data);
    }

    public Double getMax() {
        return Collections.max(data);
    }

    public Double getMedian() {
        Collections.sort(data);
        int size = data.size();
        if (size % 2 == 1) {
            return data.get((size - 1) / 2);
        } else {
            return (data.get(size / 2 - 1) + data.get(size / 2)) / 2.0;
        }
    }

    public Double getAverage() {
        double sum = 0.0;
        for (Double datum : data) {
            sum += datum;
        }
        return sum / data.size();
    }

}

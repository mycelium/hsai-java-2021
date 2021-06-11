package stats;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "average",
        "median",
        "min",
        "max"
})
public class ResultsPresenter {
    Double aver;
    Double med;
    Double min;
    Double max;

    ResultsPresenter(Double av, Double mmed, Double mmin, Double mmax) {
        aver = av;
        med = mmed;
        min = mmin;
        max = mmax;
    }

    @JsonGetter("values_aver")
    public Double getAver() {
        return aver;
    }

    @JsonGetter("values_med")
    public Double getMed() {
        return med;
    }

    @JsonGetter("values_min")
    public Double getMin() {
        return min;
    }

    @JsonGetter("values_max")
    public Double getMax() {
        return max;
    }
}

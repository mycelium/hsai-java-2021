package wsyconan.NormalityTest;

import java.util.ArrayList;

/**
 * The normality test algorithm comes from: http://www.cnki.com.cn/Article/CJFDTotal-HJDZ199002013.htm
 **/
public class NormTest {
    ArrayList<Double> data;
    // Confidence intervals: +-0.5
    double Ua = 1.9599639845400538;

    public NormTest(ArrayList<Double> data) {
        this.data = data;
    }

    public boolean analyze() {
        int i;
        double skewness, kurtosis;  //偏度系数和峰度系数
        double expectedValue;  //平均值
        double squareDeviation;  //方差
        double standardDeviation;  //标准差
        double sum = 0.0, cm2, cm3, cm4, m3, m4, tmp, tmp2;
        for (i = 0; i < data.size(); ++i) {
            sum += data.get(i);
        }
        expectedValue = sum / data.size();
        cm2 = 0.0;
        cm3 = 0.0;
        cm4 = 0.0;
        for (i = 0; i < data.size(); ++i) {
            tmp = data.get(i) - expectedValue;
            tmp2 = tmp * tmp;
            cm2 += tmp2;
            cm3 += tmp2 * tmp;
            cm4 += tmp2 * tmp2;
        }
        squareDeviation = cm2 / data.size();
        m3 = cm3 / data.size();
        m4 = cm4 / data.size();
        standardDeviation = Math.sqrt(squareDeviation);
        skewness = m3 / (squareDeviation * standardDeviation);
        kurtosis = m4 / (squareDeviation * squareDeviation) - 3;
        return Math.abs(skewness) <= Ua * Math.sqrt(6.0 / data.size()) &&
                Math.abs(kurtosis) <= Ua * Math.sqrt(24.0 / data.size());
    }
}

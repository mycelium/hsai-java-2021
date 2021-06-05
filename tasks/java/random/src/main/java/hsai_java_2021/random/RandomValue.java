package hsai_java_2021.random;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import hsai_java_2021.random.distribution.Distribution;
import hsai_java_2021.random.distribution.Distribution.DistributionType;
import hsai_java_2021.random.distribution.NormalDistribution;
import hsai_java_2021.random.distribution.PoissonDistribution;
import hsai_java_2021.random.distribution.UniformDistribution;

public class RandomValue {
    private String name;
    private DistributionType type;
    private Distribution distribution;
    private ArrayList<Double> sample;

    public RandomValue(String name, DistributionType type) {
        this.name = name;
        this.type = type;
        this.sample = new ArrayList<Double>();

        try {
            InputStream istream = new FileInputStream("src/main/resources/distribution.properties");
            Properties distribProp = new Properties();
            distribProp.load(istream);

            switch (type) {
                case UNIFORM:
                    double a = Double.parseDouble(distribProp.getProperty("uniform.a"));
                    double b = Double.parseDouble(distribProp.getProperty("uniform.b"));
                    distribution = new UniformDistribution(a, b);
                    break;
                case NORMAL:
                    double mu = Double.parseDouble(distribProp.getProperty("normal.mu"));
                    double sigma = Double.parseDouble(distribProp.getProperty("normal.sigma"));
                    distribution = new NormalDistribution(mu, sigma);
                    break;
                case POISSON:
                    int lambda = Integer.parseInt(distribProp.getProperty("poisson.lambda"));
                    distribution = new PoissonDistribution(lambda);
                    break;
                default:
                    break;
            }
        }
        catch (IOException e) {
            System.err.println("Failed to open Distribution Properties file");
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public DistributionType getType() {
        return type;
    }

    public ArrayList<Double> getSample() {
        return sample;
    }

    public ArrayList<Double> generateSample(int size) {
        sample = distribution.randomSample(size);
        return sample;
    }
}

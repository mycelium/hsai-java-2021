import java.util.ArrayList;

public class Distribution {
    public enum distribution_en{
        Normal,
        Poisson,
        Uniform
    }

    static private double RandVal(double min, double max)
    {
        return (max - min) * Math.random() + min;
    }

    static private double NormalDistributionValue(double min, double max, double m, double l)
    {
        return l * RandVal(min, max) + m;
    }

    static private double PoissonDistributionValue(double min, double max, double lambda)
    {
        double s = min;
        int i = -1;

        do
        {
            s += -Math.log(Math.random());
            i++;
        } while (s < lambda);

        return i;
    }

    static private double UniformDistributionValue(double a, double b)
    {
        return (b - a) * Math.random() + a;
    }

    static public double GetRandomVal(distribution_en distType, double min, double max, double a, double b)
    {
        switch (distType)
        {
            case Poisson:
                return PoissonDistributionValue(min, max, a);
            case Normal:
                return NormalDistributionValue(min, max, a, b);
            case Uniform:
                return UniformDistributionValue(min, max);
        }

        return 0;
    }
    
    static public ArrayList<Double> Generate(double min, double max, int count, distribution_en type, double a, double b)
    {
        ArrayList<Double> res = new ArrayList<Double>();

        for(int i = 0; i < count; i++)
        {
            res.add(GetRandomVal(type, min, max, a, b));
        }

        return res;
    }
}


package ru.spbstu.telematics.distributions;

public class Poisson extends Distribution<Integer> {

    private Integer mean;

    public Poisson(Integer mean) {
        this.mean = mean;
    }

    @Override
    public Integer getRandom() {
        double p = Math.exp(-mean);
        int x = 0;
        double r = random.nextDouble();
        double a = r;
        while (!(a < p)) {
            x++;
            a *= r;
        }
        return x;
    }

}

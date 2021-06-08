import java.io.Console;

public class Main {

    public static void main(String[] args)
    {
        var arr = Distribution.Generate(0, 5, 15, Distribution.distribution_en.Uniform, 1, 1);
        System.out.println(arr);
        DataSaving.Save("test.csv", DataSaving.DataType.CSV, arr);
    }

}

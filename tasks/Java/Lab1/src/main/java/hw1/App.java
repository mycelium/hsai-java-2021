package hw1;

import hw1.storage.CSV;
import hw1.storage.Database;

import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException {
        Сontroller controller = new Сontroller(10);
        System.out.println(controller.create("normal 2 3 uniform 5 3 poisson 8 DBF"));
    }
}

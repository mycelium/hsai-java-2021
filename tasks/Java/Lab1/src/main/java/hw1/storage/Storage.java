package hw1.storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface Storage {
    String storage(HashMap<String, ArrayList<Double>> dataTable) throws IOException;
}

package com.spbstu.output;

import java.io.IOException;
import java.util.ArrayList;

public interface Saving {
    String save(ArrayList<Double> list) throws IOException;
}

package output;

import com.fasterxml.jackson.databind.ObjectMapper;
import stats.ResultsPresenter;

import java.io.FileOutputStream;
import java.io.IOException;

public class JsonOutputter {
    public static void output(String fname, ResultsPresenter rp) {
        try {
            ObjectMapper om = new ObjectMapper();
            new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(new FileOutputStream(fname), rp);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

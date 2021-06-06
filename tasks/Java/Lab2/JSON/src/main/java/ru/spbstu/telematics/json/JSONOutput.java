package ru.spbstu.telematics.json;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.spbstu.telematics.dao.DAO;
import ru.spbstu.telematics.Characteristics;

import java.io.*;

public class JSONOutput {
    private final DAO dao;
    static Logger logger = LogManager.getLogger(JSONOutput.class);

    public JSONOutput(DAO dao) {
        this.dao = dao;
    }

    String JSONString() {
        String[] names = dao.getNames();
        logger.info("Generating JSON string for table " + dao.getTitle() + " with " + names.length + " variables...");
        StringBuilder res = new StringBuilder("{\"title\":\""+ dao.getTitle() + "\",\"variables\": [\n");
        Gson gson = new Gson();
        for (int i = 0; i < names.length; i++) {
            double[] values = dao.getColumn(i);
            Characteristics ch = new Characteristics(values, names[i]);
            res.append(gson.toJson(ch));
            if (i < names.length - 1) res.append(",");
            res.append("\n");
        }
        logger.info("Successfully converted into JSON string.");
        return res.append("]}").toString();
    }

    public void toFile(String filename) {
        try(FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(JSONString());
        } catch (FileNotFoundException e) {
            logger.error("File not found: " + filename);
        } catch (IOException e) {
            logger.error("Error with writing " + filename + " file.");
        }
        logger.info("Successfully saved in " + filename + ".");
    }
}

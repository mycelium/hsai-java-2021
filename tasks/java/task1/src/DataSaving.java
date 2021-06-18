import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataSaving {
    public enum DataType
    {
        CSV,
        Database
    }

    static private void SaveAsCsv(String filename, ArrayList<Double> data)
    {
        Path filepath = Paths.get(filename);
        try(FileWriter fw = new FileWriter(filepath.toFile()))
        {
            for (Double var: data)
            {
                fw.write(var.toString() + '\n');
            }
            fw.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    static private void SaveAsDB(String filename, ArrayList<Double> data)
    {
        Path filedir = Paths.get(filename);
    }

    static public void Save(String filename, DataType savingType, ArrayList<Double> data)
    {
        if(data == null)
            return;

        switch (savingType)
        {
            case Database:
                SaveAsDB(filename, data);
            case CSV:
                SaveAsCsv(filename, data);
            default:
                break;
        }
    }

}

package wsyconan.ReadSQLite;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBReader {
    Connection connection = null;
    Statement statement = null;
    String filePath;
    File file;

    public DBReader(String filePath){
        this.filePath = filePath;
    }

    public ArrayList<Double> readFile(){
        ArrayList<Double>arrayList = new ArrayList<>();
        String sql = "Select * from DATA";
        try {
            file = new File(filePath);
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + filePath);

        } catch (Exception e){
            System.out.println("Open database failed.");
        }
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println(resultSet.getRow());
            while ( resultSet.next() ) {
                arrayList.add(resultSet.getDouble("value"));
            }
        }catch (Exception e){
            System.out.println("Read database failed.");
        }
        return arrayList;
    }
}

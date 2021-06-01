import com.spbstu.data.TableData;
import com.spbstu.db.DBReader;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DBReaderTest {

    @Test
    public void ReaderTest() throws IOException, ClassNotFoundException, SQLException {
        String myDriver = "org.h2.Driver";
        String myUrl = "jdbc:h2:mem:db1";
        Class.forName(myDriver);

        try (Connection connection = DriverManager.getConnection(myUrl, "root", "")) {
            String query = "create table data(value FLOAT)";
            Statement st = connection.createStatement();
            st.execute(query);
            PreparedStatement insert = connection.prepareStatement("insert into data (value) values (?)");
            for(int i=0;i<100;i++){
                insert.setDouble(1, (double) (0.0+i));
                insert.executeUpdate();
            }
            DBReader dbReader = new DBReader(myUrl);
            dbReader.read();
            ArrayList<Double> listOut = new ArrayList<>();
            for(int i=0;i<100;i++){
                listOut.add(Double.valueOf(i));
            }
            TableData gotTable = dbReader.read();
            Assert.assertTrue(listOut.equals(gotTable.getColumn(0)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}

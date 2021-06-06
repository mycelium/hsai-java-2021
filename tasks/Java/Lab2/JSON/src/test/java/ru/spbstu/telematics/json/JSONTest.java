package ru.spbstu.telematics.json;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import ru.spbstu.telematics.utils.DAOStub;

public class JSONTest
{
    @Test
    public void JSONStringTest()
    {
        JSONOutput jsonOutput = new JSONOutput(new DAOStub());
        System.out.println(jsonOutput.JSONString());
    }

    @Test
    public void JSONFileTest() {
        JSONOutput jsonOutput = new JSONOutput(new DAOStub());
        jsonOutput.toFile("test.json");
    }
}

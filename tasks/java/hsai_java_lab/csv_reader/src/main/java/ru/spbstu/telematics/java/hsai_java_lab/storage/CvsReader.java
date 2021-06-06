package ru.spbstu.telematics.java.hsai_java_lab.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValueSample;

public class CvsReader {
    private String filePath;
    private ArrayList<RandomValueSample> samples;

    /**
     * Creates new instance of the CVS reader class
     * 
     * @param path Path to CVS file with random values samples
     * @throws NullPointerException if {@code path} is {@code null}
     * @throws 
     */
    public CvsReader(String path)
        throws NullPointerException, FileNotFoundException, IOException
    {
        if (path == null) {
            throw new NullPointerException("File path is null");
        }
        else {
            filePath = path;
        }

        File fin = new File(filePath);

        if (!fin.exists()) {
            throw new FileNotFoundException("File " + filePath + " not found");
        }

        BufferedReader finReader = new BufferedReader(new FileReader(fin));

        try {
            String valueNamesString = finReader.readLine();
            String[] valueNames = valueNamesString.split(",");
        }
        catch (IOException e) {
            throw e;
        }
        
    }
}

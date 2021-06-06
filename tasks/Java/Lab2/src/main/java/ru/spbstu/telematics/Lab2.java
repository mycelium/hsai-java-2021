package ru.spbstu.telematics;

import ru.spbstu.telematics.dao.DAO;
import ru.spbstu.telematics.dao.csv.CSVDAO;
import ru.spbstu.telematics.dao.db.DBDAO;
import ru.spbstu.telematics.dao.db.mysqlDAO;
import ru.spbstu.telematics.graphics.Plotter;
import ru.spbstu.telematics.json.JSONOutput;
import ru.spbstu.telematics.normalcheking.NormalChecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Lab2
{
    static Logger logger = LogManager.getLogger(Lab2.class);

    /**
     * Формат: (--cvs <файл> | --db) (--norm | --json | --plot | --hist) <номера переменных>
     */
    public static void main(String[] args) {
        logger.info("Start to parse args...");
        ArgParser argParser;
        try {
            argParser = new ArgParser(args);
        } catch (IllegalArgumentException exception) {
            logger.error("Error with parsing: " + exception.getMessage());
            return;
        }
        DAO dao;
        if (argParser.source.equals(ArgParser.Source.CSV)) {
            try {
                dao = new CSVDAO(argParser.filename);
            } catch (IOException e) {
                logger.error("Error: " + e.getMessage());
                return;
            }
        } else {
            System.out.println("Enter address of database, name of database, table, login and password splitted by space: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input;
            try {
                input = reader.readLine();
                String[] ins = input.split("\\s");
                DBDAO t = new mysqlDAO();
                t.setAddress(ins[0]).setDatabase(ins[1]).setTable(ins[2]).login(ins[3], ins[4]);
                dao = t;
            } catch (IOException e) {
                logger.error("Error: " + e.getMessage());
                return;
            }
        }
        if (argParser.action.equals(ArgParser.Action.NORMAL)) {
            boolean res = NormalChecker.isNormal(dao.getColumn(argParser.column));
            if (res) System.out.println("Values are normal");
            else System.out.println("Values are not normal");
        } else if (argParser.action.equals(ArgParser.Action.JSON)) {
            System.out.println("Enter filename for json file: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input;
            try {
                input = reader.readLine();
            } catch (IOException e) {
                logger.error("Error: " + e.getMessage());
                return;
            }
            JSONOutput json = new JSONOutput(dao);
            json.toFile(input);
        } else {
            Plotter plotter = new Plotter(dao);
            if (argParser.action.equals(ArgParser.Action.PLOT)) {
                plotter.showGraph(argParser.column);
            } else {
                plotter.showHistogram(argParser.column);
            }
        }
        logger.info("Everything is fine");
    }
}

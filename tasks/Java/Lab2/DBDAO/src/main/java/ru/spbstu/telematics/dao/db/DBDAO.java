package ru.spbstu.telematics.dao.db;

import ru.spbstu.telematics.dao.DAO;

public interface DBDAO extends DAO {
    DBDAO setAddress(String address);
    DBDAO setDatabase(String database);
    DBDAO setTable(String table);
    DBDAO login(String username, String password);
    void disconnect();
}

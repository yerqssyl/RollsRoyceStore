package com.company;

import java.sql.*;

public interface IntDB {
    void getConnection() throws SQLException, ClassNotFoundException;// to connect DB
}

package com.company;

import java.sql.*;

public class SqlDB implements IntDB{
    @Override
    public void getConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "8223147121";// connect with Postgre
        try {
            Class.forName("or" + "g.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement statement = con.createStatement();
            System.out.println("Connection done!");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
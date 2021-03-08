package com.company;

public class Main {
    public static void  main (String[] args) {
        IntDB db = new SqlDB();
        IntOrderArchive repo = new OrderArchive(db);
        OrderControl controller = new OrderControl(repo);
        DBW app = new DBW(controller);
        app.start();
    }
}

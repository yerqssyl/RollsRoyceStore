package com.company;

import java.sql.*;

public class OrderArchive implements IntOrderArchive {
    private final IntDB db;

    public OrderArchive(IntDB db) {
        this.db = db;
    }

    @Override
    public String getProductList() { // get Product name for option 2
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "82231471";// connect with Postgre
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "select * from rr";//to see all products
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while ((rs.next())) {//finding and enter product information
                System.out.println(rs.getInt("id") + " " + rs.getString("model") + " " + rs.getInt("price"));
            }
        } catch (SQLException throwables) {//error
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public String getColor() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "82231471";// connect with Postgre
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "select * from color";//to see all colors
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while ((rs.next())) {//finding and enter color
                System.out.println(rs.getString("color"));
            }
        } catch (SQLException throwables) {//error
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean createUser(String customer_name, String customer_number){// create USer for option 1
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "82231471";// connect with Postgre
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO customer (customer_name, customer_number) VALUES (?,?)";// sql function to insert information
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, customer_name);// enter in sql table customer name
            st.setString(2, customer_number);// enter in sql table customer phone
            st.executeUpdate();//update sql table
        } catch (SQLException throwables) {//error
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public void purchaseProduct (String customer_name, String customer_number, int id, String color){// to purchase product
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "82231471";// connect with Postgre
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "SELECT ORDER_id FROM customer WHERE customer_name = ? AND customer_number = ? ORDER BY order_id DESC LIMIT 1;";// find customer information and order result
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, customer_name);
            st.setString(2, customer_number);
            ResultSet rs = st.executeQuery();
            if(rs.isBeforeFirst()) {// to fill order_details
                while (rs.next()) {
                    int order_id = rs.getInt(1);
                    String sql2 = "INSERT INTO order_details (order_id, id, color) VALUES (?, ?, ?)";// sql function to insert
                    PreparedStatement st2 = con.prepareStatement(sql2);
                    st2.setInt(1, order_id);//to enter order_id
                    st2.setInt(2, id);//to enter car_id
                    st2.setString(3, color);//entering color
                    st2.execute();
                }
            }
        } catch (SQLException throwables) {//error
            throwables.printStackTrace();
        }
    }

    @Override
    public void getOrderDetails(String customer_name, String customer_number){
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "82231471";// connect with Postgre
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            String sql = "SELECT customer.ORDER_id, order_details.color, rr.model, rr.price FROM customer\n" +
                    "INNER JOIN ORDER_DETAILS ON customer.ORDER_id = ORDER_DETAILS.ORDER_id\n" +
                    "INNER JOIN rr ON ORDER_DETAILS.id = rr.id\n" +
                    "WHERE customer.customer_name = ? AND customer.customer_number = ?;";//using join function to display overall info
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, customer_name);//setting name
            st.setString(2, customer_number);//setting phone
            ResultSet rs = st.executeQuery();
            if(rs.isBeforeFirst()) {
                while (rs.next()) {//finding name
                    System.out.println(rs.getInt("order_id") + ": " + rs.getString("model") + ", " + rs.getInt("price")+" dollars - " + "color: " +rs.getString("color"));
                }
            }
        } catch (SQLException throwables) {//error
            throwables.printStackTrace();
        }
    }
}

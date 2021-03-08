package com.company;

public interface IntOrderArchive { // interface of functions
    String getProductList();
    String getColor();
    boolean createUser(String customer_name, String customer_number);
    void purchaseProduct(String customer_name, String customer_number, int id, String color);
    void getOrderDetails(String customer_name, String customer_number);
}

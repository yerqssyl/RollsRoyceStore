package com.company;


public class OrderControl{
    private final IntOrderArchive repo;

    public OrderControl(IntOrderArchive repo) {
        this.repo = repo;
    }

    public String getProductList(){
        String response = repo.getProductList();
        return (response == null ? "" : response.toString());
    }// getter for Product List
    public String getColor(){
        String response = repo.getColor();
        return (response == null ? "" : response.toString());
    }// getter for color
    public String createUser(String customer_name, String customer_phone){
        boolean created = repo.createUser(customer_name, customer_phone);
        return (created ? "" : "");
    }// to create User
    public void purchaseProduct(String customer_name, String customer_phone, int id, String color){
        repo.purchaseProduct(customer_name, customer_phone, id, color);
    }//to purchase RR
    public void getOrderDetails(String customer_name, String customer_phone){
        repo.getOrderDetails(customer_name, customer_phone);
    }//getter for Order Details
}

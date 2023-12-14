package org.example;


import org.apache.commons.dbcp2.BasicDataSource;

import java.util.Scanner;

public class App
{

    public static String url = "jdbc:mysql://localhost:3306/northwind";
    public static String user = user = "root";
    public static String password = "17b*98An"; // not my real password
    public static Scanner keyboard = new Scanner(System.in);
    public static void main( String[] args )
    {

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        DataManager dataManager = new DataManager(dataSource);
        System.out.println("1) Search for customer order history by ID");
        System.out.println("2) Search for sales by year");
        System.out.println("3) Search for sales by category");

        String userChoice = keyboard.nextLine();
        if(userChoice.equalsIgnoreCase("1")){
            System.out.println("Enter the customer ID: ");
            String id = keyboard.nextLine();
            dataManager.getCustomerOrderHist(id);
        }
        else if(userChoice.equalsIgnoreCase("2")){
            System.out.println("Enter start date: ex. 1996-07-04 : ");
            String startDate = keyboard.nextLine();
            System.out.println("Enter end date: ex. 1996-07-04 : ");
            String endDate = keyboard.nextLine();
            dataManager.getSalesByYear(startDate,endDate);
        }
        else if(userChoice.equalsIgnoreCase("3")){
            System.out.println("Enter the category: ");
            String category = keyboard.nextLine();
            System.out.println("Enter the year: ");
            String year = keyboard.nextLine();
            dataManager.salesByCategory(category,year);
        }

    }
}

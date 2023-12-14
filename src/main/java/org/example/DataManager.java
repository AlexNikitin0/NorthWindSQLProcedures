package org.example;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DataManager {

    private DataSource dataSource;


    public DataManager(BasicDataSource dataSource){
        this.dataSource = dataSource;

    }

    public void getCustomerOrderHist(String id){
        String query = "{CALL CustOrderHist(?)}";
        //create callable statement
        try(
                Connection connection = dataSource.getConnection();
                CallableStatement stmt = connection.prepareCall(query);

                ){
            //set params
            stmt.setString(1,id);
            //execute
            ResultSet resultSet = stmt.executeQuery();
            //process the returned values
            while(resultSet.next()){
                System.out.printf("%s - %d\n",resultSet.getString("ProductName"),resultSet.getInt("TOTAL"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void getSalesByYear(String startDate, String endDate){
        String query = "{CALL Sales_by_Year(?,?)}";
        //create callable statement
        try(
                Connection connection = dataSource.getConnection();
                CallableStatement stmt = connection.prepareCall(query);

        ){
            //set params
            stmt.setString(1,startDate);
            stmt.setString(2,endDate);
            //execute
            ResultSet resultSet = stmt.executeQuery();
            //process the returned values
            while(resultSet.next()){
                System.out.printf("%s - %d\n",resultSet.getTimestamp("ShippedDate"),resultSet.getInt("OrderID"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void salesByCategory(String category, String year){
        String query = "{CALL SalesByCategory(?,?)}";
        //create callable statement
        try(
                Connection connection = dataSource.getConnection();
                CallableStatement stmt = connection.prepareCall(query);

        ){
            //set params
            stmt.setString(1,category);
            stmt.setString(2,year);
            //execute
            ResultSet resultSet = stmt.executeQuery();
            //process the returned values
            while(resultSet.next()){
                System.out.printf("%s - %s\n",resultSet.getString("ProductName"),resultSet.getDouble("TotalPurchase"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

}

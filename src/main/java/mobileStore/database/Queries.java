package mobileStore.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class Queries {
    private Connection conn = null;
    public void doConnection() {
        // com.mysql.jdbc.Driver - this driver is deprecated
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/catalog";
            conn = DriverManager.getConnection(url, "root", "sara");
            runQuery();//do not remove this
        } catch (Exception e) {
            System.err.println(e.getMessage());

        } finally {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    //this methdo will enable the CRUD statement from the db
    public void runQuery(){
        insertData();
        selectData();
        //updateData();
      // deleteData();
    }
    private void insertData() {
        System.out.println("\nInsert data into product");
        try {
            Statement statement = conn.createStatement();
            LocalDate inventoryDate = LocalDate.parse("2020-01-30");
            // no need to fill the id as its value is auto-incremented
            statement.executeUpdate("INSERT INTO product (code, amount, description, created_at)  " +
                    "               VALUES ('Samsung-Galaxy', 15, 'galaxy S10', ' "+ inventoryDate + "')");

        } catch (SQLException e) {
            System.err.println("exception:: " + e.getMessage());
        }
    }
    private  void selectData() {
        System.out.println("\nSelect product from database");
        System.out.println("id\t\t|  code\t\t\t| amount\t\t| description | inventoryDate");
        String query = "SELECT id, code, amount, description, created_at " +
                       "FROM product WHERE disabled=false";
        try {

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String code = resultSet.getString("code");
                double amount = resultSet.getDouble("amount");
                DecimalFormat numberFormat = new DecimalFormat("#.00");
               //  String amountFormat = numberFormat.format(amount);

                String description = resultSet.getString("description");
                String result = resultSet.getString("created_at");

                System.out.println(String.format("%d\t\t %s\t\t %s\t\t %s\t\t %s\t", id, code, numberFormat.format(amount), description,result));
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void updateData() {
        System.out.println("\nUpdate data in product");
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(
                    "UPDATE product " +
                            "SET description='Galaxy S10, now with new and upgraded camera features' " +
                            "WHERE code='Samsung-Galaxy'");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("item has been update" );
    }

    private void deleteData() {
        System.out.println("\nDelete Samsung-Galaxy in product");
        try {

            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM product " +
                                "WHERE code='Samsung-Galaxy'");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}

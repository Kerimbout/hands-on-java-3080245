package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {
  public static Connection conn() {
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection conn = null;
    
    try {
      conn = DriverManager.getConnection(db_file); 
      // System.out.println("Connected Successfully");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return conn;
  }

  public static Customer getCustomer(String username) {
    String sql = "SELECT * FROM customers WHERE username = ?"; //prepared statement
    Customer customer = null;
    try(
      Connection conn = conn();
      PreparedStatement statement = conn.prepareStatement(sql)){
        statement.setString(1, username);
        try(ResultSet resultSet = statement.executeQuery()){
          customer = new Customer(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("username"),
            resultSet.getString("password"),
            resultSet.getInt("account_id"));
        }

      }catch(SQLException e){
        e.printStackTrace();
    }
    return customer;
  }

  public static Account getAccount(int accountId) {
    String sql = "SELECT * FROM accounts WHERE id = ?";
    Account account = null;
    try(
      Connection conn = conn();
      PreparedStatement statement = conn.prepareStatement(sql)){
        statement.setInt(1, accountId);
        try(ResultSet resultSet = statement.executeQuery()){
          account = new Account(
            resultSet.getInt("id"),
            resultSet.getString("type"),
            resultSet.getDouble("balance"));
        }
      }catch(SQLException e){
        e.printStackTrace();
    }
    return account;
  }

  public static void main(String[] args) {
    Customer customer = getCustomer("bpioch15@microsoft.com");
    Account account = getAccount(customer.getAccountId()); 
    System.out.println(customer.getName());
    System.out.println(account.getBalance());
  }
}

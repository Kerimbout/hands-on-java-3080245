package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
  public static Connection conn() {
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection conn = null;
    
    try {
      conn = DriverManager.getConnection(db_file); 
      System.out.println("Connected Successfully");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return conn;
  }

  public static void main(String[] args) {
    conn();
  }
}

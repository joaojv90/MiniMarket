package com.logic.main;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 * @author mrmango
 */
public class DBConnection {

  private static Connection con;

  static {
    String url = "jdbc:mysql://localhost:3306/qmet";
    String user = "root";
    String password = "Sup3r";
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      con = DriverManager.getConnection(url, user, password);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    }
  }

  public static Connection getConnection() {
    return con;
  }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logic.main;

import java.sql.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author mrmango
 */
public class DB {

  static String idUser;
  static String tableNameDB;
  static String sqlQuery;
  static double totalPrice;
  static boolean completeName;

  static DBConnection cnt = new DBConnection();
  static Connection cn = cnt.getConnection();
  static Statement st;
  static PreparedStatement pst;
  static ResultSet rs;
  static ResultSetMetaData rsmd;

  static DefaultTableModel model;
  static Object[] object;
  static String[] queryHeader;
  static HashMap<Integer, Integer> productsAndAmount = new HashMap();

  public static String getID() {
    return idUser;
  }

  // Metodo para obtener el modelo a ingresar en la tabla
  public static void getModel(JTable table) {
    try {
      st = cn.createStatement();
      rs = st.executeQuery(sqlQuery);
      rsmd = rs.getMetaData();
      tableNameDB = rsmd.getTableName(1);
      queryHeader = new String[rsmd.getColumnCount()];
      completeName = false;
      for (int i = 0, name_lastName = 0; i < rsmd.getColumnCount(); i++) {
        if (rsmd.getColumnName(i + 1).equals("firstName")
            || rsmd.getColumnName(i + 1).equals("lastName")) {
          name_lastName++;
          completeName = name_lastName == 2;
        }
        queryHeader[i] = rsmd.getColumnName(i + 1);
      }
      object = new Object[table.getColumnCount()];
      model = (DefaultTableModel) table.getModel();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    }
  }

  // Metodo para mostrar una consulta de la base de datos en una JTable
  public static void showQueryInTable(JTable table, String __sqlQuery) {
    sqlQuery = __sqlQuery;
    try {
      getModel(table);
      while (rs.next()) {
        for (int i = 0, a = 0; i < table.getColumnCount(); i++) {
          if (i == 1 && completeName) {
            object[i] = rs.getString(queryHeader[a]) + " " + rs.getString(queryHeader[++a]);
          } else {
            object[i] = rs.getString(queryHeader[a]);
          }
          a++;
        }
        model.addRow(object);
      }
      table.setModel(model);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    }
  }

  // Metodo para mostrar una consulta de la base de datos en una JTable
  public static void showQuerySell(JTable table, String __sqlQuery, int values) {
    double discount;
    double pvp;
    double result;
    sqlQuery = __sqlQuery;
    try {
      getModel(table);
      while (rs.next()) {
        for (int i = 0; i < table.getColumnCount() - 2; i++) {
          object[i] = rs.getString(queryHeader[i]);
        }
        object[5] = values;
        pvp = rs.getFloat("pvp");
        discount = rs.getInt("discount");
        result = -pvp * ((discount - 100) / 100);
        totalPrice += Math.round(result * values * 100.0) / 100.0;
        object[6] = Math.round(result * values * 100.0) / 100.0;
        model.addRow(object);
      }
      table.setModel(model);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    }
  }

  public static double getTotalPrice() {
    return totalPrice;
  }

  public static void resetTotalPrice() {
    totalPrice = 0;
  }

  public static void insertData(String sqlInsert, Integer idCustomer) {
    String getIdLastQuery = "SELECT LAST_INSERT_ID()";
    Integer idOrders = null;
    String sql = "insert into orderDetails(idOrder,idProduct,quantity) values (?,?,?)";
    try {
      pst = cn.prepareStatement(sqlInsert);
      if (idCustomer == 1) {
        pst.setInt(1, idCustomer);
      } else if (idCustomer == 0) {
        st = cn.createStatement();
        rs = st.executeQuery(getIdLastQuery);
        while (rs.next()) {
          pst.setInt(1, rs.getInt(1));
        }
      } else {
        pst.setInt(1, idCustomer);
      }
      pst.executeUpdate();
      st = cn.createStatement();
      rs = st.executeQuery(getIdLastQuery);
      while (rs.next()) {
        idOrders = rs.getInt(1);
      }
      pst = cn.prepareStatement(sql);
      for (Integer idProduct : productsAndAmount.keySet()) {
        pst.setInt(1, idOrders);
        pst.setInt(2, idProduct);
        pst.setInt(3, productsAndAmount.get(idProduct));
        pst.executeUpdate();
      }
      JOptionPane.showMessageDialog(null, "Compra realizada");
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    }
    productsAndAmount.clear();
  }

  public static void getHashSellDetails(int products, int amount) {
    productsAndAmount.put(products, amount);
  }
}

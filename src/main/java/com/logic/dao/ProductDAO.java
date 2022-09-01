package com.logic.dao;

import com.logic.main.DBConnection;
import com.logic.model.Product;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class ProductDAO implements DAO<Product> {

  static Connection con = DBConnection.getConnection();

  @Override
  public int add(Product pd) throws SQLException {
    String query = "INSERT INTO products(productName, stock, price, pvp, discount, idCategory, idSupplier) VALUES (?,?,?,?,?,?,?)";
    PreparedStatement ps = con.prepareStatement(query);
    Object[] values = pd.valuesForDB();
    ps.setString(1, (String) values[0]);
    ps.setInt(2, (Integer) values[1]);
    ps.setDouble(3, (Double) values[2]);
    ps.setDouble(4, (Double) values[3]);
    ps.setInt(5, (Integer) values[4]);
    ps.setInt(6, (Integer) values[5]);
    ps.setInt(7, (Integer) values[6]);
    return ps.executeUpdate();
  }

  @Override
  public void delete(int id) throws SQLException {
    String query = "update products set status=? where idProduct =?";
    if (checkStatus(id)) {
      PreparedStatement ps = con.prepareStatement(query);
      ps.setString(1, "INACTIVO");
      ps.setInt(2, id);
      ps.executeUpdate();
      JOptionPane.showMessageDialog(null, "Se ha deshabilitado el producto");
    } else {
      JOptionPane.showMessageDialog(null, "El producto ya se encuentra inactivo");
    }
  }

  public boolean checkStatus(int id) throws SQLException {
    String query = "select status from products where idProduct =?";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    boolean check = false;
    while (rs.next()) {
      check = rs.getString("status").equalsIgnoreCase("ACTIVO");
    }
    return check;
  }

  @Override
  public Product get(int id) throws SQLException {
    String query =
        "select P.idProduct, P.productName, C.name, C.description, P.pvp, P.discount from products P " +
                "join categories C on P.idCategory=C.idCategory where idProduct=?";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setInt(1, id);
    Product pd = new Product();
    ResultSet rs = ps.executeQuery();
    boolean check = false;
    while (rs.next()) {
      check = true;
      pd.getFromDBSell(rs);
    }
    if (check) {
      return pd;
    } else {
      return null;
    }
  }

  @Override
  public List<Product> getAll() throws SQLException {
    String query =
        "select P.idProduct, S.company, P.productName, P.stock, P.price, P.pvp, P.discount, C.description, C.name, P.status "
            + "from products P "
            + "join categories C on P.idCategory=C.idCategory "
            + "join suppliers S on P.idSupplier = S.idSupplier";
    PreparedStatement ps = con.prepareStatement(query);
    ResultSet rs = ps.executeQuery();
    List<Product> ls = new ArrayList<>();
    while (rs.next()) {
      Product pd = new Product();
      pd.getFromDB(rs);
      ls.add(pd);
    }
    return ls;
  }

  @Override
  public void update(Product pd) throws SQLException {
    String query =
        "update products set productName=?, stock= ?, price= ?, pvp= ?, discount= ?, idSupplier= ?, idCategory= ? "
            + "where idProduct = ?";
    PreparedStatement ps = con.prepareStatement(query);
    Object[] values = pd.valuesForUpdateDB();
    for (int i = 0; i < values.length - 1; i++) {
      ps.setString(i + 1, (String) values[i]);
    }
    ps.setInt(8, (Integer) values[7]);
    ps.executeUpdate();
  }
}

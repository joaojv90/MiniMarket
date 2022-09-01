package com.logic.dao;

import com.logic.main.DBConnection;
import com.logic.model.Supplier;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class SupplierDAO implements DAO<Supplier> {

  static Connection con = DBConnection.getConnection();

  @Override
  public int add(Supplier supplier) throws SQLException {
    String query = "INSERT INTO suppliers(ruc, company, address, phone, mail) VALUES (?,?,?,?,?)";
    PreparedStatement ps = con.prepareStatement(query);
    Object[] values = supplier.valuesForDB();
    for (int i = 0; i < values.length; i++) {
      ps.setString(i + 1, (String) values[i]);
    }
    System.out.println(ps.toString());
    return ps.executeUpdate();
  }

  @Override
  public void delete(int id) throws SQLException {
    String query = "update suppliers set status=? where idSupplier=?";
    if (checkStatus(id)) {
      PreparedStatement ps = con.prepareStatement(query);
      ps.setString(1, "INACTIVO");
      ps.setInt(2, id);
      ps.executeUpdate();
      JOptionPane.showMessageDialog(null, "Se ha deshabilitado el proveedor");
    } else {
      JOptionPane.showMessageDialog(null, "El proovedor ya se encuentra inactivo");
    }
  }

  public boolean checkStatus(int id) throws SQLException {
    String query = "select status from suppliers where idSupplier =?";
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
  public Supplier get(int id) throws SQLException {
    String query = "select * from suppliers where idSupplier= ?";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setInt(1, id);
    Supplier supplier = new Supplier();
    ResultSet rs = ps.executeQuery();
    boolean check = false;
    while (rs.next()) {
      check = true;
      supplier.getSupplierDB(rs);
    }
    if (check) {
      return supplier;
    } else {
      return null;
    }
  }

  @Override
  public List<Supplier> getAll() throws SQLException {
    String query = "select * from suppliers";
    PreparedStatement ps = con.prepareStatement(query);
    ResultSet rs = ps.executeQuery();
    List<Supplier> ls = new ArrayList<>();
    while (rs.next()) {
      Supplier supplier = new Supplier();
      supplier.getSupplierDB(rs);
      ls.add(supplier);
    }
    return ls;
  }

  @Override
  public void update(Supplier supplier) throws SQLException {
    String query =
        "update suppliers set ruc=?, company= ?, address= ?, phone= ?, mail= ?, status=? where idSupplier = ?";
    PreparedStatement ps = con.prepareStatement(query);
    Object[] values = supplier.valuesForUpdateDB();
    for (int i = 0; i < values.length - 1; i++) {
      ps.setString(i + 1, (String) values[i]);
    }
    ps.setInt(7, (Integer) values[6]);
    ps.executeUpdate();
  }
}

package com.logic.dao;

import com.logic.main.DBConnection;
import com.logic.model.Admin;
import java.sql.*;
import java.util.*;

public class AdminDAO implements DAO<Admin> {

  static Connection con = DBConnection.getConnection();

  @Override
  public int add(Admin admin) throws SQLException {
    String query = "INSERT INTO admin(nui, firstName, lastName, address, phone, mail, password) VALUES (?,?,?,?,?,?,md5(?))";
    PreparedStatement ps = con.prepareStatement(query);
    Object[] values = admin.valuesForDB();
    for (int i = 0; i < values.length; i++) {
      ps.setString(i + 1, (String) values[i]);
    }
    return ps.executeUpdate();
  }

  @Override
  public void delete(int id) throws SQLException {
    //nada
  }

  @Override
  public Admin get(int id) throws SQLException {
    String query = "select * from admin where idAdmin= ?";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setInt(1, id);
    Admin admin = new Admin();
    ResultSet rs = ps.executeQuery();
    boolean check = false;
    while (rs.next()) {
      check = true;
      admin.getFromDB(rs);
    }
    if (check) {
      return admin;
    } else {
      return null;
    }
  }

  @Override
  public List<Admin> getAll() throws SQLException {
    String query = "select * from employees";
    PreparedStatement ps = con.prepareStatement(query);
    ResultSet rs = ps.executeQuery();
    List<Admin> ls = new ArrayList<>();
    while (rs.next()) {
      Admin admin = new Admin();
      admin.getFromDB(rs);
      ls.add(admin);
    }
    return ls;
  }

  @Override
  public void update(Admin admin) throws SQLException {
    String query =
        "update admin set firstName= ?, lastName= ?, nui=?, address= ?, phone= ?, mail= ?, password= md5(?) where idAdmin = 1";
    PreparedStatement ps = con.prepareStatement(query);
    Object[] values = admin.valuesForUpdateDB();
    for (int i = 0; i < values.length; i++) {
      ps.setString(i + 1, (String) values[i]);
    }
    ps.executeUpdate();
  }

  public Admin login(String user, String password) throws SQLException {
    Admin admin = new Admin();
    String query = "select * from admin where mail=? and password=md5(?)";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setString(1, user);
    ps.setString(2, password);
    ResultSet rs = ps.executeQuery();
    boolean check = false;
    while (rs.next()) {
      check = true;
      admin.getFromDB(rs);
    }
    if (check) {
      return admin;
    } else {
      return null;
    }
  }
}

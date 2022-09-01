package com.logic.dao;

import com.logic.main.DBConnection;
import com.logic.model.Employee;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class EmployeeDAO implements DAO<Employee> {

  static Connection con = DBConnection.getConnection();

  @Override
  public int add(Employee emp) throws SQLException {
    String query = "INSERT INTO employees(nui, firstName, lastName, address, phone, mail, password) VALUES (?,?,?,?,?,?,md5(?))";
    PreparedStatement ps = con.prepareStatement(query);
    Object[] values = emp.valuesForDB();
    for (int i = 0; i < values.length; i++) {
      ps.setString(i + 1, (String) values[i]);
    }
    return ps.executeUpdate();
  }

  @Override
  public void delete(int id) throws SQLException {
    String query = "update employees set status=? where idEmployee =?";
    if (checkStatus(id)) {
      PreparedStatement ps = con.prepareStatement(query);
      ps.setString(1, "INACTIVO");
      ps.setInt(2, id);
      ps.executeUpdate();
      JOptionPane.showMessageDialog(null, "Se ha deshabilitado el empleado");
    } else {
      JOptionPane.showMessageDialog(null, "El empleado ya se encuentra inactivo");
    }
  }

  public boolean checkStatus(int id) throws SQLException {
    String query = "select status from employees where idEmployee =?";
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
  public Employee get(int id) throws SQLException {
    String query = "select * from employees where idEmployee= ?";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setInt(1, id);
    Employee emp = new Employee();
    ResultSet rs = ps.executeQuery();
    boolean check = false;
    while (rs.next()) {
      check = true;
      emp.getFromDB(rs);
    }
    if (check) {
      return emp;
    } else {
      return null;
    }
  }

  @Override
  public List<Employee> getAll() throws SQLException {
    String query = "select * from employees";
    PreparedStatement ps = con.prepareStatement(query);
    ResultSet rs = ps.executeQuery();
    List<Employee> ls = new ArrayList<>();
    while (rs.next()) {
      Employee emp = new Employee();
      emp.getFromDB(rs);
      ls.add(emp);
    }
    return ls;
  }

  @Override
  public void update(Employee emp) throws SQLException {
    String query =
            "update employees set nui=?, firstName= ?, lastName= ?, address= ?, phone= ?, mail= ?, status= ? where idEmployee = ?";
    PreparedStatement ps = con.prepareStatement(query);
    Object[] values = emp.valuesForUpdate();
    for (int i = 0; i < values.length-1; i++) {
      ps.setString(i + 1, (String) values[i]);
    }
    ps.setInt(8, (Integer) values[7]);
    ps.executeUpdate();
  }

  public void updateUser(Employee emp) throws SQLException {
    String query =
            "update employees set nui=?, firstName= ?, lastName= ?, address= ?, phone= ?, mail= ?, password=? where idEmployee = ?";
    PreparedStatement ps = con.prepareStatement(query);
    Object[] values = emp.valuesForUpdateDB();
    for (int i = 0; i < values.length-1; i++) {
      ps.setString(i + 1, (String) values[i]);
    }
    ps.setInt(8, (Integer) values[7]);
    ps.executeUpdate();
  }

  public Employee login(String user, String password) throws SQLException {
    Employee emp = new Employee();
    String query = "select * from employees where mail=? and password=md5(?)";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setString(1, user);
    ps.setString(2, password);
    ResultSet rs = ps.executeQuery();
    boolean check = false;
    while (rs.next()) {
      check = true;
      emp.getFromDB(rs);
    }
    if (check) {
      return emp;
    } else {
      return null;
    }
  }
}

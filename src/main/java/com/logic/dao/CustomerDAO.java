package com.logic.dao;

import com.logic.main.DBConnection;
import com.logic.model.Customer;
import java.sql.*;
import java.util.*;

public class CustomerDAO implements DAO<Customer> {

  static Connection con = DBConnection.getConnection();

  @Override
  public int add(Customer customer) throws SQLException {
    String query = "INSERT INTO customers(nui, firstName, lastName, address, phone, mail) VALUES (?,?,?,?,?,?)";
    PreparedStatement ps = con.prepareStatement(query);
    Object[] values = customer.valuesForDB();
    for (int i = 0; i < values.length; i++) {
      ps.setString(i + 1, (String) values[i]);
    }
    return ps.executeUpdate();
  }

  @Override
  public void delete(int id) throws SQLException {
    String query = "delete from customers where idCustomer =?";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setInt(1, id);
    ps.executeUpdate();
  }

  @Override
  public Customer get(int id) throws SQLException {
    String query = "select * from customers where idCustomer= ?";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setInt(1, id);
    Customer customer = new Customer();
    ResultSet rs = ps.executeQuery();
    boolean check = false;
    while (rs.next()) {
      check = true;
      customer.getFromDB(rs);
    }
    if (check) {
      return customer;
    } else {
      return null;
    }
  }

  public Customer get(String nui) throws SQLException {
    String query = "select * from customers where nui= ?";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setString(1, nui);
    Customer customer = new Customer();
    ResultSet rs = ps.executeQuery();
    boolean check = false;
    while (rs.next()) {
      check = true;
      customer.getFromDB(rs);
    }
    if (check) {
      return customer;
    } else {
      return null;
    }
  }

  @Override
  public List<Customer> getAll() throws SQLException {
    String query = "select * from customers";
    PreparedStatement ps = con.prepareStatement(query);
    ResultSet rs = ps.executeQuery();
    List<Customer> ls = new ArrayList<>();
    while (rs.next()) {
      Customer customer = new Customer();
      customer.getFromDB(rs);
      ls.add(customer);
    }
    return ls;
  }

  @Override
  public void update(Customer customer) throws SQLException {
    String query =
        "update customers set nui=?, firstName= ?, lastName= ?, address= ?, phone= ?, mail= ? where idCustomer = ?";
    PreparedStatement ps = con.prepareStatement(query);
    Object[] values = customer.valuesForUpdateDB();
    for (int i = 0; i < values.length - 1; i++) {
      ps.setString(i + 1, (String) values[i]);
    }
    ps.setInt(7, (Integer) values[6]);
    ps.executeUpdate();
  }
}

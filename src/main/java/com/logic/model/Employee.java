package com.logic.model;

import java.sql.*;
import java.util.*;

public class Employee extends Person {

  private String password;
  private String status = "ACTIVO";

  public Employee() {}

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public void getFromDB(ResultSet rs) throws SQLException {
    setId(rs.getInt("idEmployee"));
    setNui(rs.getString("nui"));
    setFirstName(rs.getString("firstName"));
    setLastName(rs.getString("lastName"));
    setAddress(rs.getString("address"));
    setPhone(rs.getString("phone"));
    setMail(rs.getString("mail"));
    setStatus(rs.getString("status"));
  }

  @Override
  public Object[] valuesForTable() {
    return new Object[] {id, firstName + " " + lastName, nui, address, phone, mail, status};
  }

  @Override
  public void create(List<Object> values) {
    setFirstName((String) values.get(1));
    setLastName((String) values.get(2));
    setNui((String) values.get(0));
    setAddress((String) values.get(3));
    setPhone((String) values.get(4));
    setMail((String) values.get(5));
    setPassword((String) values.get(6));
  }

  @Override
  public Object[] valuesForDB() {
    return new Object[] {firstName, lastName, nui, address, phone, mail, password};
  }

  @Override
  public void update(List<Object> values) {
    setNui((String) values.get(0));
    setFirstName((String) values.get(1));
    setLastName((String) values.get(2));
    setAddress((String) values.get(3));
    setPhone((String) values.get(4));
    setMail((String) values.get(5));
    setPassword((String) values.get(6));
  }

  @Override
  public Object[] valuesForUpdateDB() {
    return new Object[] {firstName, lastName, nui, address, phone, mail, password, id};
  }

  public void updateTable(List<Object> values) {
    setId((Integer) values.get(0));
    setCompleteName(((String) values.get(1)).split(" "));
    setFirstName(completeName[0]);
    setLastName(completeName[1]);
    setNui((String) values.get(2));
    setAddress((String) values.get(3));
    setPhone((String) values.get(4));
    setMail((String) values.get(5));
  }

  public Object[] valuesForUpdate() {
    return new Object[] {nui, firstName, lastName, address, phone, mail, status, id};
  }
}

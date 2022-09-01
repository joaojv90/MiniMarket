package com.logic.model;

import java.sql.*;
import java.util.*;

public class Admin extends Person{

  private String password;

  public Admin() {}

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public void getFromDB(ResultSet rs) throws SQLException {
    setId(rs.getInt(1));
    setNui(rs.getString(2));
    setFirstName(rs.getString(3));
    setLastName(rs.getString(4));
    setAddress(rs.getString(5));
    setPhone(rs.getString(6));
    setMail(rs.getString(7));
  }

  @Override
  public Object[] valuesForTable() {
    return new Object[] {id, firstName + " " + lastName, nui, address, phone, mail};
  }

  @Override
  public void create(List<Object> values) {
    setFirstName((String) values.get(0));
    setLastName((String) values.get(1));
    setNui((String) values.get(2));
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
    setFirstName((String) values.get(0));
    setLastName((String) values.get(1));
    setNui((String) values.get(2));
    setAddress((String) values.get(3));
    setPhone((String) values.get(4));
    setMail((String) values.get(5));
    setPassword((String) values.get(6));
  }

  @Override
  public Object[] valuesForUpdateDB() {
    return new Object[] {firstName, lastName, nui,address, phone, mail, password};
  }
}

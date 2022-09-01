package com.logic.model;

import java.sql.*;
import java.util.*;

public class Customer extends Person{


  public Customer() {}

  @Override
  public void getFromDB(ResultSet rs) throws SQLException {
    setId(rs.getInt("idCustomer"));
    setNui(rs.getString("nui"));
    setFirstName(rs.getString("firstName"));
    setLastName(rs.getString("lastName"));
    setAddress(rs.getString("address"));
    setPhone(rs.getString("phone"));
    setMail(rs.getString("mail"));
  }

  @Override
  public Object[] valuesForTable() {
    return new Object[] {id, firstName + " " + lastName, nui, address, phone, mail};
  }

  @Override
  public void create(List<Object> values) {
    setNui((String) values.get(0));
    setFirstName((String) values.get(1));
    setLastName((String) values.get(2));
    setAddress((String) values.get(3));
    setPhone((String) values.get(4));
    setMail((String) values.get(5));
  }

  @Override
  public Object[] valuesForDB() {
    return new Object[] {nui, firstName, lastName, address, phone, mail};
  }

  @Override
  public void update(List<Object> values) {
    setId((Integer) values.get(0));
    setCompleteName(((String) values.get(1)).split(" "));
    setFirstName(completeName[0]);
    setLastName(completeName[1]);
    setNui((String) values.get(2));
    setAddress((String) values.get(3));
    setPhone((String) values.get(4));
    setMail((String) values.get(5));
  }

  public Object[] valuesForUpdateDB() {
    return new Object[] {nui, firstName, lastName, address, phone, mail,id};
  }
}

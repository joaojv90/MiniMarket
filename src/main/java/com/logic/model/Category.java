package com.logic.model;

import java.sql.*;
import java.util.*;

public class Category {

  private int idCategory;
  private String name;
  private String description;

  public Category() {}

  public void setIdCategory(int idCategory) {
    this.idCategory = idCategory;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public void getFromDB(ResultSet rs) throws SQLException {
    setIdCategory(rs.getInt("idCategory"));
    setName(rs.getString("name"));
    setDescription(rs.getString("description"));
  }
  public void create(List<Object> values) {
    setName((String) values.get(0));
    setDescription((String) values.get(1));
  }
  public Object[] valuesForDB() {
    return new Object[] {name, description};
  }

  public Object[] valuesForUpdateDB() {
    return new Object[] {name, description};
  }
  
}

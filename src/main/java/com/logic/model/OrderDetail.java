package com.logic.model;

import java.sql.*;
import java.util.*;

public class OrderDetail {

  private int idOrder;
  private int idProduct;
  private int quantity;
  private String name;

  public OrderDetail() {}

  public void setIdOrder(int idOrder) {
    this.idOrder = idOrder;
  }

  public void setIdProduct(int idProduct) {
    this.idProduct = idProduct;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void getFromDB(ResultSet rs) throws SQLException {
    setName(rs.getString(1));
    setQuantity(rs.getInt(2));
  }

  public Object[] valuesForTable() {
    return new Object[] {
            name,
            quantity
    };
  }

  public void create(List<Object> values) {
    setIdOrder((Integer) values.get(0));
    setIdProduct((Integer) values.get(1));
    setQuantity((Integer) values.get(2));
  }

  public Object[] valuesForDB() {
    return new Object[] {idOrder, idProduct, quantity};
  }

  public void update(List<Object> values) {
    setIdProduct((Integer) values.get(0));
    setQuantity((Integer) values.get(1));
  }

  public Object[] valuesForUpdateDB() {
    return new Object[] {idProduct, quantity};
  }
}

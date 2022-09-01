package com.logic.model;

import java.sql.*;
import java.util.*;

public class Product {

  Category ct = new Category();
  Supplier sp = new Supplier();
  OrderDetail od = new OrderDetail();

  private int idProduct;
  private int idCategory;
  private int idSupplier;
  private int stock;
  private double price;
  private double pvp;
  private int discount;
  private String productName;
  private String status;

  public Product() {}

  public void setIdProduct(int idProduct) {
    this.idProduct = idProduct;
  }

  public void setIdCategory(int idCategory) {
    this.idCategory = idCategory;
  }

  public void setIdSupplier(int idSupplier) {
    this.idSupplier = idSupplier;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setPvp(double pvp) {
    this.pvp = pvp;
  }

  public void setDiscount(int discount) {
    this.discount = discount;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getIdProduct() {
    return idProduct;
  }

  public double getPvp() {
    return pvp;
  }

  public void setQuantity(int quantity){
    od.setQuantity(quantity);
  }

  public int getQuantity(){
    return od.getQuantity();
  }

  public int getDiscount() {
    return discount;
  }

  public void getFromDB(ResultSet rs) throws SQLException {
    setIdProduct(rs.getInt("idProduct"));
    sp.setCompany(rs.getString("company"));
    setProductName(rs.getString("productName"));
    setStock(rs.getInt("stock"));
    setPrice(rs.getDouble("price"));
    setPvp(rs.getDouble("pvp"));
    setDiscount(rs.getInt("discount"));
    ct.setDescription(rs.getString("description"));
    ct.setName(rs.getString("name"));
    setStatus(rs.getString("status"));
  }

  public Object[] valuesForTable() {
    return new Object[] {
      idProduct,
      sp.getCompany(),
      productName,
      stock,
      price,
      pvp,
      discount,
      ct.getDescription(),
      ct.getName(),
      status
    };
  }

  public void getFromDBSell(ResultSet rs) throws SQLException {
    setIdProduct(rs.getInt(1));
    setProductName(rs.getString(2));
    ct.setDescription(rs.getString(3));
    ct.setName(rs.getString(4));
    setPvp(rs.getDouble(5));
    setDiscount(rs.getInt(6));
  }

  public Object[] valuesForSell() {
    return new Object[] {
            idProduct,
            productName,
            ct.getDescription(),
            ct.getName(),
            pvp,
            discount,
            od.getQuantity(),
            Math.round(pvp*od.getQuantity()*100.0)/100.0
    };
  }

  public void create(List<Object> values) {
    setProductName((String) values.get(0));
    setStock(Integer.parseInt((String) values.get(1)));
    setPrice(Double.parseDouble((String) values.get(2)));
    setPvp(Double.parseDouble((String) values.get(3)));
    setDiscount(Integer.parseInt((String) values.get(4)));
    setIdCategory((Integer) values.get(5));
    setIdSupplier((Integer) values.get(6));
  }

  public Object[] valuesForDB() {
    return new Object[] {productName, stock, price, pvp, discount, idCategory, idSupplier};
  }

  public void update(List<Object> values) {
    setProductName((String) values.get(0));
    setStock((Integer) values.get(1));
    setPrice((Double) values.get(2));
    setPvp((Double) values.get(3));
    setDiscount((Integer) values.get(4));
    setIdCategory((Integer) values.get(5));
    setIdSupplier((Integer) values.get(6));
  }

  public Object[] valuesForUpdateDB() {
    return new Object[] {idCategory, idSupplier, productName, stock, price, pvp, discount};
  }
}

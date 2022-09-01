package com.logic.model;

import java.sql.Date;
import java.sql.*;
import java.util.List;

public class Order {

  OrderDetail od = new OrderDetail();
  Employee emp = new Employee();
  Customer cm = new Customer();
  Product pd = new Product();

  private int idOrder;
  private int idEmployee;
  private int idCustomer;
  private Date date;
  private Time time;

  public Order() {}

  public void setIdOrder(int idOrder) {
    this.idOrder = idOrder;
  }

  public void setIdEmployee(int idEmployee) {
    this.idEmployee = idEmployee;
  }

  public void setIdCustomer(int idCustomer) {
    this.idCustomer = idCustomer;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public void setTime(Time time) {
    this.time = time;
  }

  public int getIdCustomer() {
    return idCustomer;
  }

  public void getFromDB(ResultSet rs) throws SQLException {
    setIdOrder(rs.getInt(1));
    emp.setFirstName(rs.getString(2));
    emp.setLastName(rs.getString(3));
    cm.setFirstName(rs.getString(4));
    cm.setLastName(rs.getString(5));
    setDate(rs.getDate(6));
    setTime(rs.getTime(7));
    od.setQuantity(rs.getInt(8));
    pd.setPvp(rs.getDouble(9));
  }

  public Object[] valuesForTable() {
    return new Object[] {
            idOrder,
            emp.getFirstName()+" "+emp.getLastName(),
            cm.getFirstName()+" "+cm.getLastName(),
            date,
            time,
            od.getQuantity(),
            Math.round(pd.getPvp()*100.0)/100.0
    };
  }

  public void create(List<Object> values) {
    setIdEmployee((Integer) values.get(0));
    setIdCustomer((Integer) values.get(1));
  }

  public Object[] valuesForDB() {
    return new Object[] {idCustomer,idEmployee};
  }

  public void update(List<Object> values) {
    setIdEmployee((Integer) values.get(0));
    setIdCustomer((Integer) values.get(1));
  }
  public Object[] valuesForUpdateDB() {
    return new Object[] {idCustomer,idEmployee};
  }
}

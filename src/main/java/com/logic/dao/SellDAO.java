package com.logic.dao;

import com.logic.main.DBConnection;
import com.logic.model.OrderDetail;
import java.sql.*;
import java.util.*;

public class SellDAO implements DAO<OrderDetail> {

  static Connection con = DBConnection.getConnection();

  @Override
  public int add(OrderDetail od) throws SQLException {
    String query = "INSERT INTO orderDetails(idOrder, idProduct, quantity) VALUES (?,?,?)";
    PreparedStatement ps = con.prepareStatement(query);
    Object[] values = od.valuesForDB();
    ps.setInt(1, (Integer) values[0]);
    ps.setInt(2, (Integer) values[1]);
    ps.setInt(3, (Integer) values[2]);
    return ps.executeUpdate();
  }

  @Override
  public void delete(int id) throws SQLException {
    String query = "delete from orders where idOrder =?";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setInt(1, id);
    ps.executeUpdate();
  }

  @Override
  public OrderDetail get(int id) throws SQLException {
    String query = "select * from orders where idOrder= ?";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setInt(1, id);
    OrderDetail od = new OrderDetail();
    ResultSet rs = ps.executeQuery();
    boolean check = false;
    while (rs.next()) {
      check = true;
      od.getFromDB(rs);
    }
    if (check) {
      return od;
    } else {
      return null;
    }
  }

  @Override
  public List<OrderDetail> getAll() throws SQLException { 
    String query =
        "select P.productName,sum(OD.quantity) from orderDetails OD "
            + "join products P on OD.idProduct=P.idProduct "
            + "join orders O on O.idOrder=OD.idOrder "
            + "where DATE(O.orderDate)=CURDATE() group by OD.idProduct order by sum(OD.quantity) desc";
    PreparedStatement ps = con.prepareStatement(query);
    ResultSet rs = ps.executeQuery();
    List<OrderDetail> ls = new ArrayList<>();
    while (rs.next()) {
      OrderDetail od = new OrderDetail();
      od.getFromDB(rs);
      ls.add(od);
    }
    return ls;
  }

  public List<OrderDetail> getAll2() throws SQLException { 
    String query =
        "select concat(C.firstName,' ',C.lastName),sum(OD.quantity) from orderDetails OD "
            + "join orders O on O.idOrder=OD.idOrder "
            + "join customers C on C.idCustomer=O.idCustomer "
            + "where DATE(O.orderDate)=CURDATE() group by OD.idOrder order by sum(OD.quantity) desc";
    PreparedStatement ps = con.prepareStatement(query);
    ResultSet rs = ps.executeQuery();
    List<OrderDetail> ls = new ArrayList<>();
    while (rs.next()) {
      OrderDetail od = new OrderDetail();
      od.getFromDB(rs);
      ls.add(od);
    }
    return ls;
  }

  @Override
  public void update(OrderDetail od) throws SQLException {
    String query = "update orders set idEmployee=?, idCustomer= ? where idOrder = ?";
    PreparedStatement ps = con.prepareStatement(query);
    Object[] values = od.valuesForUpdateDB();
    for (int i = 0; i < values.length - 1; i++) {
      ps.setString(i + 1, (String) values[i]);
    }
    ps.setInt(3, (Integer) values[2]);
    ps.executeUpdate();
  }

  public Integer getLastQuery(){
    try {
      int lastId = 0;
      String query = "SELECT LAST_INSERT_ID()";
      PreparedStatement ps = con.prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        lastId = rs.getInt(1);
      }
      return lastId;
    } catch (SQLException e) {
      e.printStackTrace(System.out);
    }
    return null;
  }
}

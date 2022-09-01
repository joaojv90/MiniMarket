package com.logic.dao;

import com.logic.main.DBConnection;
import com.logic.model.Order;
import java.sql.*;
import java.util.*;

public class OrderDAO implements DAO<Order> {

  static Connection con = DBConnection.getConnection();

  @Override
  public int add(Order o) throws SQLException {
    String query = "INSERT INTO orders(idCustomer, idEmployee, orderDate) VALUES (?,?, now())";
    PreparedStatement ps = con.prepareStatement(query);
    Object[] values = o.valuesForDB();
    ps.setInt(1, (Integer) values[0]);
    ps.setInt(2, (Integer) values[1]);
    ps.executeUpdate();
    return getLastQuery();
  }

  @Override
  public void delete(int id) throws SQLException {
    String query = "delete from orders where idOrder =?";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setInt(1, id);
    ps.executeUpdate();
  }

  @Override
  public Order get(int id) throws SQLException {
    String query = "select * from orders where idOrder= ?";
    PreparedStatement ps = con.prepareStatement(query);
    ps.setInt(1, id);
    Order o = new Order();
    ResultSet rs = ps.executeQuery();
    boolean check = false;
    while (rs.next()) {
      check = true;
      o.getFromDB(rs);
    }
    if (check) {
      return o;
    } else {
      return null;
    }
  }

  @Override
  public List<Order> getAll() throws SQLException {
    String query =
        "select O.idOrder, E.firstName, E.lastName,C.firstName,C.lastName, "
            + "DATE(O.orderDate), TIME(O.orderDate), sum(OD.quantity),sum(P.pvp * OD.quantity) "
            + "from orders O "
            + "join orderDetails OD on O.idOrder=OD.idOrder "
            + "join employees E on O.idEmployee=E.idEmployee "
            + "join customers C on O.idCustomer=C.idCustomer "
            + "join products P on OD.idProduct=P.idProduct "
            + "group by OD.idOrder";
    PreparedStatement ps = con.prepareStatement(query);
    ResultSet rs = ps.executeQuery();
    List<Order> ls = new ArrayList<>();
    while (rs.next()) {
      Order o = new Order();
      o.getFromDB(rs);
      ls.add(o);
    }
    return ls;
  }

  @Override
  public void update(Order o) throws SQLException {
    String query = "update orders set idEmployee=?, idCustomer= ? where idOrder = ?";
    PreparedStatement ps = con.prepareStatement(query);
    Object[] values = o.valuesForUpdateDB();
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

package com.logic.views;

import com.logic.dao.*;
import com.logic.model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.*;

public class SellView {

  DefaultTableModel model;

  static List<Product> products = new ArrayList<>();
  int idOrder;

  public List<OrderDetail> getOrders() {
    List<OrderDetail> orders = new ArrayList<>();
    for (Product product : products) {
      OrderDetail od = new OrderDetail();
      od.setIdProduct(product.getIdProduct());
      od.setQuantity(product.getQuantity());
      od.setIdOrder(idOrder);
      orders.add(od);
    }
    return orders;
  }

  public double getTotalPrice() {
    Double operation;
    Double totalPrice = 0.0;
    for (Product product : products) {
      operation = -product.getPvp() * ((product.getDiscount() - 100) / 100);
      totalPrice += Math.round(operation * product.getQuantity() * 100.0) / 100.0;
    }
    return totalPrice;
  }

  public void sellCustomer(Order order) {
    OrderDAO orderDAO = new OrderDAO();
    try {
      idOrder = orderDAO.add(order);
      productsOrders();
    } catch (SQLException e) {
      e.printStackTrace(System.out);
    }
  }

  public void sellNewCustomer(Order order) {
    OrderDAO orderDAO = new OrderDAO();
    SellDAO sellDAO = new SellDAO();
    if(order.getIdCustomer()==0){
      int idCustomer = sellDAO.getLastQuery();
      order.setIdCustomer(idCustomer);
    }
    try {
      idOrder = orderDAO.add(order);
      productsOrders();
    } catch (SQLException e) {
      e.printStackTrace(System.out);
    }
  }

  public void addToCar(JTable table, int id, int quantity) {
    ProductDAO pdDAO = new ProductDAO();
    try {
      Object[] object;
      Product product = pdDAO.get(id);
      product.setQuantity(quantity);
      products.add(product);
      model = (DefaultTableModel) table.getModel();
      object = product.valuesForSell();
      model.addRow(object);
      table.setModel(model);
    } catch (SQLException e) {
      e.printStackTrace(System.out);
    }
  }

  public void dashboardCustomers(JTable table) { //funciona con las ventas de ahora
    SellDAO sellDAO = new SellDAO();
    try {
      Object[] object;
      List<OrderDetail> orderDetails = sellDAO.getAll2();
      model = (DefaultTableModel) table.getModel();
      for (OrderDetail orderDetail : orderDetails) {
        object = orderDetail.valuesForTable();
        model.addRow(object);
      }
      table.setModel(model);
    } catch (SQLException e) {
      e.printStackTrace(System.out);
    }
  }

  public void dashboardProducts(JTable table) {
    SellDAO sellDAO = new SellDAO();
    try {
      Object[] object;
      List<OrderDetail> orderDetails = sellDAO.getAll();
      model = (DefaultTableModel) table.getModel();
      for (OrderDetail orderDetail : orderDetails) {
        object = orderDetail.valuesForTable();
        model.addRow(object);
      }
      table.setModel(model);
    } catch (SQLException e) {
      e.printStackTrace(System.out);
    }
  }

  public void productsOrders() {
    SellDAO sellDAO = new SellDAO();
    List<OrderDetail> orders = getOrders();
    try {
      for (OrderDetail order : orders) {
        sellDAO.add(order);
      }
      JOptionPane.showMessageDialog(null, "Compra realizada");
    } catch (SQLException e) {
      e.printStackTrace(System.out);
    }
    products.clear();
  }
}

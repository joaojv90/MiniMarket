package com.logic.views;

import com.logic.dao.CustomerDAO;
import com.logic.main.Functions;
import com.logic.model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class CustomerView implements View<JTable> {

  DefaultTableModel model;

  @Override
  public void addToDB(JPanel panel) {
    Customer customer = new Customer();
    CustomerDAO cmDAO = new CustomerDAO();
    List<Object> dataFromTxtField = Functions.valuesJPanel(panel.getComponents());
    if (dataFromTxtField == null) {
      JOptionPane.showMessageDialog(null, "Rellene los campos necesarios");
    } else {
      try {
        customer.create(dataFromTxtField);
        if (cmDAO.add(customer) != 0) {
          JOptionPane.showMessageDialog(null, "El cliente ha sido agregado");
        }
        Functions.clearJTextFields(panel.getComponents());
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void showQuery(JTable table) {
    CustomerDAO cmDAO = new CustomerDAO();
    try {
      Object[] object;
      List<Customer> customers = cmDAO.getAll();
      model = (DefaultTableModel) table.getModel();
      for (Customer customer : customers) {
        object = customer.valuesForTable();
        model.addRow(object);
      }
      table.setModel(model);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void modify(JTable table) {
    CustomerDAO cmDAO = new CustomerDAO();
    Customer customer = new Customer();
    List<Object> values = Functions.getValuesFromJtable(table);
    if (values != null) {
      customer.update(values);
      try {
        cmDAO.update(customer);
        model.setRowCount(0);
        showQuery(table);
        JOptionPane.showMessageDialog(null, "Se actualizo el cliente");
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } else {
      JOptionPane.showMessageDialog(null, "Datos incorrectos");
    }
  }

  @Override
  public void delete(JTable table) {
    CustomerDAO cmDAO = new CustomerDAO();
    int id = (int) table.getValueAt(table.getSelectedRow(), 0);
    try {
      cmDAO.delete(id);
      model.setRowCount(0);
      showQuery(table);
      JOptionPane.showMessageDialog(null, "Se ha deshabilitado el cliente");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Integer searchCustomer(String nui, Component[] pnlComponents) {
    Customer cm;
    CustomerDAO cmDAO = new CustomerDAO();
    int count = 0;
    try {
      cm = cmDAO.get(nui);
      if (cm!=null) {
        for (Component pnlComponent : pnlComponents) {
          if (pnlComponent instanceof JTextField) {
            ((JTextField) pnlComponent).setText((String) cm.valuesForDB()[count++]);
          }
        }
        return cm.getId();
      } else {
        JOptionPane.showMessageDialog(null, "No hay un cliente registrado");
        return null;
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e);
    }
    return null;
  }
}

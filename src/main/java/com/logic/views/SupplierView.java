package com.logic.views;

import com.logic.dao.SupplierDAO;
import com.logic.main.Functions;
import com.logic.model.Supplier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class SupplierView implements View<JTable> {

  DefaultTableModel model;

  @Override
  public void addToDB(JPanel panel) {
    Supplier supplier = new Supplier();
    SupplierDAO spDAO = new SupplierDAO();
    List<Object> dataFromTxtField = Functions.valuesJPanel(panel.getComponents());
    if (dataFromTxtField == null) {
      JOptionPane.showMessageDialog(null, "Rellene los campos necesarios");
    } else {
      try {
        supplier.createSupplier(dataFromTxtField);
        if (spDAO.add(supplier) != 0) {
          JOptionPane.showMessageDialog(null, "El proveedor ha sido agregado");
        }
        Functions.clearJTextFields(panel.getComponents());
      } catch (SQLException e) {
        e.printStackTrace(System.out);
      }
    }
  }

  @Override
  public void showQuery(JTable table) {
    SupplierDAO spDAO = new SupplierDAO();
    try {
      Object[] object;
      List<Supplier> suppliers = spDAO.getAll();
      model = (DefaultTableModel) table.getModel();
      for (Supplier supplier : suppliers) {
        object = supplier.valuesForTable();
        model.addRow(object);
      }
      table.setModel(model);
    } catch (SQLException e) {
      e.printStackTrace(System.out);
    }
  }

  public void showQuery(JComboBox cbox) {
    SupplierDAO spDAO = new SupplierDAO();
    try {
      List<Supplier> suppliers = spDAO.getAll();
      for (Supplier supplier : suppliers) {
        cbox.addItem(supplier.getCompany());
      }
    } catch (SQLException e) {
      e.printStackTrace(System.out);
    }
  }

  public void modify(JTable table) {
    SupplierDAO spDAO = new SupplierDAO();
    Supplier supplier = new Supplier();
    List<Object> values = Functions.getValuesFromJtable(table);
    if (values != null) {
      supplier.updateSupplier(values);
      try {
        spDAO.update(supplier);
        model.setRowCount(0);
        showQuery(table);
        JOptionPane.showMessageDialog(null, "Se actualizo el proovedor");
      } catch (SQLException e) {
        e.printStackTrace(System.out);
      }
    } else {
      JOptionPane.showMessageDialog(null, "Datos incorrectos");
    }
  }

  @Override
  public void delete(JTable table) {
    SupplierDAO spDAO = new SupplierDAO();
    int id = (int) table.getValueAt(table.getSelectedRow(), 0);
    try {
      spDAO.delete(id);
      model.setRowCount(0);
      showQuery(table);
    } catch (SQLException e) {
      e.printStackTrace(System.out);
    }
  }
}

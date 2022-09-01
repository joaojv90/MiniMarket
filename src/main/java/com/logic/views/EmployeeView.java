package com.logic.views;

import com.logic.dao.EmployeeDAO;
import com.logic.main.Functions;
import com.logic.model.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class EmployeeView implements View<JTable> {
  DefaultTableModel model;

  @Override
  public void addToDB(JPanel panel) {
    Employee emp = new Employee();
    EmployeeDAO empDAO = new EmployeeDAO();
    List<Object> dataFromTxtField = Functions.valuesJPanel(panel.getComponents());
    if (dataFromTxtField == null) {
      JOptionPane.showMessageDialog(null, "Rellene los campos necesarios");
    } else {
      try {
        emp.create(dataFromTxtField);
        if (empDAO.add(emp) != 0) {
          JOptionPane.showMessageDialog(null, "El empleado ha sido agregado");
        }
        Functions.clearJTextFields(panel.getComponents());
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void showQuery(JTable table) {
    EmployeeDAO empDAO = new EmployeeDAO();
    try {
      Object[] object;
      List<Employee> employees = empDAO.getAll();
      model = (DefaultTableModel) table.getModel();
      for (Employee employee : employees) {
        object = employee.valuesForTable();
        model.addRow(object);
      }
      table.setModel(model);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void modify(JTable table) {
    EmployeeDAO empDAO = new EmployeeDAO();
    Employee emp = new Employee();
    List<Object> values = Functions.getValuesFromJtable(table);
    if (values != null) {
      emp.updateTable(values);
      try {
        empDAO.update(emp);
        model.setRowCount(0);
        showQuery(table);
        JOptionPane.showMessageDialog(null, "Se actualizo el empleado");
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } else {
      JOptionPane.showMessageDialog(null, "Datos incorrectos");
    }
  }

  @Override
  public void delete(JTable table) {
    EmployeeDAO empDAO = new EmployeeDAO();
    int id = (int) table.getValueAt(table.getSelectedRow(), 0);
    try {
      empDAO.delete(id);
      model.setRowCount(0);
      showQuery(table);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}

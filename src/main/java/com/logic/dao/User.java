package com.logic.dao;

import com.logic.main.Functions;
import com.logic.model.*;
import com.main.frame.*;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class User {

  public Integer login(String user, String password, Login panel) {
    Person ps;
    try {
      AdminDAO adminDAO = new AdminDAO();
      ps = new Admin();
      ps = adminDAO.login(user, password);
      if (ps != null) {
        ((Admin) ps).setPassword(password);
        AdminFrame adminMain = new AdminFrame(ps);
        adminMain.setVisible(true);
        panel.dispose();
        return 1;
      } else {
        EmployeeDAO empDAO = new EmployeeDAO();
        ps = new Employee();
        ps = empDAO.login(user, password);
        if (ps != null) {
          ((Employee) ps).setPassword(password);
          EmployeeFrame employeeMain = new EmployeeFrame(ps);
          employeeMain.setVisible(true);
          panel.dispose();
          return 0;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace(System.out);
    }
    return null;
  }

  public void settings(JPanel panel, Person ps) {
    Person person = ps;
    person.setId(ps.getId());
    List<Object> dataFromTxtField = Functions.valuesJPanel(panel.getComponents());
    if (dataFromTxtField == null) {
      JOptionPane.showMessageDialog(null, "Rellene los campos necesarios");
    } else {
      if (person instanceof Admin) {
        AdminDAO adminDAO = new AdminDAO();
        try {
          person.create(dataFromTxtField);
          adminDAO.update((Admin) person);
          JOptionPane.showMessageDialog(null, "Usuario Actualizado");
        } catch (SQLException e) {
          e.printStackTrace(System.out);
        }
      } else {
        EmployeeDAO adminDAO = new EmployeeDAO();
        try {
          person.create(dataFromTxtField);
          adminDAO.update((Employee) person);
          JOptionPane.showMessageDialog(null, "Usuario Actualizado");
        } catch (SQLException e) {
          e.printStackTrace(System.out);
        }
      }
    }
  }

  public void modifyUser(JPanel panel, Person ps) {
    Component[] pnlComponents = panel.getComponents();
    int count = 0;
      for (Component pnlComponent : pnlComponents) {
        if (pnlComponent instanceof JTextField) {
          ((JTextField) pnlComponent).setText((String) ps.valuesForDB()[count++]);
        }
    }
  }
}

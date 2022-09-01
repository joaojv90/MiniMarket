package com.logic.main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Functions {

  // Obtener datos de un JTextField
  public static List<Object> valuesJPanel(Component[] pnlComponents) {
    Object value;
    List<Object> valuesList = new ArrayList<>();
    for (Component component : pnlComponents) {
      if (component instanceof JTextField) {
        value = ((JTextField) component).getText();
        if (value.equals("")) {
          return null;
        }
        valuesList.add(value);
      }
      if (component instanceof JComboBox) {
        value = (((JComboBox) component).getSelectedIndex() + 1);
        valuesList.add(value);
      }
      if (component instanceof JTextArea) {
        value = ((JTextArea) component).getText();
        valuesList.add(value);
      }
    }
    return valuesList;
  }

  // Borrar datos de un JTextField
  public static void clearJTextFields(Component[] pnlComponents) {
    for (Component component : pnlComponents) {
      if (component instanceof JTextField) {
        ((JTextField) component).setText("");
      }
      if (component instanceof JTextArea) {
        ((JTextArea) component).setText("");
      }
    }
  }

  public static void tableSorter(JTable table, String query) {
    TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter(table.getModel());
    table.setRowSorter(rowSorter);
    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + query.trim()));
  }

  public static List<Object> getValuesFromJtable(JTable table) {
    List<Object> values = new ArrayList<>();
    int row = table.getSelectedRow();
    if (row == -1) {
      JOptionPane.showMessageDialog(null, "Seleccione un Fila");
    } else {
      for (int i = 0; i < table.getColumnCount(); i++) {
        if (table.getValueAt(row, i).equals("")) {
          return null;
        } else {
          values.add(table.getValueAt(row, i));
        }
      }
    }
    return values;
  }
}

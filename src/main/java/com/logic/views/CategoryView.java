package com.logic.views;

import com.logic.dao.CategoryDAO;
import com.logic.main.Functions;
import com.logic.model.Category;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class CategoryView implements View<JComboBox>{

    DefaultTableModel model;

    @Override
    public void addToDB(JPanel panel) {
        Category ct = new Category();
        CategoryDAO ctDAO = new CategoryDAO();
        List<Object> dataFromTxtField = Functions.valuesJPanel(panel.getComponents());
        if (dataFromTxtField == null) {
            JOptionPane.showMessageDialog(null, "Rellene los campos necesarios");
        } else {
            try {
                ct.create(dataFromTxtField);
                if (ctDAO.add(ct) != 0) {
                    JOptionPane.showMessageDialog(null, "La categoria ha sido agregado");
                }
                Functions.clearJTextFields(panel.getComponents());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void showQuery(JComboBox cbox) {
        CategoryDAO ctDAO = new CategoryDAO();
        try {
            List<Category> categories = ctDAO.getAll();
            for (Category category : categories) {
                cbox.addItem(category.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modify(JTable table) {
        //Para agregar luego
    }

    @Override
    public void delete(JTable table) {
        CategoryDAO ctDAO = new CategoryDAO();
        int id = (int) table.getValueAt(table.getSelectedRow(), 0);
        try {
            ctDAO.delete(id);
            model.setRowCount(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

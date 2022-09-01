package com.logic.views;

import com.logic.dao.OrderDAO;
import com.logic.main.Functions;
import com.logic.model.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class OrderView implements View<JTable>{

    DefaultTableModel model;

    @Override
    public void addToDB(JPanel panel) {
        Order o = new Order();
        OrderDAO oDAO = new OrderDAO();
        List<Object> dataFromTxtField = Functions.valuesJPanel(panel.getComponents());
        if (dataFromTxtField == null) {
            JOptionPane.showMessageDialog(null, "Rellene los campos necesarios");
        } else {
            try {
                o.create(dataFromTxtField);
                if (oDAO.add(o) != 0) {
                    JOptionPane.showMessageDialog(null, "El producto ha sido agregado");
                }
                Functions.clearJTextFields(panel.getComponents());
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    @Override
    public void showQuery(JTable table) {
        OrderDAO oDAO = new OrderDAO();
        try {
            Object[] object;
            List<Order> orders = oDAO.getAll();
            model = (DefaultTableModel) table.getModel();
            for (Order o : orders) {
                object = o.valuesForTable();
                model.addRow(object);
            }
            table.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void modify(JTable table) {

    }

    @Override
    public void delete(JTable table) {

    }
}

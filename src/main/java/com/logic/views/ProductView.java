package com.logic.views;

import com.logic.dao.ProductDAO;
import com.logic.main.Functions;
import com.logic.model.Product;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class ProductView implements View<JTable>{

    DefaultTableModel model;

    @Override
    public void addToDB(JPanel panel) {
        Product pd = new Product();
        ProductDAO pdDAO = new ProductDAO();
        List<Object> dataFromTxtField = Functions.valuesJPanel(panel.getComponents());
        if (dataFromTxtField == null) {
            JOptionPane.showMessageDialog(null, "Rellene los campos necesarios");
        } else {
            try {
                pd.create(dataFromTxtField);
                if (pdDAO.add(pd) != 0) {
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
        ProductDAO pdDAO = new ProductDAO();
        try {
            Object[] object;
            List<Product> products = pdDAO.getAll();
            model = (DefaultTableModel) table.getModel();
            for (Product product : products) {
                object = product.valuesForTable();
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

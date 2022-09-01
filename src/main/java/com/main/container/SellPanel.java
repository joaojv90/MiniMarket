/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.main.container;

import com.logic.main.DB;
import com.logic.main.Design;
import com.logic.model.Order;
import com.logic.model.Person;
import com.logic.views.SellView;
import com.main.content.CustomersAdd;
import com.main.content.SellMain;

public class SellPanel extends javax.swing.JPanel {

    Person employee;
    SellView sellView = new SellView();

    public SellPanel(Person employee) {
        this.employee = employee;
        initComponents();
        Design.panelChange(pnlContent, new SellMain());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCF = new javax.swing.JButton();
        btnBill = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        pnlContent = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();

        setBackground(new java.awt.Color(236, 239, 244));
        setPreferredSize(new java.awt.Dimension(940, 590));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCF.setBackground(new java.awt.Color(208, 135, 112));
        btnCF.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btnCF.setForeground(new java.awt.Color(67, 76, 94));
        btnCF.setText("CONSUMIDOR F.");
        btnCF.setBorder(null);
        btnCF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCFMouseClicked(evt);
            }
        });
        btnCF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCFActionPerformed(evt);
            }
        });
        add(btnCF, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 530, 120, 40));

        btnBill.setBackground(new java.awt.Color(143, 188, 187));
        btnBill.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btnBill.setForeground(new java.awt.Color(67, 76, 94));
        btnBill.setText("FACTURA");
        btnBill.setBorder(null);
        btnBill.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBillMouseClicked(evt);
            }
        });
        add(btnBill, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 530, 120, 40));

        btnCancel.setBackground(new java.awt.Color(191, 97, 106));
        btnCancel.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(67, 76, 94));
        btnCancel.setText("CANCELAR");
        btnCancel.setBorder(null);
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelMouseClicked(evt);
            }
        });
        add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 530, 120, 40));

        pnlContent.setBackground(new java.awt.Color(236, 239, 244));
        pnlContent.setPreferredSize(new java.awt.Dimension(840, 500));
        pnlContent.setLayout(new javax.swing.BoxLayout(pnlContent, javax.swing.BoxLayout.LINE_AXIS));
        add(pnlContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 840, 460));

        lblTitle.setBackground(new java.awt.Color(46, 52, 64));
        lblTitle.setFont(new java.awt.Font("Roboto Medium", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(46, 52, 64));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sell.png"))); // NOI18N
        lblTitle.setText("VENDER");
        add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 20, 110, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btnCFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCFMouseClicked

    }//GEN-LAST:event_btnCFMouseClicked

    private void btnBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBillMouseClicked
         Design.panelChange(pnlContent, new CustomersAdd(employee));
    }//GEN-LAST:event_btnBillMouseClicked

    private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
        Design.panelChange(pnlContent, new SellMain());
    }//GEN-LAST:event_btnCancelMouseClicked

    private void btnCFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCFActionPerformed
        Order order = new Order();
        order.setIdEmployee(employee.getId());
        order.setIdCustomer(1);
        sellView.sellCustomer(order);
    }//GEN-LAST:event_btnCFActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton btnBill;
    private static javax.swing.JButton btnCF;
    private static javax.swing.JButton btnCancel;
    private static javax.swing.JLabel lblTitle;
    private static javax.swing.JPanel pnlContent;
    // End of variables declaration//GEN-END:variables
}

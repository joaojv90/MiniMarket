/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logic.main;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author mrmango
 */
public class Design {

  static Color bgColorReset;

  public static void menuAnimation(JPanel pnlMenu) {
    if (pnlMenu.getWidth() == 210 || pnlMenu.getHeight() == 137) {
      Thread th =
          new Thread() {
            @Override
            public void run() {
              try {
                if (pnlMenu.getHeight() == 137) {
                  for (int i = 137; i >= 0; i--) {
                    Thread.sleep(1);
                    pnlMenu.setSize(150, i);
                  }
                } else {
                  for (int i = 210; i >= 60; i--) {
                    Thread.sleep(1);
                    pnlMenu.setSize(i, 625);
                  }
                }
              } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
              }
            }
          };
      th.start();
    } else if (pnlMenu.getWidth() == 60 || pnlMenu.getHeight() == 0) {
      Thread th =
          new Thread() {
            @Override
            public void run() {
              try {
                if (pnlMenu.getHeight() == 0) {
                  for (int i = 0; i <= 137; i++) {
                    Thread.sleep(1);
                    pnlMenu.setSize(150, i);
                  }
                } else {
                  for (int i = 60; i <= 210; i++) {
                    Thread.sleep(1);
                    pnlMenu.setSize(i, 625);
                  }
                }
              } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
              }
            }
          };
      th.start();
    }
  }

  public static void panelChange(JPanel oldPanel, JPanel newPanel) {
    oldPanel.removeAll();
    oldPanel.add(newPanel);
    oldPanel.revalidate();
    oldPanel.repaint();
  }

  public static void resetColor(JPanel panel) {
    panel.setBackground(bgColorReset);
  }

  public static void optionsColor(JPanel panel) {
    bgColorReset = panel.getBackground();
    panel.setBackground(new java.awt.Color(129, 161, 193));
  }

  public static void clickOptions(
      JPanel btnMenu, JPanel btnActive, JPanel mainPanel, JPanel changePanel) {
    btnActive.setBackground(new java.awt.Color(216, 222, 233));
    btnMenu.setBackground(new java.awt.Color(143, 188, 187));
    bgColorReset = btnMenu.getBackground();
    panelChange(mainPanel, changePanel);
  }
}

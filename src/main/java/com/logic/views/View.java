package com.logic.views;

import javax.swing.*;

public interface View<T> {
  void addToDB(JPanel panel);

  void showQuery(T t);

  void modify(JTable table);

  void delete(JTable table);
}

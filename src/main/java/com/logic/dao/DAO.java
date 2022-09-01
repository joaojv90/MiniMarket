package com.logic.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

  int add(T t) throws SQLException;

  void delete(int id) throws SQLException;

  T get(int id) throws SQLException;

  List<T> getAll() throws SQLException;

  void update(T t) throws SQLException;
}

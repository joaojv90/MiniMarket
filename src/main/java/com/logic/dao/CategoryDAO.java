package com.logic.dao;

import com.logic.main.DBConnection;
import com.logic.model.Category;
import java.sql.*;
import java.util.*;

public class CategoryDAO implements DAO<Category> {

    static Connection con = DBConnection.getConnection();

    @Override
    public int add(Category ct) throws SQLException {
        String query = "INSERT INTO categories(name, description) VALUES (?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        Object[] values = ct.valuesForDB();
        ps.setString(1, (String) values[0]);
        ps.setString(2, (String) values[1]);
        return ps.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "delete from categories where idCategory =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public Category get(int id) throws SQLException {
        String query = "select * from categories where idCategory= ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        Category ct = new Category();
        ResultSet rs = ps.executeQuery();
        boolean check = false;
        while (rs.next()) {
            check = true;
            ct.getFromDB(rs);
        }
        if (check) {
            return ct;
        } else {
            return null;
        }
    }

    @Override
    public List<Category> getAll() throws SQLException {
        String query
                = "select * from categories";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Category> ls = new ArrayList<>();
        while (rs.next()) {
            Category ct = new Category();
            ct.getFromDB(rs);
            ls.add(ct);
        }
        return ls;
    }

    @Override
    public void update(Category ct) throws SQLException {
        String query
                = "update categories set name=?, description= ? where idCategory = ?";
        PreparedStatement ps = con.prepareStatement(query);
        Object[] values = ct.valuesForUpdateDB();
        for (int i = 0; i < values.length - 1; i++) {
            ps.setString(i + 1, (String) values[i]);
        }
        ps.setInt(3, (Integer) values[2]);
        ps.executeUpdate();
    }
}

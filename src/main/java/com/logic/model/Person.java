package com.logic.model;

import java.sql.*;
import java.util.*;

public abstract class Person {

    protected int id;
    protected String nui;
    protected String firstName;
    protected String lastName;
    protected String[] completeName;
    protected String address;
    protected String phone;
    protected String mail;

    protected Person() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNui() {
        return nui;
    }

    public void setNui(String nui) {
        this.nui = nui;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String[] getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String[] completeName) {
        this.completeName = completeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public abstract void getFromDB(ResultSet rs) throws SQLException;
    public abstract Object[] valuesForTable();
    public abstract void create(List<Object> values);
    public abstract Object[] valuesForDB();
    public abstract void update(List<Object> values);
    public abstract Object[] valuesForUpdateDB();
}

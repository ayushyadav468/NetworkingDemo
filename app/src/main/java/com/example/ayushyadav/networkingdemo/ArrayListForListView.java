package com.example.ayushyadav.networkingdemo;

/**
 * Created by ayushyadav on 12/03/18.
 */

public class ArrayListForListView {

    private int id;
    private String name;
    private String userName;
    private String emailId;

    public ArrayListForListView(int id, String name, String userName, String emailId) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.emailId = emailId;
    }

    public ArrayListForListView(String name, String userName, String emailId) {
        this.name = name;
        this.userName = userName;
        this.emailId = emailId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}

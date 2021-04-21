package com.example.myapplication.ui;

public class User {

    private String email;
    private String password;

    public User(String n, String m) {
        email = n;
        password = m;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}

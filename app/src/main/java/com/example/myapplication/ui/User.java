package com.example.myapplication.ui;

public class User {

    private String user;
    private String password;
    private String name;
    private int age;
    private int height;
    private int weight;

    public User(String l, String m, String n, int o, int p, int q) {
        user = l;
        password = m;
        name = n;
        age = o;
        height = p;
        weight = q;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

}

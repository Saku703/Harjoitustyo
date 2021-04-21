package com.example.myapplication.ui;

import android.app.Activity;

import java.util.ArrayList;

public class UserDatabase extends Activity{

    private static String newEmail;
    private static String newPassword;
    //String newEmail;
    //String newPassword;

    //public User user = new User();
    public ArrayList<UserDatabase> array = new ArrayList<UserDatabase>();

    public UserDatabase(String email, String password) {
    }

    public void fragmentCalls(String email, String password) {
        newEmail = email;
        newPassword = password;

        UserDatabase temp1 = new UserDatabase(newEmail, newPassword);
        array.add(temp1);
    }

    //private static UserDatabase base = new UserDatabase(email, password);

    public void createUser() {
        //System.out.println("yks");
        //System.out.println(newEmail);
        //System.out.println(newPassword);
        UserDatabase temp1 = new UserDatabase("niilo@gmail.com", "1234");
        array.add(temp1);

        System.out.println("1");
        System.out.println(array.get(0));
        System.out.println("2");
    }

}

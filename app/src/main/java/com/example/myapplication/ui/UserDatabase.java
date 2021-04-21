package com.example.myapplication.ui;

import android.app.Activity;

import java.util.ArrayList;

public class UserDatabase extends Activity{

    private String email;
    private String password;
    private String newEmail;
    private String newPassword;

    //public User user = new User();
    //public ArrayList<UserDatabase> array = new ArrayList<UserDatabase>();

    //initialising the static user array list
    public static User[] array;
    static {
        array = new User[2];
    }

    public UserDatabase(String email, String password) {
    }

    public void fragmentCalls(String email, String password) {
        int usersCount = 0;
        newEmail = email;
        newPassword = password;

        User temp1 = new User(newEmail, newPassword);
        array[usersCount] = (temp1);

        usersCount++;
        System.out.println(usersCount);
    }

    //private UserDatabase base = new UserDatabase(email, password);

    public static String createUser(String newEmail, String newPassword) {
        String returnStatement = "";
        int usersCount = 0;
        int passMinLenght = 12; //minimum lenght given in the final project instructions
        int passLenght = newPassword.length();

        /*testing all of the requirements for a good password are met. they are:
        Minimum of 12 characters.
        Must contain at least 1 number, 1 capital letter, 1 lower case letter and 1 special character. */
        if (passLenght < passMinLenght) {
            returnStatement = String.format("Salasana on liian lyhyt, merkkjä %d/12", passLenght);
        } else if (PasswordTester.containsNumber(newPassword) == false) {
            returnStatement = "Salasana ei sisältänyt numeroa";
        } else if (PasswordTester.containsCapitalLetter(newPassword, passLenght) == false) {
            returnStatement = "Salasana ei sisältänyt isoa kirjainta";
        } else if (PasswordTester.containsSmallLetter(newPassword, passLenght) == false) {
            returnStatement = "Salasana ei sisältänyt pientä kirjainta";
        } else if (PasswordTester.containsSpecialCharacter(newPassword, passLenght) == false) {
            returnStatement = "Salasana ei sisältänyt erikoismerkkiä";
        } else {
            User temp = new User(newEmail, newPassword);
            array[usersCount] = (temp);

            //System.out.println(array[usersCount]);
            System.out.println(array[usersCount].getEmail());
            System.out.println(array[usersCount].getPassword());

            //usersCount++;
            //System.out.println(usersCount);

            returnStatement = "Tili luotu. Kirjaudu sisään.";
        }

        return returnStatement;
    }

    public static String logIn(String email, String password) {
        String returnStatement = "";
        int i = 0;

        //System.out.println("vanhat " + array[0].getEmail() + ", " + array[0].getPassword());
        //System.out.println("uudet " + email + ", " + password);

        if ((email.equals(array[i].getEmail()) == true) && (password.equals(array[i].getPassword()) == true)) {
            //loginInfo.setText("Olet kirjautunut sisään.");
            //System.out.println("tosi");
            returnStatement = "Olet kirjautunut sisään!";
        } else {
            //System.out.println("epätosi");
            returnStatement = "Sähköposti tai salasana on väärä.";
        }

        /*
        for (i = 0; i < array.length; i++) {
            if ((email.equals(array[i].getEmail()) == true) && (password.equals(array[i].getPassword()) == true)) {
                //loginInfo.setText("Olet kirjautunut sisään.");
                returnStatement = "Olet kirjautunut sisään!";
            } else if ((i == (array.length - 1)) && (email.equals(array[i].getEmail()) == false) && (password.equals(array[i].getPassword()) == false)) {
                //loginInfo.setText("Sähköposti tai salasana on väärä.");
                returnStatement = "Sähköposti tai salasana on väärä.";
                //((email.equals(array[i].getEmail()) == false) && (password.equals(array[i].getPassword()) == false))
            }
        } */


        return returnStatement;
    }

}

//System.out.println("yks");
//System.out.println(newEmail);
//System.out.println(newPassword);
        /*
        UserDatabase temp1 = new UserDatabase("niilo@gmail.com", "1234");
        array.add(temp1);

        System.out.println("1");
        System.out.println(array.get(0));
        System.out.println("2"); */


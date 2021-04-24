package com.example.myapplication.ui;

public class UserInputTester {
    String  returnStatement;

    public UserInputTester() {
        returnStatement = "";
    }

    //Validating the user input. Every field of create account should be filled
    public String createAccountInputTester(String newUser, String newPassword, String name,
                                   String age, String height, String weight, String sex) {

        if (newUser.equals("") == true) {
            returnStatement = "Käyttäjätunnus on pakollinen";
        } else if (newPassword.equals("") == true) {
            returnStatement = "Salasana on pakollinen";
        } else if (name.equals("") == true) {
            returnStatement = "Nimi on pakollinen";
        } else if (age.equals("") == true) {
            returnStatement = "Ikä on pakollinen";
        } else if (height.equals("") == true) {
            returnStatement = "Pituus on pakollinen";
        } else if (weight.equals("") == true) {
            returnStatement = "Paino on pakollinen";
        } else {
            returnStatement = "OK";
        }

        return returnStatement;
    }

    //Validating the user input. Every field of log in should be filled
    public String logInInputTester (String user, String password) {
        if (user.equals("") == true) {
            returnStatement = "Käyttäjätunnus on pakollinen";
        } else if (password.equals("") == true) {
            returnStatement = "Salasana on pakollinen";
        } else {
            returnStatement = "OK";
        }

        return returnStatement;
    }
}

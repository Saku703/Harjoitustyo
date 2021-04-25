package com.example.myapplication.ui;

import java.util.regex.Pattern;

public class PasswordTester {
    boolean result;
    String regex;
    char ch;

    //Password tester is used to check if the password meets the criteria
    public PasswordTester() {
        result = false;
        regex = "";
        char ch;
    }

    public boolean containsNumber (String password) {
        regex = ".*\\d.*";
        Pattern pattern = Pattern.compile(regex);
        result = pattern.matcher(password).matches();
        return result;
    }

    public boolean containsCapitalLetter (String password, int passLenght) {
        for (int i=0; i < passLenght; i++) {
            ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                result = true;
            }
        }
        return result;
    }

    public boolean containsSmallLetter (String password, int passLenght) {
        for (int i=0; i < passLenght; i++) {
            ch = password.charAt(i);
            if (Character.isLowerCase(ch)) {
                result = true;
            }
        }
        return result;
    }

    public boolean containsSpecialCharacter (String password) {
        //The special characters required are selected based on usually accepted special characters
        regex = ".*(?=.[-_!@#$%^&*+,:;<=>?\\\\]).*";
        Pattern pattern = Pattern.compile(regex);
        result = pattern.matcher(password).matches();
        return result;
    }
}

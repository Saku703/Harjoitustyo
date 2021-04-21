package com.example.myapplication.ui;

import java.util.regex.Pattern;

public class PasswordTester {

    public static boolean containsNumber (String password) {
        boolean result = false;
        //String regex = "(.)(\\d)(.)*";
        String regex = ".*\\d.*";
        Pattern pattern = Pattern.compile(regex);
        result = pattern.matcher(password).matches();
        return result;
    }

    public static boolean containsCapitalLetter (String password, int passLenght) {
        boolean result = false;
        char ch;

        for (int i=0; i < passLenght; i++) {
            ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                result = true;
            }
        }
        return result;
    }

    public static boolean containsSmallLetter (String password, int passLenght) {
        boolean result = false;
        char ch;

        for (int i=0; i < passLenght; i++) {
            ch = password.charAt(i);
            if (Character.isLowerCase(ch)) {
                result = true;
            }
        }
        return result;
    }

    public static boolean containsSpecialCharacter (String password, int passLenght) {
        boolean result = false;
        //String regex = ".*[!@#$%^&(){}:;'<>,?/~`_+-=|\\].*";
        String regex = ".*[!@#$%^&(){}:;'<>,?/~`_+-=|\\\\].*";
        Pattern pattern = Pattern.compile(regex);
        result = pattern.matcher(password).matches();
        return result;
    }
}

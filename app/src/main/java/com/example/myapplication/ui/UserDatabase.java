package com.example.myapplication.ui;

import android.app.Activity;

import java.util.ArrayList;

import static java.lang.Math.round;

public class UserDatabase extends Activity{

    private String user;
    private String password;
    private String newUser;
    private String newPassword;

    //initialising the static user array list
    public static User[] array;
    static {
        array = new User[6];
    }

    public static String createUser(String newUser, String newPassword, String name, int age, int height, int weight, String selectedSex) {
        String returnStatement = "";
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
        } else if (PasswordTester.containsSpecialCharacter(newPassword) == false) {
            returnStatement = "Salasana ei sisältänyt erikoismerkkiä";
        } else {
            User temp = new User(newUser, newPassword, name, age, height, weight, selectedSex);
            JSONWriter.basicInfoJSON(newUser, newPassword, name, age, height, weight, selectedSex); //lisäys lokitietoihin
            int usersCount = usersCount() - 1;
            array[usersCount] = (temp);
            //System.out.println(array[usersCount].getUser());
            //System.out.println(array[usersCount].getPassword());
            //System.out.println(array[usersCount].getAge());
            //System.out.println(array[usersCount].getSex());
            System.out.println("käyttäjiä" + usersCount);
            returnStatement = "Tili luotu. Kirjaudu sisään.";
        }
        return returnStatement;
    }

    public static String logIn(String user, String password) {
        String returnStatement = "";
        int i = 0;

        if (isArrayEmpty() == true) {
            returnStatement = "Yhtään tiliä ei ole vielä luotu. Luo tili.";
        } else if ((user.equals(array[i].getUser()) == true) && (password.equals(array[i].getPassword()) == true)) {
            returnStatement = "Olet kirjautunut sisään!";
        } else {
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

    private static boolean isArrayEmpty () {
        boolean empty = true;

        for (int i=0; i < array.length; i++) {
            if (array[i] != null) {
                empty = false;
                break;
            }
        }
        return empty;
    }

    public static String userDailyProteinGoal (double activityFactor) {
        String strGoal = "";
        int index = 0; //there can only be one user, index is always 0

        String sex = array[index].getSex();
        double dailyCalories = 0;
        double dailyProtein = 0;

        //Calorine goal formula is Harris-Benedict BMR - link below
        //https://en.wikipedia.org/wiki/Harris%E2%80%93Benedict_equation
        if (sex.equals("nainen") == true) {
            dailyCalories = 655 + (9.563 * array[index].getWeight()) + (1.850 * array[index].getHeight()) - (4.676 * array[index].getAge());
        } else if (sex.equals("mies") == true) {
            dailyCalories = 66.5 + (13.75 * array[index].getWeight()) + (5.003 * array[index].getHeight()) - (6.755 * array[index].getAge());
        }

        /*The daily protein intake is calculated as a percentage of daily calorine goal.
        The percentages are based on Harvard nutrition guidelines.
        https://www.health.harvard.edu/nutrition/when-it-comes-to-protein-how-much-is-too-much
        */

        dailyProtein = (dailyCalories * activityFactor) / 4; //one gram of protein is 4 kcal
        dailyProtein = round(dailyProtein);
        strGoal = Double.toString(dailyProtein);

        return strGoal;
    }

    public static int usersCount () {
        int x = 0;
        x++;
        return x;
    }

    public static String getUserName() {
        String userName = array[0].getName();
        return userName;
    }

    public static boolean isUserArrayEmpty() {
        boolean returnStatement = true;

        if (isArrayEmpty() == true) {
            returnStatement = true;
        } else if (isArrayEmpty() == false) {
            returnStatement = false;
        }

        return returnStatement;
    }
}




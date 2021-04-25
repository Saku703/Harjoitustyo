package com.example.myapplication.ui;

import android.app.Activity;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static java.lang.Math.round;

public class UserDatabase extends Activity{

    static Context context = null;

    private String user;
    private String password;
    private String newUser;
    private String newPassword;

    public UserDatabase() {
        user = "";
        password = "";
        newUser = "";
        newPassword = "";
    }

    //initialising the static user array
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
        Must contain at least 1 number, 1 capital letter, 1 lower case letter and 1 special character.
        Also testing that the username and name are of a reasonable lenght.*/
        PasswordTester passwordTester = new PasswordTester();
        if (passLenght < passMinLenght) {
            returnStatement = String.format("Salasana on liian lyhyt, merkkjä %d/12", passLenght);
        } else if (passwordTester.containsNumber(newPassword) == false) {
            returnStatement = "Salasana ei sisältänyt numeroa";
        } else if (passwordTester.containsCapitalLetter(newPassword, passLenght) == false) {
            returnStatement = "Salasana ei sisältänyt isoa kirjainta";
        } else if (passwordTester.containsSmallLetter(newPassword, passLenght) == false) {
            returnStatement = "Salasana ei sisältänyt pientä kirjainta";
        } else if (passwordTester.containsSpecialCharacter(newPassword) == false) {
            returnStatement = "Salasana ei sisältänyt erikoismerkkiä";
        } else if (newUser.length() < 8) {
            returnStatement = "Käyttäjätunnuksen tulee olla väh. 8 merkkiä.";
        } else if (name.length() < 3) {
            returnStatement = "Nimen tulee olla väh. 3 merkkiä.";
        } else {
            User temp = new User(newUser, newPassword, name, age, height, weight, selectedSex);
            JSONWriter.basicInfoJSON(newUser, newPassword, name, age, height, weight, selectedSex);
            int usersCount = usersCount() - 1;
            array[usersCount] = (temp);
            System.out.println(array[usersCount].getUser());
            System.out.println(array[usersCount].getPassword());
            System.out.println(array[usersCount].getAge());
            System.out.println(array[usersCount].getSex());
            System.out.println("käyttäjiä" + usersCount);
            returnStatement = "Tili luotu. Kirjaudu sisään.";
        }
        return returnStatement;
    }

    public static String logIn(String user, String password) {
        String returnStatement = "";
        int i = 0;
        InputStream ins = null;
        context = getContext.getContextForFile(context);
        try {
            ins = context.openFileInput("jsonFile");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(ins));

        try {
            String fld = br.readLine();  //fld = full log data
            JSONObject log = new JSONObject(fld);
            JSONArray user_data = log.getJSONArray("user_info");
            String log_line_one = String.valueOf((user_data.getJSONObject(0)));
            JSONObject first_line = new JSONObject(log_line_one);
            String userFromLog = first_line.getString("user_user");
            String passwordFromLog = first_line.getString("user_password");
            
            if (log.isNull("user_info")) {
                returnStatement = "Yhtään tiliä ei ole vielä luotu. Luo tili.";
            } else if ((user.equals(userFromLog) == true) && (password.equals(passwordFromLog))) {
                returnStatement = "Olet kirjautunut sisään!";
                String nameFromLog = first_line.getString("user_name");
                int ageFromLog = first_line.getInt("user_age");
                int heightFromLog = first_line.getInt("user_height");
                int weightFromLog = first_line.getInt("user_weight");
                String sexFromLog = first_line.getString("user_sex");
            } else {
                returnStatement = "Sähköposti tai salasana on väärä.";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        double dailyProtein;

        //Calorie goal formula is Harris-Benedict BMR - link below
        //https://en.wikipedia.org/wiki/Harris%E2%80%93Benedict_equation
        if (sex.equals("nainen") == true) {
            dailyCalories = 655 + (9.563 * array[index].getWeight()) + (1.850 * array[index].getHeight()) - (4.676 * array[index].getAge());
        } else if (sex.equals("mies") == true) {
            dailyCalories = 66.5 + (13.75 * array[index].getWeight()) + (5.003 * array[index].getHeight()) - (6.755 * array[index].getAge());
        }

        /*The daily protein intake is calculated as a percentage of daily calorie goal.
        The percentages are based on Harvard nutrition guidelines: 20 % for muscle build, 10 % for regualr people.
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




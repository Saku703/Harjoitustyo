package com.example.myapplication.ui;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class JSONWriter {

    public static Context context = null;

    private static JSONObject log = new JSONObject();
    private static JSONArray basic_list = new JSONArray();
    private static JSONArray ingredient_list = new JSONArray();

    public static void basicInfoJSON(String user_user, String user_password, String user_name, int user_age, int user_height, int user_weight, String user_sex){
        JSONObject basic_info_variable = new JSONObject();
        //populating JSONObject with basic user information
        try {
            basic_info_variable.put("user_user", user_user);
            basic_info_variable.put("user_password", user_password);
            basic_info_variable.put("user_name", user_name);
            basic_info_variable.put("user_age", user_age);
            basic_info_variable.put("user_height", user_height);
            basic_info_variable.put("user_weight", user_weight);
            basic_info_variable.put("user_sex", user_sex);
            basic_list.put(basic_info_variable);
            log.put("user_info", basic_list);
            //writing to file
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("jsonFile", Context.MODE_PRIVATE));
            osw.write(log.toString());
            osw.close();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateJSON(int amount_of_food, String food_type){
        JSONObject ingredient = new JSONObject();
        //adds the name of the food and amount eaten to JSONArray every time user inputs them
        try {
            ingredient.put("food_item", food_type);
            ingredient.put("food_amount", amount_of_food);
            ingredient_list.put(ingredient);
            log.put("log_data", ingredient_list);
            System.out.println(log.toString());
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("jsonFile", Context.MODE_PRIVATE));
            osw.write(log.toString());
            osw.close();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }
}

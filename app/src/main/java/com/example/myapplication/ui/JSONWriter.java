package com.example.myapplication.ui;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class JSONWriter {

    //public static Context context = null;
    private static Context context = null;


    public static JSONObject log = new JSONObject();

    //private static JSONObject log = new JSONObject();
    private static JSONObject beginning_log = new JSONObject();

    private static JSONArray basic_list = new JSONArray();
    private static JSONArray beginning_basic_list = new JSONArray();
    private static JSONArray ingredient_list = new JSONArray();
    private static JSONArray beginning_ingredient_list = new JSONArray();

    public static void fileTester() {
        InputStream ins = null;
        context = getContext.getContextForFile(context);
        try {
            ins = context.openFileInput("jsonFile");
        } catch (NullPointerException | FileNotFoundException e) {
            System.out.println("Ei valmiita lokitietoja.\n");
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(ins));
        try {
            String fld = br.readLine();  //fld = full log data
            beginning_log = new JSONObject(fld);
            System.out.println(beginning_log.toString());

            if (beginning_log.isNull("user_info")){
                System.out.println("Ei valmiita käyttäjätietoja.\n");
            } else {
                JSONArray user_data = beginning_log.getJSONArray("user_info");
                String log_line_one = String.valueOf((user_data.getJSONObject(0)));
                JSONObject first_line = new JSONObject(log_line_one);
                basicInfoJSON(first_line.getString("user_user"), first_line.getString("user_password"),
                        first_line.getString("user_name"), first_line.getInt("user_age"), first_line.getInt("user_height"),
                        first_line.getInt("user_weight"), first_line.getString("user_sex"));
            }
            if (beginning_log.isNull("log_data")){
                System.out.println("Ei aiempia lokipäivityksiä.\n");
            } else {
                JSONArray data = beginning_log.getJSONArray("log_data");
                int x = 0;
                String log_line = null;
                String food_info = null;
                int food_number = 0;
                while ((log_line= String.valueOf((data.getJSONObject(x)))) != null) {
                    JSONObject obj = new JSONObject(log_line);
                    food_info = obj.getString("food_item");
                    food_number = obj.getInt("food_amount");
                    updateJSON(food_number, food_info);
                    x += 1;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONWriter(Context context){
        this.context = context;
    }

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
            context = getContext.getContextForFile(context);
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
            System.out.println(log.toString());
            log.put("log_data", ingredient_list);
            System.out.println(log.toString());
            //write to file
            context = getContext.getContextForFile(context);
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("jsonFile", Context.MODE_PRIVATE));
            osw.write(log.toString());
            osw.close();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

}

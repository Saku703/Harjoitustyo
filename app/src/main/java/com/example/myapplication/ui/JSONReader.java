package com.example.myapplication.ui;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSONReader {
    static TextView logtext;
    static Context context = null;

    public static String readJSON(View v){
        String json = getJSON();  //gets JSON information
        return json;
    }

    public static String getJSON(){
        String response = null;
        StringBuilder sb = new StringBuilder();  //creates StringBuilder sb that can be expanded

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
            if (log.isNull("user_info")){
                sb.append("Ei käyttäjätietoja.\n");
            } else {
                JSONArray user_data = log.getJSONArray("user_info");
                String log_line_one = String.valueOf((user_data.getJSONObject(0)));
                JSONObject first_line = new JSONObject(log_line_one);
                sb.append("Käyttäjän tiedot: ");
                sb.append("\nKäyttäjänimi: ").append(first_line.getString("user_user"));
                sb.append("\nSalasana: ").append(first_line.getString("user_password"));
                sb.append("\nNimi: ").append(first_line.getString("user_name"));
                sb.append("\nIkä: ").append(first_line.getInt("user_age"));
                sb.append("\nPituus: ").append(first_line.getInt("user_height"));
                sb.append("\nPaino:: ").append(first_line.getInt("user_weight"));
                sb.append("\nSukupuoli: ").append(first_line.getString("user_sex")).append("\n");
            }

            if (log.isNull("log_data")){
                sb.append("Ei lokipäivityksiä.\n");
            } else {
                JSONArray data = log.getJSONArray("log_data");

                int x = 0;
                String log_line = null;
                //using same variables for both food and weather inputs
                String food_info = null;
                int food_number = 0;
                while ((log_line= String.valueOf((data.getJSONObject(x)))) != null) {
                    JSONObject obj = new JSONObject(log_line);
                    food_info = obj.getString("food_item");
                    food_number = obj.getInt("food_amount");
                    sb.append(x+1);
                    sb.append(". syöte: ");
                    sb.append(food_info);
                    sb.append(" ; ");
                    sb.append(food_number);
                    sb.append("\n");
                    x += 1;
                }
            }
            ins.close();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        response=sb.toString();
        return response;
    }
}

package com.example.myapplication.ui;

import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.myapplication.ui.JSONWriter.log;

public class JSONReader {

    static TextView logtext;

    public static String readJSON(View v){
        String json = getJSON();
        System.out.println(json);
        return json;
    }

    public static String getJSON(){
        String response = null;
        StringBuilder sb = new StringBuilder();

        ////////////////////////////////////////////////
        /*JSONObject esimerkki = new JSONObject();
        JSONArray list = new JSONArray();
        JSONObject ingredient = new JSONObject();
        JSONObject ingredient2 = new JSONObject();
        JSONObject ingredient3 = new JSONObject();
        try {
            ingredient.put("food_item", "porkkana");
            ingredient.put("food_amount", 300);
            list.put(ingredient);
            esimerkki.put("log_data", list);
            ingredient2.put("food_item", "jauheliha");
            ingredient2.put("food_amount", 200);
            list.put(ingredient2);
            esimerkki.put("log_data", list);
            ingredient3.put("food_item", "possu");
            ingredient3.put("food_amount", 155);
            list.put(ingredient3);
            esimerkki.put("log_data", list);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        ////////////////////////////////////////////////

        try {
            if (log.getJSONArray("user_info") != null){
                JSONArray user_data = log.getJSONArray("user_info");
                String log_line_one = String.valueOf((user_data.getJSONObject(0)));
                JSONObject first_line = new JSONObject(log_line_one);
                sb.append("Käyttäjän tiedot: ");
                sb.append(first_line.getString("user_user"));
                sb.append(", ").append(first_line.getString("user_password"));
                sb.append(", ").append(first_line.getString("user_name"));
                sb.append(", ").append(first_line.getInt("user_age"));
                sb.append(", ").append(first_line.getInt("user_height"));
                sb.append(", ").append(first_line.getInt("user_weight"));
                sb.append(", ").append(first_line.getString("user_sex")).append("\n");
            } else {System.out.println("Ei käyttäjätietoja syötetty.\n");}


            //JSONArray data = esimerkki.getJSONArray("log_data");

            JSONArray data = log.getJSONArray("log_data");

            int x = 0;
            String log_line = null;
            String food_info = null;
            int food_number = 0;
            while ((log_line= String.valueOf((data.getJSONObject(x)))) != null) {
                JSONObject obj = new JSONObject(log_line);
                food_info = obj.getString("food_item");
                food_number = obj.getInt("food_amount");
                System.out.println(food_info + "--" + food_number + "\n");
                sb.append(x+1);
                sb.append(". syöte: ");
                sb.append(food_info);
                sb.append("--");
                sb.append(food_number);
                sb.append("\n");
                x += 1;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        response=sb.toString();
        return response;
    }
}

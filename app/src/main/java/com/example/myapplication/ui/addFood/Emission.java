package com.example.myapplication.ui.addFood;

import com.example.myapplication.ui.JSONWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Emission {
    String co2_value;
    int food_value;
    int arrayIndex;
    String consumedProtein;

    public Emission() {
        co2_value = "";
        food_value = 0;
        arrayIndex = 0;
        consumedProtein = "";
    }

    public String readXML(int value, String food_choice) {
        //Food value selected in the AddFoodFragment's spinner
        if(0 <= value && value <= 200) {
            JSONWriter.updateJSON(value, food_choice);
            this.food_value = value;
            if(food_choice == "Nauta") {
                food_choice = "beef";
            } else if(food_choice == "Kala") {
                food_choice = "fish";
            } else if(food_choice == "Porsas") {
                food_choice = "porkPoultryLevel";
            }else if(food_choice == "Kana") {
                food_choice = "porkPoultryLevel";
            }else if(food_choice == "Juusto") {
                food_choice = "cheese";
            }else if(food_choice == "Riisi") {
                food_choice = "rice";
            }

            try {
                //http-call to the Ilmastodieetti API and parsing the response
                String url = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/FoodCalculator?query.diet=omnivore";
                StringBuilder builder = new StringBuilder(url);
                CharSequence chSeq = "&query."+ food_choice +"Level=" + value;
                builder.append(chSeq);

                URL obj = new URL(builder.toString());
                HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

                BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                String outputString = response.toString();
                String[] tokensAll = outputString.split(",");

                String totalString = tokensAll[4];
                String[] tokensTotal = totalString.split(":");
                String total = tokensTotal[1];
                total = total.substring(0, total.length() - 1).trim();
                float f = Float.parseFloat(total);
                int b = (int) Math.round(f);
                this.co2_value = Integer.toString(b);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } return co2_value;
    }
}

package com.example.myapplication.ui.addFood;

import android.text.Editable;
import android.widget.EditText;

import com.example.myapplication.ui.ProteinCalculator;

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
        /*int intFromUser = Integer.parseInt(value.getText().toString());
        String selected_Food = food_choice.getSelectedItem().toString();*/
        if(0 <= value && value <= 200) {
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
                String url = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/FoodCalculator?query.diet=omnivore";
                StringBuilder builder = new StringBuilder(url);
                CharSequence chSeq = "&query."+ food_choice +"Level=" + value;
                System.out.println(chSeq);
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
                System.out.println(outputString);
                String[] tokensAll = outputString.split(",");

                String totalString = tokensAll[4];
                String[] tokensTotal = totalString.split(":");
                String total = tokensTotal[1];
                total = total.substring(0, total.length() - 1).trim();
                this.co2_value = total;
                //this.co2_value = Float.parseFloat(total);
                //System.out.println(co2_value);
                //System.out.println(co2_value);
                //textview_CO2.setText(co2_value.toString());
                //return co2_value;

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //return 0;
            //System.out.println("Anna luku 0 ja 200 väliltä!");
        }
        return co2_value;
    }

    public String getConsumedProtein(int value, String food_choice) {

        if(0 <= value && value <= 200) {
            if (food_choice == "Nauta") {
                arrayIndex = 0;
                consumedProtein = ProteinCalculator.countConsumedProtein(value, arrayIndex);
            } else if (food_choice == "Kala") {
                arrayIndex = 1;
                consumedProtein = ProteinCalculator.countConsumedProtein(value, arrayIndex);
            } else if (food_choice == "Porsas") {
                arrayIndex = 2;
                consumedProtein = ProteinCalculator.countConsumedProtein(value, arrayIndex);
            } else if (food_choice == "Kana") {
                arrayIndex = 3;
                consumedProtein = ProteinCalculator.countConsumedProtein(value, arrayIndex);
            } else if (food_choice == "Juusto") {
                arrayIndex = 4;
                consumedProtein = ProteinCalculator.countConsumedProtein(value, arrayIndex);
            } else if (food_choice == "Riisi") {
                arrayIndex = 5;
                consumedProtein = ProteinCalculator.countConsumedProtein(value, arrayIndex);
            }
        }
        return consumedProtein;
    }
}

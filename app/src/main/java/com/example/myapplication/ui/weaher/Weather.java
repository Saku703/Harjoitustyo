package com.example.myapplication.ui.weaher;

import org.json.JSONException;
import org.json.JSONObject;

public class Weather {

    public Weather() {

    }
    public static void getWeather(String str) {
        if(str != null) {
            try {
                JSONObject jsonObject = new JSONObject(str);
                String all = jsonObject.toString();
                // Tuossa "all" on se koko rivi niitä säätietoja
                System.out.println(all);
                /*String[] tokensAll = all.split(",");
                System.out.println(tokensAll[0]);
                String[] temperature1 = tokensAll[0].split(":");
                System.out.println(temperature1[1]);
                String weather = temperature1[1];
                weather = weather.substring(1, weather.length() - 1);
                System.out.println(weather);*/
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

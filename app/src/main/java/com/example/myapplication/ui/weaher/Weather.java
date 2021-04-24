package com.example.myapplication.ui.weaher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.myapplication.ui.JSONWriter.log;

public class Weather {

    public Weather() {

    }
    public static void getWeather(String str) {
        if(str != null) {
            StringBuilder sb = new StringBuilder();
            try {
                JSONObject fwi = new JSONObject(str); //fwi = full_weather_info
                String all = fwi.toString();
                System.out.println(all);
                String day0_temp = fwi.getString("temperature");
                String day0_wind = fwi.getString("wind");
                String day0_descript = fwi.getString("description");
                sb.append("Tänään: ").append(day0_temp).append(", ").append(day0_wind).append(".\nPäivän kuvaus: ");
                sb.append(day0_descript).append(".\n");

                JSONArray forecast_data = fwi.getJSONArray("forecast");
                int x = 0;
                String forecast_day = null;
                while (x <3) {
                    forecast_day= String.valueOf((forecast_data.getJSONObject(x)));
                    JSONObject obj = new JSONObject(forecast_day);
                    String day1_temp = obj.getString("temperature");
                    String day1_wind = obj.getString("wind");
                    if (x == 0){
                        sb.append("Huomisen sää: ");
                    }
                    else if (x == 1){
                        sb.append("Ylihuomisen sää: ");
                    }
                    else if (x == 2){
                        sb.append("Sää kahden päivän päästä: ");
                    }
                    sb.append(day1_temp).append(" lämmintä, tuulee ").append(day1_wind).append(".\n");
                    x += 1;
                }

                String full_weather_report=sb.toString();
                System.out.println(full_weather_report); //Stringi sisältää kaikki parsitut ilmastojutut

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

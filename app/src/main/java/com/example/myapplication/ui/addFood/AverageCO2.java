package com.example.myapplication.ui.addFood;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.Math.round;

public class AverageCO2 {

    float averageCO2Value;
    String returnStatement;

    public AverageCO2(){

        averageCO2Value = 0;
        returnStatement = "";

    }

    public String getAverageCO2() {
        try {
            String url = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/FoodCalculator?query.diet=omnivore&query.beefLevel=100&query.fishLevel=100&query.porkPoultryLevel=100&query.dairyLevel=100&query.cheeseLevel=100&query.riceLevel=100&query.eggLevel=100&query.winterSaladLevel=100";

            URL obj = new URL(url);
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
            this.averageCO2Value = Math.round(Float.parseFloat(total));
            returnStatement = Float.toString(averageCO2Value);

        } catch (IOException e) {
            e.printStackTrace();
        }
     return returnStatement;
    }
}

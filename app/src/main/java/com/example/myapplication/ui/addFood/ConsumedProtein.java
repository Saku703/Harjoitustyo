package com.example.myapplication.ui.addFood;

import com.example.myapplication.ui.JSONWriter;
import com.example.myapplication.ui.ProteinCalculator;

public class ConsumedProtein {
    int arrayIndex;
    String consumedProtein;

    public ConsumedProtein() {
        arrayIndex = 0;
        consumedProtein = "";
    }

    public String getConsumedProtein(int value, String food_choice) {
        //Calling the protein calculator -method based on the selected food.
        //Consumed protein amount is returned as a String.
        if(0 <= value && value <= 200) {
            JSONWriter.updateJSON(value, food_choice);
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

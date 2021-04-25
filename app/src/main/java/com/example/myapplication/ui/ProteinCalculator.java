package com.example.myapplication.ui;

import static java.lang.Math.round;

public class ProteinCalculator {

    //Initialising the static protein array
    public static Protein[] array;
    static {
        array = new Protein[10];
    }

    public static void populateArray () {
        /*Source for protein amounts is Fineli, which is Finland's national database for foods and their
        nutritional information. The database is provided by THL (Finnish Institute sor Health and Welfare).
        The amount of protein is per 1g to make it convenient for further calculations.
        https://fineli.fi/fineli/fi/index*/
        Protein temp1 = new Protein("beef", 0.169);
        Protein temp2 = new Protein("fish", 0.189);
        Protein temp3 = new Protein("pork", 0.189);
        Protein temp4 = new Protein("chicken", 0.202);
        Protein temp5 = new Protein("cheese", 0.234);
        Protein temp6 = new Protein("rice", 0.081);

        array[0] = (temp1);
        array[1] = (temp2);
        array[2] = (temp3);
        array[3] = (temp4);
        array[4] = (temp5);
        array[5] = (temp6);
    }

    public static String countConsumedProtein (int consumedAmount, int index) {
        double protein = 0.0;

        protein = array[index].getProtein() * consumedAmount;
        protein = round(protein);
        String strConsumed = Double.toString(protein);

        return strConsumed;
    }


}

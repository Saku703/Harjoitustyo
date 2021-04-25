package com.example.myapplication.ui.addFood;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.myapplication.R;
import com.example.myapplication.ui.UserDatabase;
import java.util.ArrayList;


public class AddFoodFragment extends Fragment {

    //Collecting user input
    private TextView textView_Food;
    private Spinner spinner_Foods;
    private EditText userInputAPI;
    private Button button_API;
    private EditText userInputProtein;
    private Button button_protein;

    //CO2 emissions
    private TextView textview_CO2;
    private String averageCO2Value;
    private TextView infoAverageCO2;

    //protein
    private TextView infoForUserX;
    private TextView textView_protein;
    private TextView textView_proteinGoal;
    private CheckBox activityLevel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_addfood, container, false);

        textView_Food = (TextView) root.findViewById(R.id.textView_Food);
        textView_Food.setText("Valitse ruoka");
        spinner_Foods = (Spinner) root.findViewById(R.id.spinner_Foods);
        ArrayList<String> foods = new ArrayList<>();
        foods.add("Nauta");
        foods.add("Kala");
        foods.add("Porsas");
        foods.add("Kana");
        foods.add("Juusto");
        foods.add("Riisi");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, foods);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Foods.setAdapter(arrayAdapter);

        userInputAPI = (EditText) root.findViewById(R.id.userInput);
        userInputProtein = (EditText) root.findViewById(R.id.userInputProtein);
        textview_CO2 = (TextView) root.findViewById(R.id.textView_CO2);
        infoAverageCO2 = (TextView) root.findViewById(R.id.textView_finnishAverage);
        infoForUserX = (TextView) root.findViewById(R.id.textView_infoForUserX);
        textView_protein = (TextView) root.findViewById(R.id.textView_protein);
        textView_proteinGoal = (TextView) root.findViewById(R.id.textView_proteinGoal);
        activityLevel = (CheckBox) root.findViewById(R.id.checkBox_activityLevel);

        //CO2 emissions button
        button_API = (Button) root.findViewById(R.id.button_API);
        button_API.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printEmissionsInfo(v);
            }
        });

        //protein button
        button_protein = (Button) root.findViewById(R.id.button_protein);
        button_protein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printProteinInfo(v);
            }
        });

        //Calculating the annual CO2-emission per year
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        AverageCO2 avg = new AverageCO2();
        averageCO2Value = avg.getAverageCO2();
        infoAverageCO2.setText("(Suomalaisen keskiarvo " + averageCO2Value + " kg / vuosi)");
        return root;
    }

    public void printEmissionsInfo(View v) {

        int intFromUser = Integer.parseInt(userInputAPI.getText().toString());
        String selected_Food = spinner_Foods.getSelectedItem().toString();

        Emission emission = new Emission();
        String co2_value = emission.readXML(intFromUser, selected_Food);

        if(co2_value == "") {
            textview_CO2.setText("Anna arvo 0-200 väliltä");
        } else {
            textview_CO2.setText(co2_value);
        }
    }

    public void printProteinInfo(View v) {

        int intFromUser = Integer.parseInt(userInputProtein.getText().toString());
        String selected_Food = spinner_Foods.getSelectedItem().toString();

        ConsumedProtein consumedProtein = new ConsumedProtein();
        String protein_value = consumedProtein.getConsumedProtein(intFromUser, selected_Food);

        //Checking if the user is signed in
        boolean isUserArrayEmpty = UserDatabase.isUserArrayEmpty();
        //If user array is empty, user has not signed in. Therefore the needed user info
        //to calculate daily protein goal is not available and will not be printed.
        if (isUserArrayEmpty == true) {
            textView_protein.setText(protein_value);
            textView_proteinGoal.setText("20 % päivän kaloreista");

            activityLevel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        double activityFactor = 0.1; //regular person
                        textView_proteinGoal.setText("10 % päivän kaloreista");
                    } else {
                        double activityFactor = 0.2; //bulker
                        textView_proteinGoal.setText("20 % päivän kaloreista");
                    }
                }
            });
        }
        //If user array is not empty, user has signed in. Therefore the needed user info
        //to calculate daily protein goal are available and protein goal will be printed.
        else if (isUserArrayEmpty == false) {
            textView_protein.setText(protein_value);

            //Default: bulker protein goal with activity factor 0,2.
            //For bulkers proteins account for 20% of daily calories, and for regular person 10%.
            double activityFactor = 0.2;
            String protein_goal = UserDatabase.userDailyProteinGoal(activityFactor);
            String userName = UserDatabase.getUserName();

            infoForUserX.setText("Juuri sinulle " + userName);
            textView_proteinGoal.setText(protein_goal + " g");

            activityLevel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        double activityFactor = 0.1; //regular person
                        String protein_goal = UserDatabase.userDailyProteinGoal(activityFactor);
                        textView_proteinGoal.setText(protein_goal + " g");
                    } else {
                        double activityFactor = 0.2; //bulker
                        String protein_goal = UserDatabase.userDailyProteinGoal(activityFactor);
                        textView_proteinGoal.setText(protein_goal + " g");
                    }
                }
            });
        }
    }
}
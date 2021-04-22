package com.example.myapplication.ui.addFood;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.ui.ProteinCalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class AddFoodFragment extends Fragment {

    //private AddFoodViewModel addFoodViewModel;
    private Button button_API;
    private Spinner spinner_Foods;
    private EditText userInput;
    private TextView textView_Food;
    private TextView textview_CO2;
    private TextView textView_protein;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*addFoodViewModel =
                new ViewModelProvider(this).get(AddFoodViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_addfood, container, false);
        //final TextView textView = root.findViewById(R.id.text_addFood);
        /*addFoodViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
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
        userInput = (EditText) root.findViewById(R.id.userInput);
        userInput.setText("0");
        textview_CO2 = (TextView) root.findViewById(R.id.textView_CO2);
        textView_protein = (TextView) root.findViewById(R.id.textView_protein);
        button_API = (Button) root.findViewById(R.id.button_API);
        button_API.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readXML(v);
            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        return root;
    }

    public void readXML(View v) {
        int arrayIndex = 0;
        String consumedProtein = "";
        int intFromUser = Integer.parseInt(userInput.getText().toString());
        String selected_Food = spinner_Foods.getSelectedItem().toString();
        System.out.println(intFromUser);
        if(0 <= intFromUser && intFromUser <= 200) {
            if(selected_Food == "Nauta") {
                selected_Food = "beef";
                //noora lisää näihin kohtii indeksin että saa haettua proteiinin
                arrayIndex = 0;
                consumedProtein = ProteinCalculator.countConsumedProtein(intFromUser, arrayIndex);
            } else if(selected_Food == "Kala") {
                selected_Food = "fish";
                arrayIndex = 1;
            } else if(selected_Food == "Porsas") {
                selected_Food = "porkPoultryLevel";
                arrayIndex = 2;
            }else if(selected_Food == "Kana") {
                selected_Food = "porkPoultryLevel";
                arrayIndex = 3;
            }else if(selected_Food == "Juusto") {
                selected_Food = "cheese";
                arrayIndex = 4;
            }else if(selected_Food == "Riisi") {
                selected_Food = "rice";
                arrayIndex = 5;
            }

            try {
                String url = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/FoodCalculator?query.diet=omnivore";
                StringBuilder builder = new StringBuilder(url);
                CharSequence chSeq = "&query."+ selected_Food +"Level=" + intFromUser;
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
                Float co2_value = Float.parseFloat(total);
                System.out.println(co2_value);
                textview_CO2.setText(co2_value.toString());
                textView_protein.setText(consumedProtein);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Anna luku 0 ja 200 väliltä!");
        }
    }
}
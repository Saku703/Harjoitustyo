package com.example.myapplication.ui.addFood;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class AddFoodFragment extends Fragment {

    //private AddFoodViewModel addFoodViewModel;
    private Button button_API;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*addFoodViewModel =
                new ViewModelProvider(this).get(AddFoodViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_addfood, container, false);
        final TextView textView = root.findViewById(R.id.text_addFood);
        /*addFoodViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        button_API = (Button) root.findViewById(R.id.button_API);
        button_API.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               readXML(v);
            }
        });
        return root;
    }

    public void readXML (View v) {
        System.out.println("TEST");
        /*try {
            String url = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/FoodCalculator?query.diet=omnivore";
            StringBuilder builder = new StringBuilder(url);
            CharSequence chSeq = "&query.beefLevel=200";
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

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
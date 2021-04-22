package com.example.myapplication.ui.log;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class LogFragment extends Fragment {

    TextView logtext;

    private LogViewModel logViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logViewModel =
                new ViewModelProvider(this).get(LogViewModel.class);
        View root = inflater.inflate(R.layout.fragment_log, container, false);
        final TextView textView = root.findViewById(R.id.text_log);
        logViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        logtext = (TextView) textView.findViewById(R.id.text_log);
        Button button_log = root.findViewById(R.id.button);
        button_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readJSON(v);
            }
        });

        return root;
    }

    public void readJSON(View v){
        String json = getJSON();
        logtext.setText("Lokitiedot:\n" + json);
    }



    public String getJSON(){
        String response = null;
        StringBuilder sb = new StringBuilder();

        ////////////////////////////////////////////////
        JSONObject esimerkki = new JSONObject();
        JSONArray list = new JSONArray();
        JSONObject ingredient = new JSONObject();
        JSONObject ingredient2 = new JSONObject();
        JSONObject ingredient3 = new JSONObject();
        try {
            ingredient.put("food_item", "porkkana");
            ingredient.put("food_amount", 300);
            list.put(ingredient);
            ingredient2.put("food_item", "jauheliha");
            ingredient2.put("food_amount", 200);
            list.put(ingredient2);
            ingredient3.put("food_item", "possu");
            ingredient3.put("food_amount", 155);
            list.put(ingredient3);
            esimerkki.put("log_data", list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ////////////////////////////////////////////////

        try {
            JSONArray data = esimerkki.getJSONArray("log_data");
            int x = 0;
            String log_line = null;
            String food_info = null;
            int food_number = 0;
            while ((log_line= String.valueOf((data.getJSONObject(x)))) != null) {
                JSONObject obj = new JSONObject(log_line);
                food_info = obj.getString("food_item");
                food_number = obj.getInt("food_amount");
                System.out.println(food_info + "--" + food_number + "\n");
                sb.append(x+1);
                sb.append(". syöte: ");
                sb.append(food_info);
                sb.append("--");
                sb.append(food_number);
                sb.append("\n");
                x += 1;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            }

        response=sb.toString();
        return response;
    }
}
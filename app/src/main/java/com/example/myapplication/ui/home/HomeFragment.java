package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button button_Waste;
    private TextView textview_Waste;
    private Spinner spinner_Waste;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        spinner_Waste = (Spinner) root.findViewById(R.id.spinner_Waste);
        ArrayList<String> waste_behavior = new ArrayList<>();
        waste_behavior.add("En koskaan");
        waste_behavior.add("Joskus");
        waste_behavior.add("Usein");
        waste_behavior.add("Aina");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, waste_behavior);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Waste.setAdapter(arrayAdapter);
        button_Waste = (Button) root.findViewById(R.id.button_Waste);
        button_Waste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AddFood food = new AddFood();
                //food.readXML();
                //readXML(v);
            }
        });
        String url = "https://goweather.herokuapp.com/weather/Helsinki";

        new AsyncHttpClient().get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String str = new String(responseBody);
                //System.out.println(str);
                if(str != null) {
                    try {
                        //JSONArray jsonArray = new JSONArray(str);
                        //for (int i=0; i < jsonArray.length(); i++) {
                        //JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONObject jsonObject = new JSONObject(str);
                        String all = jsonObject.toString();
                        System.out.println(all);
                            //System.out.println(i);
                        String[] tokensAll = all.split(",");
                        System.out.println(tokensAll[0]);
                        //String totalString = tokensAll[4];
                        String[] temperature1 = tokensAll[0].split(":");
                        System.out.println(temperature1[1]);
                        //String total = tokensTotal[1];
                        String weather = temperature1[1];
                        weather = weather.substring(1, weather.length() - 1);
                        System.out.println(weather);
                        //Float co2_value = Float.parseFloat(total);
                        //System.out.println(co2_value);
                        //System.out.println(all.split(","));
                        //System.out.println(jsonObject.getString("temperature"));
                        } catch (JSONException e) {
                        e.printStackTrace();
                    }
                /*} catch (JSONException e) {
                        e.printStackTrace();
                    }*/
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("Epäonnistui");
            }
        });
        return root;
    }

}
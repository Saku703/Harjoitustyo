package com.example.myapplication.ui.weaher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.ui.JSONWriter;
import com.example.myapplication.ui.PasswordTester;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class WeatherFragment extends Fragment {

    //private HomeViewModel homeViewModel;
    private Button button_weather;
    private EditText editText_weatherInput;
    private TextView textView_cityHeader;
    private TextView textView_weatherData;
    private int counter = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_weather, container, false);

        textView_cityHeader = (TextView) root.findViewById(R.id.textView_cityHeader);
        textView_cityHeader.setText("Syötä kaupunki");
        textView_weatherData = (TextView) root.findViewById(R.id.textView_weatherData);
        textView_weatherData.setText("");
        editText_weatherInput = (EditText) root.findViewById(R.id.editText_weatherInput);
        editText_weatherInput.setText("");
        button_weather = (Button) root.findViewById(R.id.button_weather);
        button_weather.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Utilising the passwordtester again with city names: reusing excisting code.
                PasswordTester passwordTester = new PasswordTester();
                counter++;
                JSONWriter.updateJSON(counter, editText_weatherInput.getText().toString());
                //Check if users input is 
                if (passwordTester.containsNumber(editText_weatherInput.getText().toString()) == true) {
                    textView_cityHeader.setText("Kaupunkia " + editText_weatherInput.getText().toString() + " ei löytynyt");
                } else if (passwordTester.containsSpecialCharacter(editText_weatherInput.getText().toString()) == true) {
                    textView_cityHeader.setText("Kaupunkia " + editText_weatherInput.getText().toString() + " ei löytynyt");
                } else {
                    String url = "https://goweather.herokuapp.com/weather/";
                    StringBuilder builder = new StringBuilder(url);
                    CharSequence chSeq = editText_weatherInput.getText().toString();
                    builder.append(chSeq);
                    //System.out.println(builder);
                    new AsyncHttpClient().get(builder.toString(), new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            String str = new String(responseBody);
                            String full_weather_report = Weather.getWeather(str);
                            if (full_weather_report == "") {
                                textView_weatherData.setText("Säätietoja ei ole saatavilla paikkaan " +
                                        editText_weatherInput.getText().toString() + ". Tarkista syötteesi.");
                            } else {
                                textView_weatherData.setText(full_weather_report);
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            textView_weatherData.setText("Säätietoja ei ole juuri nyt saatavilla paikkaan " +
                                    editText_weatherInput.getText().toString() + ". Yritä myöhemmin uudestaan");
                        }
                    });
                }
            }
        });
        return root;
    }
}
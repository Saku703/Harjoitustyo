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
import com.example.myapplication.ui.PasswordTester;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class WeatherFragment extends Fragment {

    //private HomeViewModel homeViewModel;
    private Button button_weather;
    private EditText editText_weatherInput;
    private TextView textView_cityHeader;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_weather, container, false);
        textView_cityHeader = (TextView) root.findViewById(R.id.textView_cityHeader);
        textView_cityHeader.setText("Syötä kaupunki");
        editText_weatherInput = (EditText) root.findViewById(R.id.editText_weatherInput);
        editText_weatherInput.setText("");
        button_weather = (Button) root.findViewById(R.id.button_weather);
        button_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PasswordTester.containsNumber(editText_weatherInput.getText().toString()) == true) {
                    textView_cityHeader.setText("Kaupunkia " + editText_weatherInput.getText().toString() + " ei löytynyt");
                } else if (PasswordTester.containsSpecialCharacter(editText_weatherInput.getText().toString()) == true) {
                    textView_cityHeader.setText("Kaupunkia " + editText_weatherInput.getText().toString() + " ei löytynyt");
                } else {
                    String url = "https://goweather.herokuapp.com/weather/";
                    StringBuilder builder = new StringBuilder(url);
                    CharSequence chSeq = editText_weatherInput.getText().toString();
                    builder.append(chSeq);
                    new AsyncHttpClient().get(builder.toString(), new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            String str = new String(responseBody);
                            Weather.getWeather(str);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            textView_cityHeader.setText("Tietojen haku epäonnistui");
                        }
                    });
                }
            }
        });

        return root;
    }
}
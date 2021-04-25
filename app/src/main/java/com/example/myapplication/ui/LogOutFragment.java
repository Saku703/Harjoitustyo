package com.example.myapplication.ui;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.ui.weaher.WeatherViewModel;

import com.example.myapplication.R;

public class LogOutFragment extends Fragment {

    private static TextView logOutInfo;
    private Button logOut;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_logout, container, false);

        logOutInfo = (TextView) root.findViewById(R.id.textView_logOutInfo);
        logOut = (Button) root.findViewById(R.id.button_logOut);

        if (UserDatabase.isUserArrayEmpty() == true) {
            logOutInfo.setText("Et ole kirjautunut sisään");
        } else {
            String username = UserDatabase.getUserame();
            logOutInfo.setText("Olet kirjautunut sisään käyttäjällä " + username);
        }

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String infoToUser;
                infoToUser = UserDatabase.logOut();
                logOutInfo.setText(infoToUser);
            }
        });

        return root;
    }
}

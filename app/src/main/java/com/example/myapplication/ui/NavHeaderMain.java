package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;

public class NavHeaderMain {

    //navigation bar header
    public static TextView usernameInHeader;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*openingViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class); */
        View root = inflater.inflate(R.layout.nav_header_main, container, false);
        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_header_main);
        //View headerView = navigationView.getHeaderView(0);
        //final TextView textView = root.findViewById(R.id.text_opening);


        usernameInHeader = (TextView) root.findViewById(R.id.textView_navigationHeader);



        return root;
    }

    public static void changeUsername() {
        usernameInHeader.setText("moi");
    }
}


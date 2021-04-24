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
import androidx.appcompat.app.ActionBarDrawerToggle;

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;

public class NavHeaderMain {

    //navigation bar header
    public TextView usernameInHeader;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*openingViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class); */
        View root = inflater.inflate(R.layout.nav_header_main, container, false);
        /* NavigationView root = (NavigationView) inflater.inflate(R.layout.nav_header_main, container, false);
        View headerView = root.getHeaderView(0);
        usernameInHeader = (TextView) headerView.findViewById(R.id.textView_navigationHeader);
        usernameInHeader.setText("noora"); */

        //usernameInHeader = (TextView) root.findViewById(R.id.textView_navigationHeader);
        //usernameInHeader.setText("noora");
        //NavigationView navigationView = (NavigationView) root.findViewById(R.id.nav_header_main);
        //View headerView = root.getHeaderView(0);
        //final TextView textView = root.findViewById(R.id.text_opening);

        //mDrawerTogle = new ActionBarDrawerToggle()


        //usernameInHeader = (TextView) root.findViewById(R.id.textView_navigationHeader);



        return root;
    }

    public void changeUsername() {
        usernameInHeader.setText("noora");
    }
}


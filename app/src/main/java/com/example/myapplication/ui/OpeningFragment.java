package com.example.myapplication.ui;

import android.content.Intent;
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
import com.example.myapplication.ui.home.HomeFragment;
import com.example.myapplication.ui.home.HomeViewModel;

public class OpeningFragment extends Fragment {

    private HomeViewModel openingViewModel;

    //log in
    private TextView welcome;
    private EditText email;
    private EditText password;
    private Button logIn;
    private TextView loginInfo;

    //create account
    private EditText newEmail;
    private EditText newPassword;
    private Button createAccount;
    private TextView createAccountInfo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*openingViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class); */
        View root = inflater.inflate(R.layout.fragment_opening, container, false);
        //final TextView textView = root.findViewById(R.id.text_opening);

        welcome = (TextView) root.findViewById(R.id.text_opening);
        email = (EditText) root.findViewById(R.id.emailLogin_opening);
        password = (EditText) root.findViewById(R.id.passwordLogin_opening);
        logIn = (Button) root.findViewById(R.id.button1_opening);
        loginInfo = (TextView) root.findViewById(R.id.infoLogin_opening);

        newEmail = (EditText) root.findViewById(R.id.emailCreateAccount_opening);
        newPassword = (EditText) root.findViewById(R.id.passwordCreateAccount_opening);
        createAccount = (Button) root.findViewById(R.id.button2_opening);
        createAccountInfo = (TextView) root.findViewById(R.id.infoCreateAccount_opening);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  validateLogin(email.getText().toString(), newEmail.getText().toString(), password.getText().toString(), newPassword.getText().toString());
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("hei");
                createAccount(newEmail.getText().toString(), newPassword.getText().toString());
            }
        });

        /*
        openingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        }); */
        return root;
    }

    private void validateLogin (String email, String newEmail, String password, String newPassword) {
        System.out.println(email + " " + password);
        System.out.println(newEmail + " " + newPassword);

        /*
        if (newEmail == "") {
            loginInfo.setText("Tiliä ei vielä ole. Luo tili.");
        } */

        if ((email.equals(newEmail) == true) && (password.equals(newPassword) == true)) {
            //loginInfo.setText("Olet kirjautunut sisään.");
            welcome.setText("Olet kirjautunut sisään!");
        } else {
            //loginInfo.setText("Sähköposti tai salasana on väärä.");
            welcome.setText("Sähköposti tai salasana on väärä.");

        }
    }

    private void createAccount (String newEmail, String newPassword) {
        //User testi = User.getInstance();
        System.out.println("opening");

        int passLenght = newPassword.length();
        if (passLenght < 12) {
            //createAccountInfo.setText("Salasana on liian lyhyt" + passLenght);
            welcome.setText("Salasana on liian lyhyt, merkkjä " + passLenght + "/12");
            newEmail = "";
            newPassword = "";
        } else {
            //createAccountInfo.setText("Tili luotu.");
            welcome.setText("Tili luotu. Kirjaudu sisään");
        }

        //UserDatabase.fragmentCalls(email, password);
    }

}

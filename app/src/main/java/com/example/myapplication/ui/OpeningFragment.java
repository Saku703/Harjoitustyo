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

    private EditText email;
    private EditText password;
    private Button logIn;
    private TextView loginInfo;

    private Button createAccount;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*openingViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class); */
        View root = inflater.inflate(R.layout.fragment_opening, container, false);
        final TextView textView = root.findViewById(R.id.text_opening);

        email = (EditText) root.findViewById(R.id.emailLogin_opening);
        password = (EditText) root.findViewById(R.id.passwordLogin_opening);
        logIn = (Button) root.findViewById(R.id.button1_opening);
        loginInfo = (TextView) root.findViewById(R.id.loginInfo_opening);

        createAccount = (Button) root.findViewById(R.id.button2_opening);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  //System.out.println("joo");
                  validateLogin(email.getText().toString(), password.getText().toString());
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("hei");
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

    private void validateLogin (String email, String password) {
        int length1 = email.length();
        int length2 = password.length();
        System.out.println(email + length1 + " " + password + length2);
        //System.out.println(password + length2);
        if ((email == "moi") && (password == "1234")) {
            Intent intent = new Intent (getActivity(), HomeFragment.class);
            startActivity(intent);
        } else {
            //System.out.println("ei toimi");
            loginInfo.setText("Sähköposti tai salasana on väärä.");
        }
    }

}

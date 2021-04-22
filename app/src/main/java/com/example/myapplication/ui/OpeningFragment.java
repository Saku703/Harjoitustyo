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
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.ui.home.HomeViewModel;

public class OpeningFragment extends Fragment {

    private HomeViewModel openingViewModel;

    //log in
    private TextView welcome;
    private EditText user;
    private EditText password;
    private Button logIn;
    private TextView loginInfo;

    //create account
    private EditText newUser;
    private EditText newPassword;
    private Button createAccount;
    private TextView createAccountInfo;
    private EditText name;
    private EditText age;
    private EditText height;
    private EditText weight;
    private Spinner chooseSex;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*openingViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class); */
        View root = inflater.inflate(R.layout.fragment_opening, container, false);
        //final TextView textView = root.findViewById(R.id.text_opening);

        welcome = (TextView) root.findViewById(R.id.text_opening);
        user = (EditText) root.findViewById(R.id.userLogin_opening);
        password = (EditText) root.findViewById(R.id.passwordLogin_opening);
        logIn = (Button) root.findViewById(R.id.button1_opening);
        loginInfo = (TextView) root.findViewById(R.id.infoLogin_opening);

        newUser = (EditText) root.findViewById(R.id.userCreateAccount_opening);
        newPassword = (EditText) root.findViewById(R.id.passwordCreateAccount_opening);
        createAccount = (Button) root.findViewById(R.id.button2_opening);
        createAccountInfo = (TextView) root.findViewById(R.id.infoCreateAccount_opening);
        name = (EditText) root.findViewById(R.id.name_opening);
        age = (EditText) root.findViewById(R.id.age_opening);
        height = (EditText) root.findViewById(R.id.height_opening);
        weight = (EditText) root.findViewById(R.id.weight_opening);
        chooseSex = (Spinner) root.findViewById(R.id.spinner_opening);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.chooseSex, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseSex.setAdapter(adapter);
        //chooseSex.setOnItemSelectedListener();



        //log in button -> user database call -> search wether user & password are in the database
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String printToUser = "";
                printToUser = UserDatabase.logIn(user.getText().toString(), password.getText().toString());
                welcome.setText(printToUser);
            }
        });

        //create account button -> user database call -> create new user
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String printToUser = "";

                int ageInt = Integer.parseInt(age.getText().toString());
                int heightInt = Integer.parseInt(height.getText().toString());
                int weightInt = Integer.parseInt(weight.getText().toString());
                String selectedSex = chooseSex.getSelectedItem().toString();

                printToUser = UserDatabase.createUser(newUser.getText().toString(),
                        newPassword.getText().toString(), name.getText().toString(),
                        ageInt, heightInt, weightInt, selectedSex);
                createAccountInfo.setText(printToUser);

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
}

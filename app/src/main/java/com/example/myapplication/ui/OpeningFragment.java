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
import com.example.myapplication.R;

public class OpeningFragment extends Fragment {

    //log in
    private TextView welcome;
    private EditText user;
    private EditText password;
    private Button logIn;
    private CheckBox showPassword_logIn;

    //create account
    private TextView createAccountInfo;
    private EditText newUser;
    private EditText newPassword;
    private EditText name;
    private EditText age;
    private EditText height;
    private EditText weight;
    private Spinner chooseSex;
    private Button createAccount;
    private CheckBox showPassword_createAccount;

    //for both functions
    private String areFieldsFilled = "";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_opening, container, false);

        welcome = (TextView) root.findViewById(R.id.text_opening);
        user = (EditText) root.findViewById(R.id.userLogin_opening);
        password = (EditText) root.findViewById(R.id.passwordLogin_opening);
        logIn = (Button) root.findViewById(R.id.button1_opening);
        showPassword_logIn = (CheckBox) root.findViewById(R.id.showPass_logIn);

        newUser = (EditText) root.findViewById(R.id.userCreateAccount_opening);
        newPassword = (EditText) root.findViewById(R.id.passwordCreateAccount_opening);
        createAccount = (Button) root.findViewById(R.id.button2_opening);
        createAccountInfo = (TextView) root.findViewById(R.id.infoCreateAccount_opening);
        name = (EditText) root.findViewById(R.id.name_opening);
        age = (EditText) root.findViewById(R.id.age_opening);
        height = (EditText) root.findViewById(R.id.height_opening);
        weight = (EditText) root.findViewById(R.id.weight_opening);
        chooseSex = (Spinner) root.findViewById(R.id.spinner_opening);
        showPassword_createAccount = (CheckBox) root.findViewById(R.id.showPass_createAccount);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.chooseSex, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseSex.setAdapter(adapter);
        UserInputTester userInputTester = new UserInputTester();

        //create account button -> user database call -> create new user
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String printToUser = "";

                areFieldsFilled = userInputTester.createAccountInputTester(newUser.getText().toString(),
                        newPassword.getText().toString(), name.getText().toString(),
                        age.getText().toString(), height.getText().toString(),
                        weight.getText().toString(), chooseSex.getSelectedItem().toString());

                if (areFieldsFilled.equals("OK") == true) {
                    int ageInt = Integer.parseInt(age.getText().toString());
                    int heightInt = Integer.parseInt(height.getText().toString());
                    int weightInt = Integer.parseInt(weight.getText().toString());
                    String selectedSex = chooseSex.getSelectedItem().toString();

                    printToUser = UserDatabase.createUser(newUser.getText().toString(),
                            newPassword.getText().toString(), name.getText().toString(),
                            ageInt, heightInt, weightInt, selectedSex);
                    createAccountInfo.setText(printToUser);
                } else {
                    //printing the warning to user
                    createAccountInfo.setText(areFieldsFilled);
                }
            }
        });

        //log in button -> user database call -> search wether user & password are in the database
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String printToUser = "";

                areFieldsFilled = userInputTester.logInInputTester(user.getText().toString(), password.getText().toString());

                if (areFieldsFilled.equals("OK") == true) {
                    printToUser = UserDatabase.logIn(user.getText().toString(), password.getText().toString());
                    welcome.setText(printToUser);
                } else {
                    //printing the warning to user
                    welcome.setText(areFieldsFilled);
                }
            }
        });

        showPassword_logIn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        showPassword_createAccount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    newPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    newPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        return root;
    }
}

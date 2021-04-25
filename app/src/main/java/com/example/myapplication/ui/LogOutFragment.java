package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.myapplication.R;

public class LogOutFragment extends Fragment {

    private TextView logOutInfo;
    private Button logOut;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_logout, container, false);

        logOutInfo = (TextView) root.findViewById(R.id.textView_logOutInfo);
        logOut = (Button) root.findViewById(R.id.button_logOut);

        //Printing info to user based on wether the user is signed in
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

package com.example.myapplication.ui.log;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.myapplication.R;
import com.example.myapplication.ui.JSONReader;

public class LogFragment extends Fragment {

    private static TextView logtext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_log, container, false);
        final TextView textView = root.findViewById(R.id.text_log);

        logtext = (TextView) textView.findViewById(R.id.text_log);
        Button button_log = root.findViewById(R.id.button);
        button_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String json = JSONReader.readJSON(v);
                logtext.setText("Lokitiedot:\n" + json);
            }
        });

        return root;
    }
}
package com.example.myapplication.ui.addFood;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;

public class AddFoodFragment extends Fragment {

    private AddFoodViewModel addFoodViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addFoodViewModel =
                new ViewModelProvider(this).get(AddFoodViewModel.class);
        View root = inflater.inflate(R.layout.fragment_addfood, container, false);
        final TextView textView = root.findViewById(R.id.text_addFood);
        addFoodViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
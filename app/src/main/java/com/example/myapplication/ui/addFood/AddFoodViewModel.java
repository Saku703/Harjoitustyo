package com.example.myapplication.ui.addFood;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddFoodViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddFoodViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Täällä voit lisätä ruuan ja laskea päästöt");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
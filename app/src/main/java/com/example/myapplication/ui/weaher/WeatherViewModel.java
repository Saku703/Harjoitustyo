package com.example.myapplication.ui.weaher;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WeatherViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public WeatherViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Tämä on kotinäkymä");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
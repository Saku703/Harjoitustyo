package com.example.myapplication.ui.log;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LogViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LogViewModel() {
        mText = new MutableLiveData<>();
        /*String json = getJSON();
        mText.setValue("Lokitiedot: " + json);*/
    }

    /*public void readJSON(View v) {
        System.out.println("Täällä");
        String json = getJSON();
        System.out.println("Lokitiedot:" + json);  //ylimääräinen
        mText.setValue("Lokitiedot:" + json);
    }

    public String getJSON(){
        String response = null;
        try {
            InputStream in = new BufferedInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line=br.readLine()) != null){
                sb.append(line).append("\n");
            }
            in.close();
            response = sb.toString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }*/

    public LiveData<String> getText() {
        return mText;
    }
}
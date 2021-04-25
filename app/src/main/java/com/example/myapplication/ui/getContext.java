package com.example.myapplication.ui;

import android.content.Context;

public class getContext {

    public static Context real_context = null;

    //Get context for the JSONFile log
    public static Context getContextForFile(Context context){
        if (context == null){
            context = real_context;
        }
        else {
            real_context = context;
        }
        return real_context;
    }
}

package com.demo.callrecordcatch;

import android.app.Application;
import android.content.Context;

/**
 * Created by Waylon on 16/7/7.
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}

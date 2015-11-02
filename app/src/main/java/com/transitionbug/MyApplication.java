package com.transitionbug;

import android.app.Application;

/**
 * Created by hat on 11/2/15.
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;
    public static MyApplication getInstance() {
        return mInstance;
    }

    public MyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}

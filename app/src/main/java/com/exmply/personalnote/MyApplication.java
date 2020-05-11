package com.exmply.personalnote;

import android.app.Application;

public class MyApplication extends Application {

    public String answer="";
    public boolean isUnlock=false;
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }

    public static MyApplication getInstance(){
        return mInstance;
    }

}



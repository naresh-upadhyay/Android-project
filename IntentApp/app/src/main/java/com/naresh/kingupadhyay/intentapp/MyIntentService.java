package com.naresh.kingupadhyay.intentapp;

// Background services not  usable for user .
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {

    private static final String TAG="com.naresh.kingupadhyay.intentapp";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent( Intent intent) {
        Log.i(TAG,"The service has started ");
    }
}

package com.naresh.kingupadhyay.intentapp;

//Service running in background by using this class ->>user is not aware from this
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG="com.naresh.kingupadhyay.intentapp";
    public MyService() {
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        Log.i(TAG, "onStartCommand called");
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Long futuretime = System.currentTimeMillis() + 5000;
                    while (System.currentTimeMillis() < futuretime) {
                        synchronized (this) {
                            try {
                                wait(futuretime - System.currentTimeMillis());
                                Log.i(TAG, "Service is running");
                            } catch (Exception e) {
                            }

                        }
                    }
                }
            }
        };
        Thread mythread = new Thread(runnable);
        mythread.start();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {

        Log.i(TAG,"onDestroy called");
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }
}

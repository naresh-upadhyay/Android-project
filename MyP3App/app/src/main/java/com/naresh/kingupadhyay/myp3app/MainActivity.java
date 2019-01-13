package com.naresh.kingupadhyay.myp3app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    private TextView myMessage;
    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myMessage=(TextView) findViewById(R.id.MyMessage);
        this.gestureDetector=new GestureDetector(this,this);
        gestureDetector.setOnDoubleTapListener(this);
    }
    //After this press ALT+INSERT keys -> (override methods)-> (simply type ontouchevent) all other things of activty comes automatically


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    //After this press ALT+INSERT keys ->(implement methods) ->(select all) all other things of activty comes automatically

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        myMessage.setText("onSingleTapConfirmed");
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        myMessage.setText("onDoubleTap");

        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        myMessage.setText("onDoubleTapEvent");

        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        myMessage.setText("onDown");

        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        myMessage.setText("onShowPress");

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        myMessage.setText("onSingleTapUp");

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        myMessage.setText("onScroll");

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        myMessage.setText("onLongPress");

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        myMessage.setText("onFling");

        return false;
    }
}

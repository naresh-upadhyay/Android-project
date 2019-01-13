package com.naresh.kingupadhyay.animationapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    ViewGroup Mylayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mylayout=(ViewGroup) findViewById(R.id.mylayout);
        Mylayout.setOnTouchListener(
                new RelativeLayout.OnTouchListener() {
                       public boolean onTouch(View v, MotionEvent event) {
                        movebutton();
                        return true;
                    }
                }

        );

    }
    public void movebutton(){
        TransitionManager.beginDelayedTransition(Mylayout);
        View mybutton=findViewById(R.id.mybutton);
        RelativeLayout.LayoutParams positionrules =new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT

        );
        positionrules.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        mybutton.setLayoutParams(positionrules);
        //code for expanding the button is given below
        ViewGroup.LayoutParams sizerule=mybutton.getLayoutParams();
        sizerule.width=450;
        sizerule.height=300;
        mybutton.setLayoutParams(sizerule);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

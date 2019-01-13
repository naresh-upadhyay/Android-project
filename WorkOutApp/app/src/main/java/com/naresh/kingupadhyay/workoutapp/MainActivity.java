package com.naresh.kingupadhyay.workoutapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private WorkoutExpert expert= new WorkoutExpert();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Animation animation = new RotateAnimation(0.0f, 360.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setRepeatCount(-1);
        animation.setDuration(2000);


        Button button=(Button)findViewById(R.id.workoutbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView workoutT=(TextView) findViewById(R.id.Workouttext);
                Spinner workoutType=(Spinner) findViewById(R.id.workoutspin);
                String work=String.valueOf(workoutType.getSelectedItem());
                //workoutT.setText(work);
                List<String> workout=new ArrayList<String>();
                if(work.equals("Chest")){
                    workout.add("Push Up");
                    workout.add("Bench press");
                    ((Button)findViewById(R.id.workoutbutton)).clearAnimation();
                }
                else if(work.equals("Arms")){
                    workout.add("Dombles");
                    workout.add("Weight lifting");
                }
                else if(work.equals("Biseps")){
                    workout.add("Biseps Ext");
                    workout.add("Biseps Curle");
                }
                else if (work.equals("Triseps")){
                    workout.add("Triseps Ext");
                    workout.add("Triseps pushdown");
                }
                else if (work.equals("Legs")){
                    workout.add("Jogging ");
                    workout.add("Jumping");
                    workout.add("Weight Lifting");
                    ((Button)findViewById(R.id.workoutbutton)).clearAnimation();
                }

                StringBuilder workoutformats =new StringBuilder();

                for(String Work :workout){
                    workoutformats.append(Work).append('\n');
                }
                workoutT.setText(workoutformats);


            }
        });
        ((Button)findViewById(R.id.workoutbutton)).setAnimation(animation);


    }


}

package com.naresh.kingupadhyay.intentapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        Bundle firstData=getIntent().getExtras();
        if(firstData==null){
            return;
        }
        String firstmessage=firstData.getString("First message");
        final TextView secondtext=(TextView) findViewById(R.id.SecondText);
        secondtext.setText(firstmessage);
    }

    public void onclick2(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

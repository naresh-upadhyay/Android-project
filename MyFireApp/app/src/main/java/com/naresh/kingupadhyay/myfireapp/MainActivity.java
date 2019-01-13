package com.naresh.kingupadhyay.myfireapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

     FirebaseDatabase database;
     DatabaseReference myRef;
     // Read from the database


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("MyMessage");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);
                TextView textView=(TextView) findViewById(R.id.myTextView);
                textView.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }

    public void ButtoClicked(View view){

        EditText editText=(EditText) findViewById(R.id.myEditText);
        String message=editText.getText().toString();
        myRef.setValue(message);

    }
}

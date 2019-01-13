package com.naresh.kingupadhyay.myp2app;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.graphics.Color;
import android.widget.EditText;
import android.content.res.Resources;
import android.util.TypedValue;

import static android.graphics.Color.rgb;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout mylayout =new RelativeLayout(this);                                   //new variable 1
        Button mybutton = new Button(this);                                                  //new variable 2

        mylayout.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        mybutton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        mybutton.setText("Click Here");

        RelativeLayout.LayoutParams buttondetail= new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);  //Alignment of button

        buttondetail.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttondetail.addRule(RelativeLayout.CENTER_VERTICAL);
        mylayout.addView(mybutton,buttondetail);

        EditText username=new EditText(this);

        mybutton.setId(R.id.button);
        username.setId(R.id.text);

        RelativeLayout.LayoutParams usernamedetail= new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);  //Alignment of text

              usernamedetail.addRule(RelativeLayout.ABOVE,mybutton.getId());
              usernamedetail.addRule((RelativeLayout.CENTER_HORIZONTAL));

              Resources r=getResources();
              int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,r.getDisplayMetrics());
              username.setWidth(px);
              usernamedetail.setMargins(0,0,0,50);
              mylayout.addView(username,usernamedetail);
        setContentView(mylayout);

    }


}

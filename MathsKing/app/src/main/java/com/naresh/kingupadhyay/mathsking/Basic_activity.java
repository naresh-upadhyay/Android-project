package com.naresh.kingupadhyay.mathsking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import uk.co.senab.photoview.PhotoViewAttacher;


public class Basic_activity extends AppCompatActivity {

    ImageView imageView ;

    PhotoViewAttacher photoViewAttacher ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_activity);

        Bundle bundle = getIntent().getExtras();
        String imageUrl = bundle.getString("message");
        final ProgressBar progressBar=findViewById(R.id.progressBar);
        imageView = (ImageView)findViewById(R.id.imageView);
        photoViewAttacher = new PhotoViewAttacher(imageView);
        Picasso.get().load(imageUrl)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        if( photoViewAttacher!=null){
                            photoViewAttacher.update();
                        }else{
                            photoViewAttacher = new PhotoViewAttacher(imageView);
                        }
                        photoViewAttacher.update();
                        progressBar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(Basic_activity.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });


    }
}


package com.naresh.kingupadhyay.mathsking;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class CourseDetails extends AppCompatActivity{

    private Toolbar toolbar;
    private RecyclerView mBlogList;
    private RecyclerView fav_BlogList;
    private static DatabaseReference mDatabase;
    private static DatabaseReference favourites;
    public ProgressDialog progDailog;
    public String[] arraySpinner;
    public static Drawable draw;
    private static DatabaseReference favourite;
    protected String NAME="name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("message");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("chapters").child("algebra").child(message);
        mDatabase.keepSynced(true);

        mBlogList=(RecyclerView)findViewById(R.id.myrecyclerview);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));


        fav_BlogList=(RecyclerView)findViewById(R.id.reCyclerview);
        fav_BlogList.setHasFixedSize(true);
        fav_BlogList.setLayoutManager(new LinearLayoutManager(this));

        // Adding Toolbar to Main screen
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(message.equals("determinants")){
            arraySpinner = new String[] {
                    "Determinants", "Matrices", "con determinants", "4", "5"
            };

        }else if (message.equals("matrices")){
            arraySpinner = new String[] {
                    "Determinants", "Matrices", "condition matrices", "4", "5"
            };

        }else{
            arraySpinner = new String[] {
                    "Determinants", "Matrices", "No condition", "4", "5"
            };

        }

        final Spinner spinner=(Spinner) findViewById(R.id.spinner_determinants);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final Animation rotation = AnimationUtils.loadAnimation(this, R.anim.clockwise_refresh);
        // rotation.setRepeatCount(Animation.INFINITE);
        rotation.setRepeatCount(-1);
        rotation.setDuration(1000);

        progDailog = new ProgressDialog(CourseDetails.this);
        progDailog.setTitle("Data Loading....");
        progDailog.setMessage("Please Wait.....");
        progDailog.setCancelable(false);
        progDailog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        progDailog.show();



        final ImageButton imageButton=(ImageButton)findViewById(R.id.refresh_determinants);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String work=String.valueOf(spinner.getSelectedItem());

                final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser.getUid());
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("favourites")) {

                            favourite=FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser.getUid()).child("favourites");
                            favourite.keepSynced(true);
                            FirebaseRecyclerAdapter<dFavourite,MainActivity.ChapterViewHolder> fireBaseRecyclerAdapter =new FirebaseRecyclerAdapter <dFavourite, MainActivity.ChapterViewHolder>
                                    (dFavourite.class,R.layout.content_determinants,MainActivity.ChapterViewHolder.class,favourite) {
                                @Override
                                protected void populateViewHolder(MainActivity.ChapterViewHolder viewHolder, dFavourite model, int position) {
                                    viewHolder.setTitle(model.getTitle());

                                }
                            };
                            fav_BlogList.setAdapter(fireBaseRecyclerAdapter);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


                if(work.equals("Determinants")){
                    imageButton.startAnimation(rotation);
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("chapters").child("algebra").child("determinants");
                    mDatabase.keepSynced(true);
                    FirebaseRecyclerAdapter<dChapter,mChapterViewHolder> firebaseRecyclerAdapter =new FirebaseRecyclerAdapter <dChapter, mChapterViewHolder>
                            (dChapter.class,R.layout.content_determinants,mChapterViewHolder.class,mDatabase) {

                        @Override
                        protected void populateViewHolder(mChapterViewHolder viewHolder, dChapter model, int position) {
                            viewHolder.setTitle(model.getTitle());
                            viewHolder.setAnswer(model.getAnswer());
                            viewHolder.setQuestion(model.getQuestion());
                            viewHolder.setImage_concept(model.getImage_concept());
                            viewHolder.setAnswer_image(model.getAnswer_image());
                            viewHolder.setQuestion_image(model.getQuestion_image());
                            ((ImageButton)findViewById(R.id.refresh_determinants)).clearAnimation();

                        }

                    };
                    mBlogList.setAdapter(firebaseRecyclerAdapter);
                }
                else if(work.equals("Matrices")){
                    imageButton.startAnimation(rotation);
                    progDailog = new ProgressDialog(CourseDetails.this);
                    progDailog.setTitle("Data Loading...");
                    progDailog.setMessage("Please Wait.....");
                    progDailog.setCancelable(false);
                    progDailog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    progDailog.show();
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("chapters").child("algebra").child("matrices");
                    mDatabase.keepSynced(true);

                    FirebaseRecyclerAdapter<dChapter,mChapterViewHolder> firebaseRecyclerAdapter =new FirebaseRecyclerAdapter <dChapter, mChapterViewHolder>
                            (dChapter.class,R.layout.content_determinants,mChapterViewHolder.class,mDatabase) {
                        @Override
                        protected void populateViewHolder(mChapterViewHolder viewHolder, dChapter model, int position) {
                            viewHolder.setTitle(model.getTitle());
                            viewHolder.setAnswer(model.getAnswer());
                            viewHolder.setQuestion(model.getQuestion());
                            viewHolder.setImage_concept(model.getImage_concept());
                            viewHolder.setAnswer_image(model.getAnswer_image());
                            viewHolder.setQuestion_image(model.getQuestion_image());
                            progDailog.dismiss();
                            ((ImageButton)findViewById(R.id.refresh_determinants)).clearAnimation();
                        }
                    };
                    mBlogList.setAdapter(firebaseRecyclerAdapter);

                }
                else if(work.equals("Logarithms")){
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("chapters").child("algebra").child("logarithms");
                    mDatabase.keepSynced(true);
                    imageButton.startAnimation(rotation);
                }
                else if (work.equals("Pnc")){
                    ((ImageButton)findViewById(R.id.refresh_determinants)).clearAnimation();
                }
                else if (work.equals("Binomial")){
                    ((ImageButton)findViewById(R.id.refresh_determinants)).clearAnimation();
                }
                else if (work.equals("Blank")){

                }

            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();


        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser.getUid());
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("favourites")) {

                    favourite=FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser.getUid()).child("favourites");
                    favourite.keepSynced(true);
                    FirebaseRecyclerAdapter<dFavourite,MainActivity.ChapterViewHolder> fireBaseRecyclerAdapter =new FirebaseRecyclerAdapter <dFavourite, MainActivity.ChapterViewHolder>
                            (dFavourite.class,R.layout.content_determinants,MainActivity.ChapterViewHolder.class,favourite) {
                        @Override
                        protected void populateViewHolder(MainActivity.ChapterViewHolder viewHolder, dFavourite model, int position) {
                            viewHolder.setTitle(model.getTitle());

                        }
                    };
                    fav_BlogList.setAdapter(fireBaseRecyclerAdapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



        FirebaseRecyclerAdapter<dChapter,mChapterViewHolder> firebaseRecyclerAdapter =new FirebaseRecyclerAdapter <dChapter, mChapterViewHolder>
                (dChapter.class,R.layout.content_determinants,mChapterViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(mChapterViewHolder viewHolder, dChapter model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setAnswer(model.getAnswer());
                viewHolder.setQuestion(model.getQuestion());
                viewHolder.setImage_concept(model.getImage_concept());
                viewHolder.setAnswer_image(model.getAnswer_image());
                viewHolder.setQuestion_image(model.getQuestion_image());
                progDailog.dismiss();

            }
        };
        mBlogList.setAdapter(firebaseRecyclerAdapter);
        // ((ImageButton)findViewById(R.id.refresh_determinants)).clearAnimation();
    }

    public static class mChapterViewHolder extends RecyclerView.ViewHolder{
        View mView;
        private RelativeLayout rl_ques;
        private RelativeLayout rl_ans;
        public Button expand_ques;
        public Button expand_ans;
        boolean check;
        private String tileText;
        private String questionText;
        private String answerText;
        private String conceptUrl;
        private String questionUrl;
        private String answerUrl;
        private String NAME="name";
        private String KEY;
        final Resources res = itemView.getResources();
        final ImageButton favoriteImageButton = (ImageButton) itemView.findViewById(R.id.add_favorite);

        public mChapterViewHolder(View itemView){
            super(itemView);
            mView=itemView;

            rl_ques=itemView.findViewById(R.id.question_layout);
            rl_ques.setVisibility(View.GONE);
            expand_ques = (Button)itemView.findViewById(R.id.expand_ques);
            expand_ques.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_expand, 0);
            expand_ques.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(rl_ques.getVisibility()==View.GONE){
                        rl_ques.setVisibility(View.VISIBLE);
                        expand_ques.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_collapse, 0);
                        expand_ques.setCompoundDrawablePadding(10);
                        //algebra.setTextColor(Color.BLACK);
                    }else {
                        rl_ques.setVisibility(View.GONE);
                        expand_ques.setCompoundDrawablesWithIntrinsicBounds(0, 0,R.drawable.ic_action_expand, 0);
                    }

                }
            });

            rl_ans=itemView.findViewById(R.id.answer_layout);
            rl_ans.setVisibility(View.GONE);
            expand_ans = (Button)itemView.findViewById(R.id.expand_ans);
            expand_ans.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_expand, 0);
            expand_ans.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(rl_ans.getVisibility()==View.GONE){
                        rl_ans.setVisibility(View.VISIBLE);
                        expand_ans.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_action_collapse, 0);
                        expand_ans.setCompoundDrawablePadding(10);
                        //algebra.setTextColor(Color.BLACK);
                    }else {
                        rl_ans.setVisibility(View.GONE);
                        expand_ans.setCompoundDrawablesWithIntrinsicBounds(0, 0,R.drawable.ic_action_expand, 0);
                    }

                }
            });

            final ImageView concept_Imag=(ImageView)itemView.findViewById(R.id.concept_image);
            concept_Imag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Basic_activity.class);
                    intent.putExtra("message",conceptUrl);
                    context.startActivity(intent);


                }
            });
            final ImageView question_Imag=(ImageView)itemView.findViewById(R.id.question_image);
            question_Imag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Basic_activity.class);
                    intent.putExtra("message", questionUrl);
                    context.startActivity(intent);


                }
            });

            final ImageView answer_Imag=(ImageView)itemView.findViewById(R.id.answer_image);
            answer_Imag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Basic_activity.class);
                    intent.putExtra("message", answerUrl);
                    context.startActivity(intent);

                }
            });

            final Activity myActivity=(Activity)(itemView.getContext());
            favoriteImageButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    KEY = tileText;
                    final SharedPreferences prefs=myActivity.getSharedPreferences(NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor edt = prefs.edit();

                    FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
                    favourites=FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser.getUid()).child("favourites");
                    favourites.keepSynced(true);
                    favourites.push().child("title").setValue(tileText);

                    if(check){
                        draw = res.getDrawable(R.drawable.favourite_fill);
                        favoriteImageButton.setImageDrawable(draw);

                        Query applesQuery = favourites.orderByChild("title").equalTo(tileText);
                        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                    appleSnapshot.getRef().child("question").setValue(questionText);
                                    appleSnapshot.getRef().child("answer").setValue(answerText);
                                    appleSnapshot.getRef().child("image_concept").setValue(conceptUrl);
                                    appleSnapshot.getRef().child("question_image").setValue(questionUrl);
                                    appleSnapshot.getRef().child("answer_image").setValue(answerUrl);

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.e("CourseDetails", "onCancelled", databaseError.toException());
                            }
                        });

                        edt.putBoolean(KEY,false);
                        edt.apply();
                        check=prefs.getBoolean(KEY,true);
                        // check=false;
                    }else {
                        draw = res.getDrawable(R.drawable.favourite_blank);
                        favoriteImageButton.setImageDrawable(draw);

                        Query applesQuery = favourites.orderByChild("title").equalTo(tileText);
                        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                    appleSnapshot.getRef().removeValue();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.e("CourseDetails", "onCancelled", databaseError.toException());
                            }
                        });

                        edt.putBoolean(KEY,true);
                        edt.apply();
                        check=prefs.getBoolean(KEY,true);
                        //check=true;
                    }


                }
            });
        }

        public void setTitle(String title){
            tileText=title;
            KEY = tileText;
            final Activity myActivity=(Activity)(itemView.getContext());
            final SharedPreferences prefs=myActivity.getSharedPreferences(NAME, Context.MODE_PRIVATE);
            check=prefs.getBoolean(KEY,true);
            if(check){
                draw = res.getDrawable(R.drawable.favourite_blank);
                favoriteImageButton.setImageDrawable(draw);
            }else {
                draw = res.getDrawable(R.drawable.favourite_fill);
                favoriteImageButton.setImageDrawable(draw);
            }
            TextView title_text=(TextView)mView.findViewById(R.id.concept);
            title_text.setText(title);
        }

        public void setQuestion(String question) {
            questionText=question;
            TextView question_text = (TextView)mView.findViewById(R.id.question);
            question_text.setText(question);
        }

        public void setAnswer(String answer) {
            answerText=answer;
            TextView answer_text=(TextView)mView.findViewById(R.id.answer);
            answer_text.setText(answer);
        }
        final Context context=itemView.getContext();

        public void setImage_concept(String image_concept) {
            conceptUrl=image_concept;
            ImageView concept_Image=(ImageView) itemView.findViewById(R.id.concept_image);
            final ProgressBar progressBar1=itemView.findViewById(R.id.progressBar1);
            Picasso.get().load(image_concept)
                    .into(concept_Image, new Callback() {
                        @Override
                        public void onSuccess() {
                            progressBar1.setVisibility(View.GONE);

                        }

                        @Override
                        public void onError(Exception e) {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });
        }

        public void setQuestion_image(String question_image) {
            questionUrl=question_image;
            ImageView question_Image=(ImageView)mView.findViewById(R.id.question_image);
            final ProgressBar progressBar2=itemView.findViewById(R.id.progressBar2);
            Picasso.get().load(question_image)
                    .into(question_Image, new Callback() {
                        @Override
                        public void onSuccess() {
                            progressBar2.setVisibility(View.GONE);

                        }

                        @Override
                        public void onError(Exception e) {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });
        }
        public void setAnswer_image(String answer_image) {
            answerUrl=answer_image;
            final ProgressBar progressBar3=itemView.findViewById(R.id.progressBar3);
            ImageView answer_Image=(ImageView)mView.findViewById(R.id.answer_image);
            Picasso.get().load(answer_image)
                    .into(answer_Image, new Callback() {
                        @Override
                        public void onSuccess() {
                            progressBar3.setVisibility(View.GONE);

                        }

                        @Override
                        public void onError(Exception e) {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });
        }

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

   @Override
   public void onBackPressed(){

       if (isNetworkAvailable()){
           SharedPreferences prefs =getSharedPreferences(NAME, Context.MODE_PRIVATE);
           SharedPreferences.Editor editor = prefs.edit();
           editor.clear().apply();
       }

      FragmentManager fm = getFragmentManager();
      if (fm.getBackStackEntryCount() > 0) {
          Log.i("MainActivity", "popping backstack");
          fm.popBackStack();
      } else {
          Log.i("MainActivity", "nothing on backstack, calling super");
          super.onBackPressed();
      }
    }


}
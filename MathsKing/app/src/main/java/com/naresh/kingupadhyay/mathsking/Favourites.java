package com.naresh.kingupadhyay.mathsking;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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


public class Favourites extends AppCompatActivity{

    private Toolbar toolbar;
    private RecyclerView mBlogList;
    private static DatabaseReference mDatabase;
    public ProgressDialog progDailog;
    public static Drawable draw;
    protected String NAME="name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        //if (isNetworkAvailable()){
            SharedPreferences prefs =getSharedPreferences(NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear().apply();
        //}


        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        mDatabase=FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser.getUid()).child("favourites");
        mDatabase.keepSynced(true);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser.getUid());

        mBlogList=(RecyclerView)findViewById(R.id.fav_myrecyclerview);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

        // Adding Toolbar to Main screen
        toolbar = (Toolbar) findViewById(R.id.fav_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progDailog = new ProgressDialog(Favourites.this);
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

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild("favourites")) {

                    }else{
                        Toast.makeText(getApplicationContext(), "Favourites is empty", Toast.LENGTH_SHORT).show();
                        progDailog.dismiss();

                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<dChapter,mChapterViewHolder> firebaseRecyclerAdapter =new FirebaseRecyclerAdapter <dChapter, mChapterViewHolder>
                (dChapter.class,R.layout.favourite_contents,mChapterViewHolder.class,mDatabase) {
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
    }

    public static class mChapterViewHolder extends RecyclerView.ViewHolder{
        View mView;
        private RelativeLayout rl_ques;
        private RelativeLayout rl_ans;
        public Button expand_ques;
        public Button expand_ans;
        private String tileText;
        private String conceptUrl;
        private String questionUrl;
        private String answerUrl;
        private String NAME="name";
        private String KEY;

        final Resources res = itemView.getResources();
        final ImageButton favoriteImageButton = (ImageButton) itemView.findViewById(R.id.fav_add_favorite);

        public mChapterViewHolder(View itemView){
            super(itemView);
            mView=itemView;

            rl_ques=itemView.findViewById(R.id.fav_question_layout);
            rl_ques.setVisibility(View.GONE);
            expand_ques = (Button)itemView.findViewById(R.id.fav_expand_ques);
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

            rl_ans=itemView.findViewById(R.id.fav_answer_layout);
            rl_ans.setVisibility(View.GONE);
            expand_ans = (Button)itemView.findViewById(R.id.fav_expand_ans);
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

            final ImageView concept_Imag=(ImageView)itemView.findViewById(R.id.fav_concept_image);
            concept_Imag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Basic_activity.class);
                    intent.putExtra("message",conceptUrl);
                    context.startActivity(intent);


                }
            });
            final ImageView question_Imag=(ImageView)itemView.findViewById(R.id.fav_question_image);
            question_Imag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, Basic_activity.class);
                    intent.putExtra("message", questionUrl);
                    context.startActivity(intent);


                }
            });

            final ImageView answer_Imag=(ImageView)itemView.findViewById(R.id.fav_answer_image);
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

                        draw = res.getDrawable(R.drawable.favourite_blank);
                        favoriteImageButton.setImageDrawable(draw);

                        Query applesQuery = mDatabase.orderByChild("title").equalTo(tileText);
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

                }
            });


        }

        public void setTitle(String title){
            tileText=title;
            KEY = tileText;
            final Activity myActivity=(Activity)(itemView.getContext());
            final SharedPreferences prefs=myActivity.getSharedPreferences(NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor edt = prefs.edit();
            edt.putBoolean(KEY,false);
            edt.apply();
            draw = res.getDrawable(R.drawable.favourite_fill);
            favoriteImageButton.setImageDrawable(draw);
            TextView title_text=(TextView)mView.findViewById(R.id.fav_concept);
            title_text.setText(title);
        }

        public void setQuestion(String question) {
            TextView question_text = (TextView)mView.findViewById(R.id.fav_question);
            question_text.setText(question);
        }

        public void setAnswer(String answer) {
            TextView answer_text=(TextView)mView.findViewById(R.id.fav_answer);
            answer_text.setText(answer);
        }
        final Context context=itemView.getContext();

        public void setImage_concept(String image_concept) {
            conceptUrl=image_concept;
            ImageView concept_Image=(ImageView) itemView.findViewById(R.id.fav_concept_image);
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
            ImageView question_Image=(ImageView)mView.findViewById(R.id.fav_question_image);
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
            ImageView answer_Image=(ImageView)mView.findViewById(R.id.fav_answer_image);
            final ProgressBar progressBar3=itemView.findViewById(R.id.progressBar3);
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


}
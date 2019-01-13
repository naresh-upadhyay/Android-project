package com.naresh.kingupadhyay.mathsking;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public String name;
    public String phone;
    private static DatabaseReference favourite;
    private DatabaseReference update;
    private RecyclerView mBlogList;
    protected String NAME="name";
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Adding Toolbar to Main screen


        //if (isNetworkAvailable()){
        SharedPreferences prefs =getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear().apply();
        //}

        SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        name=pref.getString("name","Unknown Name");
        phone=pref.getString("number","Unknown Number");

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        String strDate = sdf.format(c.getTime());
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        update=FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser.getUid()).child("Last_online");
        update.onDisconnect().setValue(strDate);
        update.keepSynced(true);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        // Create Navigation drawer and inlfate layout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mBlogList=(RecyclerView)findViewById(R.id.recycler_view);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        TextView userName=(TextView)headerView.findViewById(R.id.id_name);
        userName.setText(name);
        TextView phoneNumber = (TextView) headerView.findViewById(R.id.id_phone);
        phoneNumber.setText(phone);
    }


    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser.getUid());
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("favourites")) {

                    favourite=FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser.getUid()).child("favourites");
                    favourite.keepSynced(true);
                    FirebaseRecyclerAdapter<dFavourite,ChapterViewHolder> firebaseRecyclerAdapter =new FirebaseRecyclerAdapter <dFavourite, ChapterViewHolder>
                            (dFavourite.class,R.layout.content_determinants,ChapterViewHolder.class,favourite) {
                        @Override
                        protected void populateViewHolder(ChapterViewHolder viewHolder, dFavourite model, int position) {
                            viewHolder.setTitle(model.getTitle());

                        }
                    };
                    mBlogList.setAdapter(firebaseRecyclerAdapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public static class ChapterViewHolder extends RecyclerView.ViewHolder{
        View mView;
        private String tileText;
        private String NAME="name";
        private String KEY;

        public ChapterViewHolder(View itemView){
            super(itemView);
            mView=itemView;

        }

        public void setTitle(String title){
            tileText=title;
            KEY = tileText;
            final Activity myActivity=(Activity)(itemView.getContext());
            final SharedPreferences prefs=myActivity.getSharedPreferences(NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor edt = prefs.edit();
            edt.putBoolean(KEY,false);
            edt.apply();
        }

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new Course(), "Course");
        adapter.addFragment(new ShortCut(), "ShortCut");
        adapter.addFragment(new Questions(), "Questions");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    public  int backpress=0;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            backpress = (backpress + 1);
            if(backpress<=1){
                Toast.makeText(getApplicationContext(), " Press Back again to Exit ", Toast.LENGTH_SHORT).show();
            }else{
                this.finish();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home) {

            } else if (id == R.id.r_books) {

            } else if (id == R.id.r_sites) {

            } else if (id == R.id.favourite) {

            Intent intent = new Intent(MainActivity.this, Favourites.class);
            startActivity(intent);

            } else if (id == R.id.e_time) {

            } else if (id == R.id.update) {

            } else if (id == R.id.share) {

            } else if (id == R.id.settings) {

            } else if (id == R.id.r_us) {

            } else if (id == R.id.a_us) {

            } else if (id == R.id.help) {

            }else if (id ==R.id.logout){
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(MainActivity.this,SplashActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    this.finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

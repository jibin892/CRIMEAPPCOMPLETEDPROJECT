package com.msg91.sendotp.sample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Objects;


public class MainActivityhome2 extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapter2 pageAdapter;
    TabItem tabChats;
    TabItem tabStatus;
    TabItem tabCalls;
    SharedPreferences sh,logout;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainhome2);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        sh=getSharedPreferences("Official1",MODE_PRIVATE);
        tabLayout = findViewById(R.id.tablayout);
        tabChats = findViewById(R.id.tabChats);
        SharedPreferences.Editor e=sh.edit();
        e.putBoolean("ph1",true);
        e.apply();
        viewPager = findViewById(R.id.viewPager);
        logout= Objects.requireNonNull(MainActivityhome2.this).getSharedPreferences("Official1",MODE_PRIVATE);
        pageAdapter = new PageAdapter2(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(MainActivityhome2.this,
                            R.color.green));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivityhome2.this,
                            R.color.green));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivityhome2.this,
                                R.color.green));
                    }
                } else if (tab.getPosition() == 2) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(MainActivityhome2.this,
                            R.color.green));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivityhome2.this,
                            R.color.green));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivityhome2.this,
                               R.color.green));
                    }
                } else {
                    toolbar.setBackgroundColor(ContextCompat.getColor(MainActivityhome2.this,
                            R.color.green));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivityhome2.this,
                            R.color.green));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivityhome2.this,
                                R.color.green));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;

            }
        }, 2000);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.logout) {
            SharedPreferences.Editor e = sh.edit();
            e.clear();
            e.apply();
            startActivity(new Intent(getApplication(), Admin_login.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }

        else if(id==R.id.missing11) {
            Intent i= new Intent(getApplicationContext(), Teachers_detailes_Admin.class);
            startActivity(i);
        }

        else if(id==R.id.comp1t) {
            Intent jj= new Intent(getApplicationContext(),Teachers_detailes_Admin1.class);
            startActivity(jj );
        }
        return super.onOptionsItemSelected(item);


    }
}


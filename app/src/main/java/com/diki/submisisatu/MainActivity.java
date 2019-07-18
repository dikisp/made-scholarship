package com.diki.submisisatu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_movies:
                    fragment = new fragmentMovies();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout,fragment, fragment. getClass().getSimpleName())
                            .commit();
                    return true;
                case R.id.navigation_tvShow:
                    fragment = new fragmentTvShow();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout,fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState == null) {
            navView.setSelectedItemId(R.id.navigation_movies);
        }
    }


    //setting
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu1:
                MenuFragment menuFragment = new MenuFragment();
                FragmentManager mFragmentManager = getSupportFragmentManager();
                FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.fragment_container, menuFragment);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
                Intent i = new Intent(this,MenuActivity.class);
                startActivity(i);
                return  true;
                default:
                    return true;
        }
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.menu1){
//            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
//            startActivity(mIntent);
//        }
//        return super.onOptionsItemSelected(item);
//    }

}

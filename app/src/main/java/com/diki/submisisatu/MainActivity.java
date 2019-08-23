package com.diki.submisisatu;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.diki.submisisatu.Fragment.FragmentMovies;
import com.diki.submisisatu.Fragment.FragmentTvShow;

public class MainActivity extends AppCompatActivity  {
    private TextView mTextMessage;
    private static final String STATE_RESULT = "state_result";
    private int position;
    private ViewPager viewPager;
    RecyclerView recyclerView;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_movies:
                    fragment = new FragmentMovies();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_layout,fragment, fragment. getClass().getSimpleName())
                            .commit();
                    return true;
                case R.id.navigation_tvShow:
                    fragment = new FragmentTvShow();
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

//        if (savedInstanceState != null){
//            String result = savedInstanceState.getString(STATE_RESULT);
//            recyclerView.toString(result);
//        }


        //view pager
//        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
//        ViewPagerAdapter viewPagerAdapter =new ViewPagerAdapter(getSupportFragmentManager(), this);
//        viewPager.setAdapter(viewPagerAdapter);
//
//        BottomNavigationView bottomNavigationView =findViewById(R.id.nav_view);
//        bottomNavigationView.setSelectedItemId();
    }



//    private void setupViewPager(ViewPager viewPager){
//        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), context);
//        pagerAdapter.add(new FragmentMovies());
//        pagerAdapter.addFragment(new FragmentTvShow());
//        viewPager.setAdapter(pagerAdapter);
//    }



    //change language

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_setting){
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    //rotate
    protected void onSavedInstanceState(final Bundle outSate){
        super.onSaveInstanceState(outSate);
        outSate.putInt(STATE_RESULT, position);
    }

    protected void onRestoreInstanceState(final Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        position = savedInstanceState.getInt(STATE_RESULT);
    }

}

package com.diki.myprofile.splashScreenPager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.diki.myprofile.R;



/**
 * 10 Agustus 2019
 * 101163352
 * Diki Supriadi
 * IF-8
 */

public class view_pager extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_pager);

        //Initializing view_pager
        viewPager = (ViewPager) findViewById(R.id.view_pager_app);

        setupViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        FragmentViewSatu fragmentViewSatu  = new FragmentViewSatu();
        FragmentViewDua fragmentViewDua  = new FragmentViewDua();
        FragmentViewTiga fragmentViewTiga  = new FragmentViewTiga();

        adapter.addFragment(fragmentViewSatu);
        adapter.addFragment(fragmentViewDua);
        adapter.addFragment(fragmentViewTiga);
        viewPager.setAdapter(adapter);
    }
}


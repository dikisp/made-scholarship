package com.diki.submisisatu.ViewPager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diki.submisisatu.Adapter.ViewPagerAdapter;
import com.diki.submisisatu.Fragment.FragmentMovies;
import com.diki.submisisatu.Fragment.FragmentTvShow;
import com.diki.submisisatu.R;

import static com.diki.submisisatu.R.*;
import static com.diki.submisisatu.R.id.*;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class ViewPager extends AppCompatActivity {

    //Initializing view_pager
    private ViewPager viewPager;

    public ViewPager() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.fragment_view_pager);

        setupViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        FragmentMovies fragmentMovies = new FragmentMovies();
        FragmentTvShow fragmentTvShow = new FragmentTvShow();

        adapter.addFragment(fragmentMovies);
        adapter.addFragment(fragmentTvShow);
    }
}

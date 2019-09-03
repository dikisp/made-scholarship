package com.diki.submisisatu.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.diki.submisisatu.Fragment.FragmentMovies;
import com.diki.submisisatu.Fragment.FragmentTvShow;
import com.diki.submisisatu.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final Context context;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new FragmentMovies();
            case 1:
                return new FragmentTvShow();
        }

        return null;
    }

    @SuppressWarnings("SameReturnValue")
    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getResources().getString(R.string.movies);
            case 1:
                return context.getResources().getString(R.string.tvShow);
            default:
                return null;
        }
    }
}

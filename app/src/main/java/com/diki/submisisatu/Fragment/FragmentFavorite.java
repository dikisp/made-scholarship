package com.diki.submisisatu.Fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.diki.submisisatu.Adapter.ViewPagerAdapter;
import com.diki.submisisatu.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFavorite extends Fragment {
    View view;
    Adapter adapter;

    private void setupViewPager(ViewPager viewPager) {
        adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new FragmentMovies(), "Movie");
        adapter.addFragment(new FragmentTvShow(), "Tv Show");
        viewPager.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorite, container, false);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this, view);
        final ViewPager viewPager = ButterKnife.findById(view, R.id.main_pager);
        setupViewPager(viewPager);
        TabLayout tabLayout = ButterKnife.findById(view, R.id.main_tablayout);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.unbind(this);
//    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}






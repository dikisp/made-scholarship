package com.diki.myprofile.main;
// 17 MEI
// 10116352
// DIKI SUPRIADI
// IF-8
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.diki.myprofile.Model.AppDatabase;
import com.diki.myprofile.Preferences;
import com.diki.myprofile.login.Login;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.diki.myprofile.R;
import com.diki.myprofile.contact.ContactFragment;
import com.diki.myprofile.list_friends.ListFriendsFragment;
import com.diki.myprofile.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    public static AppDatabase db;
    private int menuProfile = 0;
    private int menuContact = 1;
    private int menuListFriends = 2;

    BottomNavigationView bottomNavigationView;
    private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView nama = findViewById(R.id.tv_namaMain);

        nama.setText(Preferences.getLoggedInUser(getBaseContext()));


        findViewById(R.id.button_logoutMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferences.clearLoggedInUser(getBaseContext());
                startActivity(new Intent(getBaseContext(), Login.class));
                finish();


                db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "db").allowMainThreadQueries().build();
            }
        });

        mPager = findViewById(R.id.vp_main);

        bottomNavigationView = findViewById(R.id.bnv_main);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.itemProfile:
                        mPager.setCurrentItem(menuProfile);
                        break;
                    case R.id.itemContact:
                        mPager.setCurrentItem(menuContact);
                        break;
                    case R.id.itemListFriends:
                        mPager.setCurrentItem(menuListFriends);
                }
                return false;
            }
        });

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            MenuItem prevMenuItem;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null){
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                MenuItem bottomIconActive = bottomNavigationView.getMenu().getItem(position);
                bottomIconActive.setChecked(true);
                prevMenuItem = bottomIconActive;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(mPager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mPager.getCurrentItem() == 0){
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private void setupViewPager(ViewPager viewPager){
        MainAdapter pagerAdapter = new MainAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new ProfileFragment());
        pagerAdapter.addFragment(new ContactFragment());
        pagerAdapter.addFragment(new ListFriendsFragment());
//        pagerAdapter.addFragment(new);
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if (item.getItemId() == R.id.logout){
           Preferences.clearLoggedInUser(getBaseContext());
           startActivity(new Intent(getBaseContext(),Login.class));
           finish();
       }
        return super.onOptionsItemSelected(item);
    }


}

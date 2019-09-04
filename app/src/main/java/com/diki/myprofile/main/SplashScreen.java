package com.diki.myprofile.main;
// 17 MEI
// 10116352
// DIKI SUPRIADI
// IF-8
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.diki.myprofile.R;
import com.diki.myprofile.login.Login;
import com.diki.myprofile.splashScreenPager.view_pager;

public class SplashScreen extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Intent intent = new Intent(this, Login.class);
//        startActivity(intent);
//        finish();
//    }

    SharedPreferences sharedPreferences;
    private String loginSts = "0";
    private Button button;

    public static final String MyPREFERENCES = "ViewPager";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        if (loginSts.equals("1")) {
            Intent intent = new Intent(SplashScreen.this, Login.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(SplashScreen.this, view_pager.class);
            startActivity(intent);
            finish();
        }



    }
}

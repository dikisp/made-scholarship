package com.diki.myprofile.splashScreenPager;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.diki.myprofile.R;
import com.diki.myprofile.login.Login;

public class ViewPagerExit extends AppCompatActivity {
    private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_view_tiga);
        super.onCreate(savedInstanceState);

        button = (Button) findViewById(R.id.btn_finish_vp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Login.class);
                startActivity(intent);
            }
        });

}


}

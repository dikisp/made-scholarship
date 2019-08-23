package com.diki.submisisatu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.diki.submisisatu.Model.Movie;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailTVActivity extends AppCompatActivity {
    private TextView  title, tvRelease, deskripsi, rating, voteCount;
    private static final String posterPath = BuildConfig.POSTER_PATH;
    private ImageView circleImageView;
    public static final  String EXTRA_TV = "EXTRA_TV";
    public static final  String TAG = "cek";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);


        circleImageView = findViewById(R.id.gambarMovie);
        title = findViewById(R.id.tvTitle);
        deskripsi = findViewById(R.id.tvOverview);
        tvRelease = findViewById(R.id.tvRelease);
        rating = findViewById(R.id.tvShowRating);
        voteCount = findViewById(R.id.TVvoteCount);

        Intent intent = getIntent();
        Movie data = intent.getParcelableExtra(EXTRA_TV);
//        Log.d("cek",data.getOriginalTitle());
        Log.d("cek",data.toString());

        Glide.with(this).load(posterPath+data.getPosterPath())
                .into(circleImageView);
        title.setText(data.getName());
        rating.setText(String.valueOf(data.getVoteAverage()));
        deskripsi.setText(data.getOverview());
        tvRelease.setText(data.getReleaseDate());
        voteCount.setText(String.valueOf(data.getVoteCount()));

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

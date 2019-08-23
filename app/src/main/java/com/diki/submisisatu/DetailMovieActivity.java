package com.diki.submisisatu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.diki.submisisatu.Model.Movie;

import java.util.Objects;


public class DetailMovieActivity extends AppCompatActivity {
   private TextView  title, tvRelease, deskripsi, rating, voteCount;
   private static final String posterPath = BuildConfig.POSTER_PATH;
   private ImageView circleImageView;
   public static final  String EXTRA_MOVIE = "extra_movie";
   public static final  String TAG = "cek";
   private ProgressBar loading;
   private String mPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);


        circleImageView = findViewById(R.id.poster);
        title = findViewById(R.id.tvName);
        deskripsi = findViewById(R.id.tvOverview);
        tvRelease = findViewById(R.id.tvRelease);
        rating = findViewById(R.id.tvRating);
        voteCount = findViewById(R.id.tvVote);
        loading = findViewById(R.id.Loading);
        setView(false);
        //

        Intent intent = getIntent();
        Movie data = intent.getParcelableExtra(EXTRA_MOVIE);
//        Log.d("cek",data.getOriginalTitle());
        Log.d("cek", data.toString());

        if (data != null) {
            setView(true);

            Glide.with(this).load(posterPath + data.getPosterPath())
                    .into(circleImageView);
            title.setText(data.getOriginalTitle());
            rating.setText(String.valueOf(data.getVoteAverage()));
            deskripsi.setText(data.getOverview());
            tvRelease.setText(data.getReleaseDate());
            voteCount.setText(String.valueOf(data.getVoteCount()));

            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }
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

    private void setView(Boolean status){
        if(status){
            loading.setVisibility(View.GONE);
            circleImageView.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
            rating.setVisibility(View.VISIBLE);
            deskripsi.setVisibility(View.VISIBLE);
        }
        else{
            loading.setVisibility(View.VISIBLE);
            circleImageView.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            rating.setVisibility(View.GONE);
            deskripsi.setVisibility(View.GONE);
        }
    }

    //rotate
    protected void onSaveInstanceState (final Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(EXTRA_MOVIE,mPosition);
    }

    protected void onRestoreInstanceState(final Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        mPosition = savedInstanceState.getString(EXTRA_MOVIE);
    }
}

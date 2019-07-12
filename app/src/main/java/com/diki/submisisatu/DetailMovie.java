package com.diki.submisisatu;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailMovie extends AppCompatActivity {
    TextView  title, genre, release, deskripsi, rating, income, produksi;
    CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);


        circleImageView = findViewById(R.id.PosterMovie);
        title = findViewById(R.id.movieName);
        genre = findViewById(R.id.movieGenre);
        deskripsi = findViewById(R.id.tvOverview);
        release = findViewById(R.id.dateRelease);
        produksi = findViewById(R.id.movieProduction);
        rating = findViewById(R.id.movieRating);
        income = findViewById(R.id.movieIncome);

        Intent intent = getIntent();
        Movie data = intent.getParcelableExtra(Utils.parcel);

        Glide.with(this).load(data.getPoster())
                .into(circleImageView);
        title.setText(data.getTitle());
        genre.setText( data.getGenre());
        deskripsi.setText(data.getDeskripsi());
        release.setText(data.getRealese());
        produksi.setText(data.getPruduksi());
        rating.setText(data.getRating());
        income.setText(data.getIncome());

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

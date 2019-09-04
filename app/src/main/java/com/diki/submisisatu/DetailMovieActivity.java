package com.diki.submisisatu;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.diki.submisisatu.Database.AppDatabase;
import com.diki.submisisatu.Model.DataFavoriteMovie;
import com.diki.submisisatu.Model.Movie;

import java.util.Objects;


public class DetailMovieActivity extends AppCompatActivity implements View.OnClickListener{
   private TextView  title, tvRelease, deskripsi, rating, voteCount;
   private static final String posterPath = BuildConfig.POSTER_PATH;
   private ImageView circleImageView;
   private Button buttonDelete, buttonFavorite;
   public static final  String EXTRA_MOVIE = "extra_movie";
   public static final  String TAG = "cek";
   private ProgressBar loading;
   private String mPosition;
   boolean adaData = false;
   private AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        buttonDelete = findViewById(R.id.btn_delete);
        buttonFavorite = findViewById(R.id.btn_fav);
        circleImageView = findViewById(R.id.poster);
        title = findViewById(R.id.tvName);
        deskripsi = findViewById(R.id.tvOverview);
        tvRelease = findViewById(R.id.tvRelease);
        rating = findViewById(R.id.tvRating);
        voteCount = findViewById(R.id.tvVote);
        loading = findViewById(R.id.Loading);
        setView(false);
        //


        //btn fav
        buttonFavorite.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "db").allowMainThreadQueries().build();

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


        //add to fav
        final DataFavoriteMovie dataFavoriteMovie = (DataFavoriteMovie) getIntent().getSerializableExtra("data");
         if (dataFavoriteMovie != null){
             Glide.with(this).load(posterPath + data.getPosterPath())
                     .into(circleImageView);
             title.setText(data.getOriginalTitle());
             rating.setText(String.valueOf(data.getVoteAverage()));
             deskripsi.setText(data.getOverview());
             tvRelease.setText(data.getReleaseDate());
             voteCount.setText(String.valueOf(data.getVoteCount()));
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


    //add to favorite





    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.btn_delete) :
//                saveFavoriteMovie;
                break;
            case (R.id.btn_fav) :
                DataFavoriteMovie a =new DataFavoriteMovie();
                 a = new DataFavoriteMovie();
                a.setOriginalTitle(title.getText().toString());
                a.setOverview(deskripsi.getText().toString());
                if (adaData == true) {
                    updateData(a);
                } else {
                    insertData(a);
                }

        }
    }

    private void updateData(final DataFavoriteMovie friend){
        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = (long) db.dao().updateFriend(friend);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(DetailMovieActivity.this, "status row "+status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }


    private void insertData(final DataFavoriteMovie friend){

        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = (long) db.dao().insertFriend(friend);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(DetailMovieActivity.this, "Add Friend Success "+status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, DataFavoriteMovie.class);
    }



}

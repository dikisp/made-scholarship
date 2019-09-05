package com.diki.submisisatu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
import com.diki.submisisatu.Item.Support;
import com.diki.submisisatu.Model.DataFavoriteMovie;
import com.diki.submisisatu.Model.Movie;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class DetailMovieActivity extends AppCompatActivity {
   private TextView  title, tvRelease, deskripsi, rating, voteCount;
   private static final String posterPath = BuildConfig.POSTER_PATH;
   private ImageView circleImageView;
   private Button buttonDelete, buttonFavorite;
   private AppDatabase Db;
   public static final  String EXTRA_MOVIE = "extra_movie";
   public static final  String TAG = "cek";
   private ProgressBar loading;
   private String mPosition;
   boolean adaData = false;
   List<DataFavoriteMovie> enter = new ArrayList<>();
   private AppDatabase db;

   Movie movie;
   String poster, name, overview , realeseDate;
   int movie_id;

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
    public void saveFavorite(){
        Double rate = movie.getVoteAverage();
        final DataFavoriteMovie dataFavoriteMovie = new DataFavoriteMovie(movie_id, name, poster, overview);
        Support.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {

                    db.dao().insertFavorite(dataFavoriteMovie);


            }
        });
    }

    private void deleteFavorite(final int movie_id){
        Support.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                db.dao().deleteFavoriteWithId(movie_id);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void checkStatus(final String movieName){
        final MaterialFavoriteButton materialFavoriteButton = (MaterialFavoriteButton) findViewById(R.id.favorite_button);
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params){
                enter.clear();
                enter = db.dao().loadAll(movieName);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                if (enter.size() > 0){
                    materialFavoriteButton.setFavorite(true);
                    materialFavoriteButton.setOnFavoriteChangeListener(
                            new MaterialFavoriteButton.OnFavoriteChangeListener() {
                                @Override
                                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                                    if (favorite == true) {
                                        saveFavorite();
                                        Snackbar.make(buttonView, "Added to Favorite",
                                                Snackbar.LENGTH_SHORT).show();
                                    } else {
                                        deleteFavorite(movie_id);
                                        Snackbar.make(buttonView, "Removed from Favorite",
                                                Snackbar.LENGTH_SHORT).show();
                                    }
                                }
                            });


                }else {
                    materialFavoriteButton.setOnFavoriteChangeListener(
                            new MaterialFavoriteButton.OnFavoriteChangeListener() {
                                @Override
                                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                                    if (favorite == true) {
                                        saveFavorite();
                                        Snackbar.make(buttonView, "Added to Favorite",
                                                Snackbar.LENGTH_SHORT).show();
                                    } else {
                                        int movie_id = getIntent().getExtras().getInt("id");
                                        deleteFavorite(movie_id);
                                        Snackbar.make(buttonView, "Removed from Favorite",
                                                Snackbar.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        }.execute();
    }








    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, DataFavoriteMovie.class);
    }



}

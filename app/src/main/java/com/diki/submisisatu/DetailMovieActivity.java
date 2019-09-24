package com.diki.submisisatu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.diki.submisisatu.Adapter.ListMovieAdapter;
import com.diki.submisisatu.Database.AppDatabase;
import com.diki.submisisatu.Model.FavoriteMovie;
import com.diki.submisisatu.Model.Movie;
import com.diki.submisisatu.data.AppExecutors;
import com.diki.submisisatu.data.FavoriteDbHelper;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class DetailMovieActivity extends AppCompatActivity {
    private TextView title, tvRelease, deskripsi, rating, voteCount;
    private static final String posterPath = BuildConfig.POSTER_PATH;
    private ImageView circleImageView;
    public static final String EXTRA_MOVIE = "extra_movie";
    public static String MOVIE_ID = "movie_id";
    public static String LOCAL_STATUS = "local_status";
    public static final String TAG = "cek";
    private ProgressBar loading;
    private String mPosition;

    Movie movie;
    private Uri uri;
    private RecyclerView recyclerView;
    private ListMovieAdapter adapter;


    String posterMovie, movieName, overview, ratingMovie, dateOfRelease;
    int movie_id;
    List<FavoriteMovie> entries = new ArrayList<>();

    private AppDatabase db;


    private boolean favourite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        FavoriteDbHelper dbHelper = new FavoriteDbHelper(this);
        db = AppDatabase.getInstance(getApplicationContext());

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
        Log.d("cek", data.toString());


        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("movies")) {

            movie = getIntent().getParcelableExtra("movies");

            posterMovie = movie.getPosterPath();
            movieName = movie.getOriginalTitle();
            overview = movie.getOverview();
            ratingMovie = Double.toString(movie.getVoteAverage());
            dateOfRelease = movie.getReleaseDate();
            movie_id = movie.getId();

            String poster = "https://image.tmdb.org/t/p/w500" + posterMovie;

            Glide.with(this)
                    .load(poster + data.getPosterPath())
                    .into(circleImageView);

            title.setText(movieName);
            deskripsi.setText(overview);
            rating.setText(ratingMovie);
            tvRelease.setText(dateOfRelease);

            //TODO

        } else {
            Toast.makeText(this, "No API Data", Toast.LENGTH_SHORT).show();
        }


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
        checkStatus(movieName);
        initViews();
    }

    @SuppressLint("StaticFieldLeak")
    private void checkStatus(final String movieName) {
        final MaterialFavoriteButton materialFavoriteButton = findViewById(R.id.favorite_button);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                entries.clear();
                entries = db.dao().loadAll(movieName);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
//                if (entries.size() > 0){
//                    materialFavoriteButton.setFavorite(true);
//                    materialFavoriteButton.setOnFavoriteChangeListener(
//                            new MaterialFavoriteButton.OnFavoriteChangeListener() {
//                                @Override
//                                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
//                                    if (favorite == true) {
//                                        saveFavorite();
//                                        Snackbar.make(buttonView, "Added to Favorite",
//                                                Snackbar.LENGTH_SHORT).show();
//                                    } else {
//                                        deleteFavorite(movie_id);
//                                        Snackbar.make(buttonView, "Removed from Favorite",
//                                                Snackbar.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//
//
//                } else {
//                    materialFavoriteButton.setOnFavoriteChangeListener(
//                            new MaterialFavoriteButton.OnFavoriteChangeListener() {
//                                @Override
//                                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
//                                    if (favorite == true) {
//                                        saveFavorite();
//                                        Snackbar.make(buttonView, "Added to Favorite",
//                                                Snackbar.LENGTH_SHORT).show();
//                                    } else {
//                                        int movie_id = getIntent().getExtras().getInt("id");
//                                        deleteFavorite(movie_id);
//                                        Snackbar.make(buttonView, "Removed from Favorite",
//                                                Snackbar.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//                }
                if (entries.size() > 0) {
                    Log.d("cekcekcek", "bisa coy");
                    saveFavorite();
                }
            }
        }.execute();
    }

    //add to favorite
    public void saveFavorite() {
        Double rate = movie.getVoteAverage();

        final FavoriteMovie favorite = new FavoriteMovie(movie.getId());

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                db.dao().insertFavorite(favorite);
            }
        });
    }

    private void deleteFavorite(final int movie_id) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
//                db.dao().deleteFavoriteWithId(movie_id);
            }
        });
    }

    //    @SuppressLint("WrongViewCast")
    private void initViews() {

//        recyclerView = (RecyclerView) findViewById(R.id.rv_listMovie);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

        loadJSON();
        loadReview();
    }

    private void loadReview() {
    }

    private void loadJSON() {
//        try{
//            if (BuildConfig.APIKEY.isEmpty()){
//                Toast.makeText(getApplicationContext(), "Please get your API Key", Toast.LENGTH_SHORT).show();
//                return;
//            }else {
//                RetrofitApi Client = new RetrofitApi();
//                Service apiService = Client.getClient().create(Service.class);
//                }
//
//        } catch(Exception e){
//            Log.d("Error", e.getMessage());
//            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
//        }
    }


    private void getMovieSqlite() {
        Log.d(TAG, "getMovieSqlite: berjalan");


        Cursor cursor = getContentResolver().query(uri,
                null,
                null,
                null,
                null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                movie = new Movie();

                title.setText(movie.getOriginalTitle());

            }
            cursor.close();
        }

    }

    //=====================================================================
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setView(Boolean status) {
        if (status) {
            loading.setVisibility(View.GONE);
            circleImageView.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
            rating.setVisibility(View.VISIBLE);
            deskripsi.setVisibility(View.VISIBLE);
        } else {
            loading.setVisibility(View.VISIBLE);
            circleImageView.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            rating.setVisibility(View.GONE);
            deskripsi.setVisibility(View.GONE);
        }
    }

    //rotate
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EXTRA_MOVIE, mPosition);
    }

    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mPosition = savedInstanceState.getString(EXTRA_MOVIE);
    }

    public void setRuntime(int anInt) {
    }
}
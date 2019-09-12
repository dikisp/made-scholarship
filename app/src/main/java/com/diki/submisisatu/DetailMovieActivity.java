package com.diki.submisisatu;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.diki.submisisatu.Database.DatabaseContract;
import com.diki.submisisatu.Model.Movie;

import java.util.Objects;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.diki.submisisatu.BuildConfig.IMAGE_BASE_URL;
import static com.diki.submisisatu.Database.DatabaseContract.CONTENT_URI;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.BACKDROP_URL;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.HOMEPAGE;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.ORIGINAL_LANGUAGE;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.OVERVIEW;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.POSTER_URL;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.RELEASE_DATE;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.RUNTIME;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.STATUS;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.TAGLINE;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.TITLE;
import static com.diki.submisisatu.Database.DatabaseContract.FavouriteColumns.VOTE_AVERAGE;
import static com.diki.submisisatu.Database.DatabaseContract.getColumnString;


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


    //favoirte
    @BindView(R.id.icon_favorite_unclicked)
    ImageButton icFavoriteUnclicked;

    @BindView(R.id.icon_favorite_unclicked)
    ImageButton icFavoriteClicked;

    @BindView(R.id.tvRelease)
    TextView movieYear;

    @BindView(R.id.tvTitle)
    TextView titleMovie;

    @BindView(R.id.tvRating)
    TextView ratingMovie;

    @BindView(R.id.tvOverview)
    TextView overviewMovie;


    Movie movie;
    private Uri uri;

    private String check;
    private int movie_id;

    private boolean favourite;


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


        //favorite
        if (savedInstanceState != null) {
            movie = savedInstanceState.getParcelable("movies");
            checkAgain();
        } else {
            checkStatus();
        }

        icFavoriteUnclicked.setOnClickListener((View.OnClickListener) this);
        icFavoriteClicked.setOnClickListener((View.OnClickListener) this);


        loadSqliteData();
    }

    //favorite
    private void checkStatus() {
        if (check.equals("1")) {
            Log.d(TAG, "onCreate: sama cuy");
            getMovieSqlite();
        }
        if (check.equals("0")) {
            Log.d(TAG, "onCreate: gak sama cuy");
            getDetailMovie();
        }
    }

    private void checkAgain() {
        if (check.equals("1")) {
            Log.d(TAG, "onCreate: sama cuy");
            getMovieSqlite();
        }
        if (check.equals("0")) {
            Log.d(TAG, "onCreate: gak sama cuy");
            setMovie();
        }
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

//                String poster_url = IMAGE_BASE_URL + "w342" + movie.getPosterPath();
//                String backdrop_url = IMAGE_BASE_URL + "w780" + movie.getBackdropPath();

                title.setText(movie.getOriginalTitle());
                movieYear.setText(movie.getReleaseDate().split("-")[0]);

                //buat set apabila taglinenya kosong


                ratingMovie.setText(movie.getVoteAverage().toString());
                movieYear.setText(movie.getReleaseDate());
                overviewMovie.setText(movie.getOverview());



            }
            cursor.close();
        }

    }


    private void loadSqliteData() {
        Cursor cursor = getContentResolver().query(Uri.parse(CONTENT_URI + "/" + movie_id),
                null,
                null,
                null,
                null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String idIntent = "" + movie_id;
                String movieId = getColumnString(cursor, DatabaseContract.FavouriteColumns.MOVIE_ID);
                if (movieId.equals(idIntent)) favourite = true;
            }
            cursor.close();
        } else favourite = false;
        setFavoriteIcon();
    }

    private void setFavoriteIcon() {
        if (favourite) {
            icFavoriteUnclicked.setVisibility(View.INVISIBLE);
            icFavoriteClicked.setVisibility(View.VISIBLE);
        } else {
            icFavoriteUnclicked.setVisibility(View.VISIBLE);
            icFavoriteClicked.setVisibility(View.INVISIBLE);
        }
    }


    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.icon_favorite_unclicked) {
            favourite = true;
            setFavoriteIcon();

            ContentValues values = new ContentValues();

            Movie movie = null;
            values.put(MOVIE_ID, movie.getId());
            values.put(TITLE, movie.getOriginalTitle());
            values.put(RELEASE_DATE, movie.getReleaseDate());
//            values.put(TAGLINE, movie.getTagline());
            values.put(VOTE_AVERAGE, movie.getVoteAverage());
            values.put(OVERVIEW, movie.getOverview());
//            values.put(STATUS, detailMovie.getStatus());
//            values.put(ORIGINAL_LANGUAGE, detailMovie.getOriginalLanguage());
            values.put(RUNTIME, movie.getRuntime());
//            values.put(HOMEPAGE, detailMovie.getHomepage());
            values.put(POSTER_URL, movie.getPosterPath());
            values.put(BACKDROP_URL, movie.getBackdropPath());

            getContentResolver().insert(CONTENT_URI, values);

            sendUpdateFavoriteList(this);

            Toast.makeText(this, R.string.add_favourite, Toast.LENGTH_SHORT).show();


        } else if (id == R.id.icon_favorite_clicked) {
            favourite = false;
            setFavoriteIcon();

            getContentResolver().delete(Uri.parse(CONTENT_URI + "/" + movie_id),
                    null,
                    null);

            sendUpdateFavoriteList(this);

            Toast.makeText(this, R.string.remove_favourite, Toast.LENGTH_SHORT).show();
        }
    }
//=====================================================================
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

    public void setRuntime(int anInt) {
    }
}
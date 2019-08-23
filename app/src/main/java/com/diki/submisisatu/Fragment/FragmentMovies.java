package com.diki.submisisatu.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.diki.submisisatu.Adapter.ListMovieAdapter;
import com.diki.submisisatu.Api.MovieApi;
import com.diki.submisisatu.Api.RetrofitApi;
import com.diki.submisisatu.Api.Scraper;
import com.diki.submisisatu.BuildConfig;
import com.diki.submisisatu.Model.Movie;
import com.diki.submisisatu.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMovies extends Fragment {
    RecyclerView rvCategory;
    private ListMovieAdapter listMovieAdapter;
    private ArrayList<Movie> list ;
    private static final String API_KEY = BuildConfig.APIKEY;
    private final List<Movie>  movieList = new ArrayList<>();
    private ProgressBar loading;
    private static final String STATE_RESULT = "state_result";
    static final String SOME_VALUE = "int_value";
    private int position;

    public FragmentMovies() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvMainMovie = view.findViewById(R.id.lv_movies);

        if(savedInstanceState != null){
            list = savedInstanceState.getParcelableArrayList(STATE_RESULT);
            movieList.addAll(Objects.requireNonNull(list));
        }

        listMovieAdapter = new ListMovieAdapter(getContext(),movieList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvMainMovie.setLayoutManager(layoutManager);
        rvMainMovie.setAdapter(listMovieAdapter);
        setView(false);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_movies, container, false);
//        return inflater.inflate(R.layout.fragment_fragment_movies, container, false);

        rvCategory = v.findViewById(R.id.lv_movies);

        list = new ArrayList<>();

        if(savedInstanceState != null){
            list = savedInstanceState.getParcelableArrayList(STATE_RESULT);
            movieList.addAll(Objects.requireNonNull(list));
        }

        fetchDataMovie();
        listMovieAdapter = new ListMovieAdapter(getContext(),movieList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvCategory.setLayoutManager(layoutManager);
        rvCategory.setAdapter(listMovieAdapter);
        loading = v.findViewById(R.id.Loading);
        return v;
    }


    private  void fetchDataMovie(){
        MovieApi movieApi = RetrofitApi.getClient().create(MovieApi.class);
        movieApi.findNowPlayingMovies(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Scraper<Movie>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Scraper<Movie> movieScraper) {
                        initData(movieScraper.getResultMovies());
                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }


    private void setView(Boolean status){
        if(status){
            loading.setVisibility(View.GONE);
            rvCategory.setVisibility(View.VISIBLE);

        }
        else{
            loading.setVisibility(View.VISIBLE);
            rvCategory.setVisibility(View.VISIBLE);
        }
    }

    private void initData(List<Movie> Movie){
        movieList.clear();
        movieList.addAll(Movie);
        listMovieAdapter.notifyDataSetChanged();
        if(movieList.size()> 0){
            setView(true);
        }
    }



    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList(STATE_RESULT,list);
    }


}

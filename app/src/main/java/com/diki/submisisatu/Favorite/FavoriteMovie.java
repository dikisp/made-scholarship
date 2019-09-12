package com.diki.submisisatu.Favorite;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.diki.submisisatu.Database.AppDatabase;
import com.diki.submisisatu.DetailMovieActivity;
import com.diki.submisisatu.Model.DataFavoriteMovie;
import com.diki.submisisatu.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FavoriteMovie.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FavoriteMovie#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteMovie extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<DataFavoriteMovie> favoriteMovies;
    private AppDatabase db;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FavoriteMovie() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FavoriteMovie newInstance(String param1, String param2) {
        FavoriteMovie fragment = new FavoriteMovie();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_movies, container, false);

        favoriteMovies = new ArrayList<>();

        db = Room.databaseBuilder(this.getContext().getApplicationContext(),
                AppDatabase.class, "db").allowMainThreadQueries().build();
        final FragmentActivity c = getActivity();

        recyclerView = view.findViewById(R.id.lv_movies);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);

//        favoriteMovies.addAll(Arrays.asList(db.dao().loadAll()));
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                final DetailMovieActivity adapter = new DetailMovieActivity(favoriteMovies, c);
//                c.runOnUiThread(new Runnable() {
//                    public void run() {
//                        recyclerView.setAdapter(adapter);
//                    }
//                });
//            }
//        }).start();


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

package com.diki.submisisatu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentMovies extends Fragment {
    RecyclerView rvCategory;
    private ArrayList<Movie> list ;

    public fragmentMovies() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_movies, container, false);

        rvCategory = v.findViewById(R.id.lv_category);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>();
        list.addAll(MovieData.getListData());
        showRecyclerList();

        return v;
    }

    private void showRecyclerList() {
        rvCategory.setLayoutManager(new LinearLayoutManager(requireContext()));
        listMovieAdapter listMovieAdapter = new listMovieAdapter(requireContext());
        listMovieAdapter.setListMovie(list);
        rvCategory.setAdapter(listMovieAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v){
                showSelectedPresident(list.get(position));
            }
        });
    }

    private void showSelectedPresident(Movie movie){
        Toast.makeText(requireContext(), "Kamu memilih "+ movie.getTitle(), Toast.LENGTH_SHORT).show();
    }
}

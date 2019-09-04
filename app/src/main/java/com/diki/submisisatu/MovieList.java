//package com.diki.submisisatu;
////
////import android.content.Intent;
////import android.os.Bundle;
////import android.support.annotation.NonNull;
////import android.support.design.widget.BottomNavigationView;
////import android.support.v4.app.Fragment;
////import android.support.v7.app.AppCompatActivity;
////import android.support.v7.widget.LinearLayoutManager;
////import android.support.v7.widget.RecyclerView;
////import android.view.Menu;
////import android.view.MenuItem;
////import android.view.View;
////import android.widget.ImageView;
////import android.widget.TextView;
////import android.widget.Toast;
////
////import com.diki.submisisatu.Adapter.ListMovieAdapter;
////import com.diki.submisisatu.Fragment.FragmentMovies;
////import com.diki.submisisatu.Fragment.FragmentTvShow;
////import com.diki.submisisatu.Item.ItemClickSupport;
////import com.diki.submisisatu.Model.Movie;
////
////import java.util.ArrayList;
////
////
////public class MovieList extends AppCompatActivity  implements View.OnClickListener {
////    RecyclerView rvCategory;
////    private ArrayList<Movie> list ;
////    private String title = "Move list";
////    final String STATE_TITLE = "state_string";
////    final String STATE_LIST = "state_list";
////    final String STATE_MODE = "state_mode";
////    int mode;
////    ImageView btnIntent;
////
////
////
////    //boyttom navigation
////
////    private TextView mTextMessage;
////
////    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
////            = new BottomNavigationView.OnNavigationItemSelectedListener() {
////
////        @Override
////        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
////            Fragment fragment;
////            switch (item.getItemId()) {
////                case R.id.navigation_movies:
////                    fragment = new FragmentMovies();
////                    getSupportFragmentManager().beginTransaction()
////                            .replace(R.id.container_layout,fragment, fragment. getClass().getSimpleName())
////                            .commit();
////                    return true;
////                case R.id.navigation_tvShow:
////                    fragment = new FragmentTvShow();
////                    getSupportFragmentManager().beginTransaction()
////                            .replace(R.id.container_layout,fragment, fragment.getClass().getSimpleName())
////                            .commit();
////                    return true;
////            }
////            return false;
////        }
////
////    };
////
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.fragment_fragment_movies);
////
////
////
////        BottomNavigationView navView = findViewById(R.id.nav_view);
////        mTextMessage = findViewById(R.id.message);
////        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
////
////        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
////        if(savedInstanceState == null){
////            navView.setSelectedItemId(R.id.navigation_movies);
////        }
////        rvCategory = findViewById(R.id.lv_movies);
////        rvCategory.setHasFixedSize(true);
////
////
//////        list = new ArrayList<>();
//////        if (savedInstanceState == null){
//////            setActionBarTitle("Catalogue Movie");
//////            list.addAll(MovieData.getListData());
//////            showRecyclerList();
//////            mode = R.id.action_change_setting;
//////        }else
//////        {
//////            String stateTitle = savedInstanceState.getString(STATE_TITLE);
//////            ArrayList<Movie> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
//////            int stateMode = savedInstanceState.getInt(STATE_MODE);
//////            setActionBarTitle(stateTitle);
//////            list.addAll(stateList);
//////            setMode(stateMode);
//////        }
////
////
////    }
////
////
////    @Override
////    public void onClick(View view) {
////        switch (view.getId()){
////            case R.id.lv_movies:
////                Intent i = new Intent(MovieList.this, ListMovieAdapter.class);
////                startActivity(i);
////                break;
////        }
////    }
////
////    private void showSelectedPresident(Movie movie){
////        Toast.makeText(this, "Kamu memilih "+ movie.getOriginalTitle(), Toast.LENGTH_SHORT).show();
////    }
////
////
////    private void showRecyclerList() {
////        rvCategory.setLayoutManager(new LinearLayoutManager(this));
////        ListMovieAdapter listMovieAdapter = new ListMovieAdapter(this, list);
////        listMovieAdapter.setListMovie(list);
////        rvCategory.setAdapter(listMovieAdapter);
////
////        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){
////            @Override
////            public void onItemClicked(RecyclerView recyclerView, int position, View v){
////                showSelectedPresident(list.get(position));
////            }
////        });
////    }
////
////
////
////    private void setActionBarTitle(String title){
////        getSupportActionBar().setTitle(title);
////    }
////
////
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        getMenuInflater().inflate(R.menu.menu_main, menu);
////        return super.onCreateOptionsMenu(menu);
////    }
////
////
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        setMode(item.getItemId());
////        return super.onOptionsItemSelected(item);
////
////    }
////
////    public void setMode(int selectedMode){
////        String title = null;
////        switch (selectedMode) {
////            case R.id.action_change_setting:
////                title = "Mode List";
////                showRecyclerList();
////                break;
////
////        }
////        mode = selectedMode;
////        setActionBarTitle(title);
////    }
////
////
////    @Override
////    protected void onSaveInstanceState(Bundle outState){
////        super.onSaveInstanceState(outState);
////        outState.putString(STATE_TITLE, getSupportActionBar().getTitle().toString());
////        outState.putParcelableArrayList(STATE_LIST, list);
////        outState.putInt(STATE_MODE, mode);
////    }
////}
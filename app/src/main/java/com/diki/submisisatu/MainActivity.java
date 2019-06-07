package com.diki.submisisatu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RecyclerView rvCategory;
    private ArrayList<President> list = new ArrayList<>();
    private String title = "Move list";
    final String STATE_TITLE = "state_string";
    final String STATE_LIST = "state_list";
    final String STATE_MODE = "state_mode";
    int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

//        list.addAll(PresidentData.getListData());
//        showRecyclerList();

        //untuk menampilkan data ketika ada orientasi layar

        list = new ArrayList<>();
        if (savedInstanceState == null){
            setActionBarTitle("Mode List");
            list.addAll(PresidentData.getListData());
            showRecyclerList();
            mode = R.id.action_list;
        }else
        {
            String stateTitle = savedInstanceState.getString(STATE_TITLE);
                ArrayList<President> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
                int stateMode = savedInstanceState.getInt(STATE_MODE);
                setActionBarTitle(stateTitle);
                list.addAll(stateList);
//                setMode(stateMode);
        }
    }

//    @Override
//    private void onSavedInstanceState(Bundle outState){
//        super.onSavedInstanceState(outState);
//        outState.putString(STATE_TITLE, getSupportActionBar().getTitle().toString());
//        outState.putParcelableArrayList(STATE_LIST, list);
//        outState.putInt(STATE_MODE, mode);
//    }


    private void showRecyclerList() {
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        listPresidentAdapter ListPresidentAdapter = new listPresidentAdapter(this);
        ListPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(ListPresidentAdapter);
    }

    private void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_list:
                setActionBarTitle("Mode List");
                showRecyclerList();
                break;
            case R.id.action_gird:
                setActionBarTitle("Mode Grid");
                showRecyclerGrid();
                break;
            case R.id.action_cardview:
                setActionBarTitle("Mode CardView");
                showReciclerCardView();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showRecyclerGrid(){
        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        GridPresidentAdapter gridPresidentAdapter = new GridPresidentAdapter(this);
        gridPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(gridPresidentAdapter);
    }

    private void  showReciclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardViewPresidentAdapter cardViewPresidentAdapter = new CardViewPresidentAdapter(this);
        cardViewPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(cardViewPresidentAdapter);
    }
}
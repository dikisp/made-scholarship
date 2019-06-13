package com.diki.submisisatu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rvCategory;
    private ArrayList<President> list ;
    private String title = "Move list";
    final String STATE_TITLE = "state_string";
    final String STATE_LIST = "state_list";
    final String STATE_MODE = "state_mode";
    int mode;
    ImageView btnIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        btnIntent = findViewById(R.id.card_view);

//        list.addAll(PresidentData.getListData());
//        showRecyclerList();

        //untuk menampilkan data ketika ada orientasi layar

        list = new ArrayList<>();
        if (savedInstanceState == null){
            setActionBarTitle("Mode CardView");
            list.addAll(PresidentData.getListData());
//            showRecyclerList();
            showReciclerCardView();
            mode = R.id.action_list;
        }else
        {
            String stateTitle = savedInstanceState.getString(STATE_TITLE);
                ArrayList<President> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
                int stateMode = savedInstanceState.getInt(STATE_MODE);
                setActionBarTitle(stateTitle);
                list.addAll(stateList);
                setMode(stateMode);
        }
    }

    //metode intent
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_item_foto:
                Intent i = new Intent(MainActivity.this, DetailHokage.class);
                startActivity(i);
                break;
        }
    }

    //metode untuk menampilkan pemilihan
    private void showSelectedPresident(President president){

        Toast.makeText(this, "Kamu memilih "+ president.getName(), Toast.LENGTH_SHORT).show();
    }


    private void showRecyclerList() {
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        listPresidentAdapter ListPresidentAdapter = new listPresidentAdapter(this);
        ListPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(ListPresidentAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v){
                showSelectedPresident(list.get(position));
            }
        });
    }

    private void showRecyclerGrid(){
        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        GridPresidentAdapter gridPresidentAdapter = new GridPresidentAdapter(this);
        gridPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(gridPresidentAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v){
                showSelectedPresident(list.get(position));
            }
        });
    }

    private void  showReciclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardViewPresidentAdapter cardViewPresidentAdapter = new CardViewPresidentAdapter(this);
        cardViewPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(cardViewPresidentAdapter);
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
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);

    }

        public void setMode(int selectedMode){
        String title = null;
        switch (selectedMode) {
            case R.id.action_list:
                title = "Mode List";
                showRecyclerList();
                break;
            case R.id.action_gird:
                title = "Mode Grid";
                showRecyclerGrid();
                break;
            case R.id.action_cardview:
                title = "Mode CardView";
                showReciclerCardView();
                break;
        }
        mode = selectedMode;
        setActionBarTitle(title);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(STATE_TITLE, getSupportActionBar().getTitle().toString());
        outState.putParcelableArrayList(STATE_LIST, list);
        outState.putInt(STATE_MODE, mode);
    }
}
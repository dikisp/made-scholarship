package com.diki.submisisatu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RecyclerView rvCategory;
    private ArrayList<President> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list.addAll(PresidentData.getListData());
        showRecyclerList();
    }

    private void  showRecyclerList(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        listPresidentAdapter ListPresidentAdapter = new listPresidentAdapter(this);
        ListPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(ListPresidentAdapter);
    }
}

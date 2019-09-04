package com.diki.myprofile.list_friends;
// 17 MEI
// 10116352
// DIKI SUPRIADI
// IF-8
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.diki.myprofile.Model.AppDatabase;
import com.diki.myprofile.Model.Friend;
import com.diki.myprofile.Model.FriendData;
import com.diki.myprofile.R;
import com.diki.myprofile.main.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;


public class ListFriendsFragment extends Fragment implements ListFriendsContract.View {


    Button addFriend;
    RecyclerView rvView;

    private ArrayList<FriendData> daftarFriend;
    private AppDatabase db;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    public ListFriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_friends, container, false);

        daftarFriend = new ArrayList<>();

        db = Room.databaseBuilder(this.getContext().getApplicationContext(),
                AppDatabase.class, "db").allowMainThreadQueries().build();
        final FragmentActivity c = getActivity();

        rvView = view.findViewById(R.id.rv_friend);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(c);
        rvView.setLayoutManager(layoutManager);

        daftarFriend.addAll(Arrays.asList(db.dao().selectAllFriend()));

        new Thread(new Runnable() {
            @Override
            public void run() {
                final crud_adapter adapter = new crud_adapter(daftarFriend, c);
                c.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rvView.setAdapter(adapter);
                    }
                });
            }
        }).start();


        addFriend = (Button) view.findViewById(R.id.btn_add_friend);

        addFriend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent addFriendIntent = new Intent(getActivity(), AddFriend.class);

                startActivity(addFriendIntent);
            }
        });

        return view;

    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showListFriends() {

    }

    @Override
    public void showFriendDetailUI(String friend_id) {

    }

    @Override
    public void setPresenter(ListFriendsContract.Presenter presenter) {

    }
}

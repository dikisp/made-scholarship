package com.diki.myprofile.friend_detail;
// 17 MEI
// 10116352
// DIKI SUPRIADI
// IF-8
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.diki.myprofile.R;

public class FriendDetailActivity extends AppCompatActivity  implements FriendDetailContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void setPresenter(FriendDetailContract.Presenter presenter) {

    }

    @Override
    public void showFriend() {

    }
}

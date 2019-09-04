package com.diki.myprofile.list_friends;
// 17 MEI
// 10116352
// DIKI SUPRIADI
// IF-8
import com.diki.myprofile.BasePresenter;
import com.diki.myprofile.BaseView;
import com.diki.myprofile.Model.Friend;

public class ListFriendsContract {
    public interface View extends BaseView<Presenter>{
        void setLoadingIndicator(boolean active);

        void showListFriends();

        void showFriendDetailUI(String friend_id);
    }

    public interface Presenter extends BasePresenter {
        void loadListFriends();

        void addNewFriend();

        void openDetailFriendDetail(Friend requestedFriend);
    }
}

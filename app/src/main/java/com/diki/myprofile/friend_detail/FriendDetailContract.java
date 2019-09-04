package com.diki.myprofile.friend_detail;
// 17 MEI
// 10116352
// DIKI SUPRIADI
// IF-8
import com.diki.myprofile.BasePresenter;
import com.diki.myprofile.BaseView;

public class FriendDetailContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showFriend();

    }

    interface Presenter extends BasePresenter {

        void loadFriendProfile();

        void editFriendProfile();

        void deleteFriendProfile();
    }
}

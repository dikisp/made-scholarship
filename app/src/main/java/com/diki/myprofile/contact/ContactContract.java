package com.diki.myprofile.contact;

import com.diki.myprofile.BasePresenter;
import com.diki.myprofile.BaseView;
import com.diki.myprofile.Model.Friend;

import java.util.List;

public interface ContactContract {
    interface View extends BaseView<Presenter>{
        void setLoadingIndicator(boolean active);

        void showContact(List<Friend> friends);
    }

    interface Presenter extends BasePresenter {
        void loadContact(Boolean forceUpdate);
    }
}

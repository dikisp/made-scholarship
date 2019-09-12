package com.diki.submisisatu.Favorite;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.diki.submisisatu.Database.AppDatabase;
import com.diki.submisisatu.Model.DataFavoriteMovie;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {
    private static final String TAG = FavoriteViewModel.class.getSimpleName();

    private LiveData<List<DataFavoriteMovie>> favorite;

    public FavoriteViewModel(Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving the tasks from the DataBase");
        favorite = database.dao().loadAllFavorite();
    }

    public LiveData<List<DataFavoriteMovie>> getFavorite() {
        return favorite;
    }
}

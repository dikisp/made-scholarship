package com.diki.submisisatu.Fragment.Favorite;


import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.diki.submisisatu.Adapter.FavoriteAdapter;
import com.diki.submisisatu.Database.FavoriteHelper;
import com.diki.submisisatu.DetailMovieActivity;
import com.diki.submisisatu.DetailTVActivity;
import com.diki.submisisatu.Model.FavoriteMovie;
import com.diki.submisisatu.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.diki.submisisatu.Database.DatabaseContract.CONTENT_URI;
import static com.diki.submisisatu.repo.FavoriteRepo.getTvFavoriteList;
import static java.security.AccessController.getContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTvFavorite extends Fragment {


    private static final String EXTRA_STATE = "EXTRA_STATE";
    @BindView(R.id.null_layout)
    LinearLayout nullLayout;
    @BindView(R.id.lv_favorite)
    RecyclerView rvTv;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    private LoadFavoriteCallback callback;
    private FavoriteAdapter adapter;
    private FavoriteHelper helper;

    public FragmentTvFavorite() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.list_favorite, container, false);
//        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
        if (savedInstanceState == null) {
            new LoadFavoriteAsync(getContext(), callback).execute();
        } else {
            ArrayList<FavoriteMovie> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null) {
                adapter.setListFavorite(list);
            }
        }
    }

    private void setupView(View view) {
        callback = new LoadFavoriteCallback() {
            @Override
            public void preExecute() {

            }

            @Override
            public void postExecute(Cursor cursor) {
//                ArrayList<FavoriteMovie> list = getTvFavoriteList(cursor);
//                if (list.size() > 0) {
//                    adapter.setListFavorite(list);
//                    nullLayout.setVisibility(View.GONE);
//                    rvTv.setVisibility(View.VISIBLE);
//                } else {
//                    adapter.setListFavorite(new ArrayList<FavoriteMovie>());
//                    nullLayout.setVisibility(View.VISIBLE);
//                    rvTv.setVisibility(View.GONE);
//                }
            }
        };
        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        DataObserver myObserver = new DataObserver(handler, getContext(), callback);
        Objects.requireNonNull(getActivity()).getContentResolver().registerContentObserver(CONTENT_URI, true, myObserver);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvTv = view.findViewById(R.id.lv_favorite);
        rvTv.setLayoutManager(layoutManager);
        rvTv.setHasFixedSize(true);


//        rvTv.setLayoutManager(new RecyclerView.LayoutManager() {
//            @Override
//            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
//                return null;
//            }
//        });
//        rvTv.setHasFixedSize(true);

        helper = FavoriteHelper.getInstance(getContext());

        helper.open();


        adapter = new FavoriteAdapter(getActivity(), new FavoriteAdapter.PostListListener() {
            @Override
            public void onPostClick(int id) {
                Intent intent = new Intent(FragmentTvFavorite.this.getContext(), DetailTVActivity.class);
                intent.putExtra(DetailTVActivity.TID, id);
                FragmentTvFavorite.this.startActivity(intent);
            }
        });


        rvTv.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_STATE, adapter.getListFavorite());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        helper.close();
    }

    interface LoadFavoriteCallback {
        void preExecute();

        void postExecute(Cursor cursor);
    }

    private static class LoadFavoriteAsync extends AsyncTask<Void, Void, Cursor> {

        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadFavoriteCallback> weakCallback;

        private LoadFavoriteAsync(Context context, LoadFavoriteCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            Context context = weakContext.get();
            return context.getContentResolver().query(CONTENT_URI, null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            weakCallback.get().postExecute(cursor);
        }
    }

    static class DataObserver extends ContentObserver {

        final Context context;
        final LoadFavoriteCallback callback;

        DataObserver(Handler handler, Context context, LoadFavoriteCallback callback) {
            super(handler);
            this.context = context;
            this.callback = callback;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new LoadFavoriteAsync(context, callback).execute();

        }
    }

}

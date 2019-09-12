package com.diki.submisisatu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.diki.submisisatu.BuildConfig;
import com.diki.submisisatu.DetailMovieActivity;
import com.diki.submisisatu.Item.CustomOnItemClickListener;
import com.diki.submisisatu.Model.Movie;
import com.diki.submisisatu.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.diki.submisisatu.Database.DatabaseContract.CONTENT_URI;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private static final String TAG = "RecyclerFavouriteAdapte";
    private final static String IMAGE_BASE_URL = BuildConfig.IMAGE_BASE_URL;

    private Cursor listFavourite;
    private Context context;

    public FavoriteAdapter(Cursor items, Context context) {
        this.context = context;
        setListFavourite(items);
    }

    public void setListFavourite(Cursor listFavourite) {
        this.listFavourite = listFavourite;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: called");

        final Movie detailMovie = getItem(position);

        String poster_url = IMAGE_BASE_URL + "w185" + detailMovie.getPosterPath();

        Glide.with(context)
                .load(poster_url)
                .into(holder.imgMoviePoster);

        holder.tvMovieTitle.setText(detailMovie.getOriginalTitle());
        holder.tvMovieDescription.setText(detailMovie.getOverview());
        holder.tvMovieDate.setText(dateFormat(detailMovie.getReleaseDate()));
        holder.parentMovieCard.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                String local = "1";
                Intent intent = new Intent(context, DetailMovieActivity.class);

                Uri uri = Uri.parse(CONTENT_URI + "/" + detailMovie.getId());
                intent.putExtra(DetailMovieActivity.MOVIE_ID, detailMovie.getId());
                intent.putExtra(DetailMovieActivity.LOCAL_STATUS, local);
                intent.setData(uri);
                context.startActivity(intent);
            }
        }));

    }

    @Override
    public int getItemCount() {
        if (listFavourite == null) return 0;
        return listFavourite.getCount();
    }

    private Movie getItem(int position) {
        if (!listFavourite.moveToPosition(position)) {
            throw new IllegalStateException("Position Invalid");
        }
        return new Movie();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rv_listMovie)
        RecyclerView parentMovieCard;

        @BindView(R.id.img_item_foto)
        ImageView imgMoviePoster;

        @BindView(R.id.tvTitle)
        TextView tvMovieTitle;

        @BindView(R.id.tvOverview)
        TextView tvMovieDescription;

        @BindView(R.id.tvRelease)
        TextView tvMovieDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private String dateFormat(String oldDate) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(oldDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat newFormat = new SimpleDateFormat("EEEE, MMM dd, yyyy");
        String finalDate = newFormat.format(myDate);

        return finalDate;

    }

}

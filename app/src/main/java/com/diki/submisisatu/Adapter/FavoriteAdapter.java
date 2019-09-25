package com.diki.submisisatu.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.diki.submisisatu.Model.FavoriteMovie;
import com.diki.submisisatu.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.diki.submisisatu.BuildConfig.POSTER_PATH;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.Viewholder> {
    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final PostListListener mListListener;
        @BindView(R.id.img_item_fotoFav)
        ImageView listMoviePoster;
        @BindView(R.id.realease)
        TextView listMovieReleaseDate;
        @BindView(R.id.tvTitle)
        TextView listMovieTitle;
        @BindView(R.id.tv_item_remarksFav)
        TextView listMovieRating;
        @BindView(R.id.rv_listMovieFav)
        RecyclerView btnMovie;

        Viewholder(View itemView, PostListListener postListListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            this.mListListener = postListListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            FavoriteMovie favoriteMovie = getItem(getAdapterPosition());
            this.mListListener.onPostClick(favoriteMovie.getmId());
            notifyDataSetChanged();
        }
    }
    private final ArrayList<FavoriteMovie> favorites = new ArrayList<>();
    private final Activity activity;
    private final PostListListener postItemListener;

    public FavoriteAdapter(Activity activity, PostListListener postItemListener) {
        this.activity = activity;
        this.postItemListener = postItemListener;
    }



    public ArrayList<FavoriteMovie> getListFavorite() {
        return favorites;
    }

    public void setListFavorite(ArrayList<FavoriteMovie> listFavorite) {

        if (listFavorite.size() > 0) {
            this.favorites.clear();
        }
        this.favorites.addAll(listFavorite);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.list_favorite, parent, false);

        return new Viewholder(postView, this.postItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        FavoriteMovie favorite = favorites.get(position);
        if (favorite.getOriginalTitle().length() > 15) {
            holder.listMovieTitle.setText((favorite.getOriginalTitle().substring(0, 15) + "..."));
        } else {
            holder.listMovieTitle.setText(favorite.getOriginalTitle());
        }
        holder.listMovieRating.setText(favorite.getRating());
        holder.listMovieReleaseDate.setText(favorite.getReleaseDate().split("-")[0]);
        Glide.with(activity)
                .load(POSTER_PATH + favorites.get(position).getPosterPath())
                .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                .into(holder.listMoviePoster);
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    private FavoriteMovie getItem(int adapterPosition) {
        return favorites.get(adapterPosition);
    }

    public interface PostListListener {
        void onPostClick(int mId);
    }



}

package com.diki.submisisatu;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class listMovieAdapter extends RecyclerView.Adapter<listMovieAdapter.CategoryViewHolder>

{
    public listMovieAdapter(Context context) {
        this.context = context;
    }

    private Context context;

    public ArrayList<Movie> getListMovie() {
        return listMovie;
    }

    public void setListMovie(ArrayList<Movie> listMovie)  {
        this.listMovie = listMovie;
    }

    private ArrayList<Movie> listMovie;

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View  itemRow =
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_movie,
                        viewGroup, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, final int position) {

        categoryViewHolder.tvName.setText(getListMovie().get(position).getTitle());
        categoryViewHolder.tvRemarks.setText(getListMovie().get(position).getDeskripsi());

        Glide.with(context)
                .load(getListMovie().get(position).getPoster())
                .apply(new RequestOptions().override(55,55))
                .into(categoryViewHolder.imgPhoto);

        categoryViewHolder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailMovieActivity.class);
                intent.putExtra(Utils.parcel,listMovie.get(position));
                context.startActivity(intent);
            }
        });

        categoryViewHolder.imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailMovieActivity.class);
                intent.putExtra(Utils.parcel,listMovie.get(position));
                context.startActivity(intent);
            }
        });

        categoryViewHolder.tvRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailMovieActivity.class);
                intent.putExtra(Utils.parcel,listMovie.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvRemarks;
        ImageView imgPhoto;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvRemarks = itemView.findViewById(R.id.tv_item_remarks);
            imgPhoto = itemView.findViewById(R.id.img_item_foto);
        }
    }
}

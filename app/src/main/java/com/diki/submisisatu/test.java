package com.diki.submisisatu;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;


public class test extends RecyclerView.Adapter<test.CardViewViewHolder> {
    private Context context;
    private ArrayList<Movie> listPresident;
    private ArrayList<Movie> getListPresident;

    private ArrayList<Movie> getListPresident() {
        return listPresident;
    }

    public void setListPresident(ArrayList<Movie> listPresident) {
        this.listPresident = listPresident;
    }

    public test(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_movie, viewGroup, false);
        return new CardViewViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder cardViewViewHolder, int i) {
        final Movie p = getListPresident().get(i);

        Glide.with(context)
                .load(p.getPoster())
                .apply(new RequestOptions().override(350, 550))
                .into(cardViewViewHolder.imgPhoto);

        cardViewViewHolder.tvName.setText(p.getTitle());
        cardViewViewHolder.tvRemarks.setText(p.getDeskripsi());


        cardViewViewHolder.imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailMovie.class);
                intent.putExtra(Utils.parcel,p);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListPresident().size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvRemarks;


        CardViewViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_foto);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvRemarks = itemView.findViewById(R.id.tv_item_remarks);
        }
    }
}
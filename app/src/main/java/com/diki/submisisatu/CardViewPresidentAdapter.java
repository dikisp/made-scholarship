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


public class CardViewPresidentAdapter extends RecyclerView.Adapter<CardViewPresidentAdapter.CardViewViewHolder> {
    private Context context;
    private ArrayList<President> listPresident;
    private ArrayList<President> getListPresident;

    private ArrayList<President> getListPresident() {
        return listPresident;
    }

    public void setListPresident(ArrayList<President> listPresident) {
        this.listPresident = listPresident;
    }

    public CardViewPresidentAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card, viewGroup, false);
        return new CardViewViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder cardViewViewHolder, int i) {
        final President p = getListPresident().get(i);

        Glide.with(context)
                .load(p.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(cardViewViewHolder.imgPhoto);

        cardViewViewHolder.tvName.setText(p.getName());
        cardViewViewHolder.tvRemarks.setText(p.getRemarks());

        cardViewViewHolder.btnFavorite.setOnClickListener(new
                CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallBack() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Favorite " + getListPresident().get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        }));

        cardViewViewHolder.btnShare.setOnClickListener(new
                CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallBack() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Share " + getListPresident().get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        }));

        cardViewViewHolder.imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailHokage.class);
//                intent.putExtra(Utils.imagePhoto,p.getPhoto());
//                intent.putExtra(Utils.name,p.getName());
//                intent.putExtra(Utils.position,p.getRemarks());
//                intent.putExtra(Utils.lahir,p.getLahir());
//                intent.putExtra(Utils.wafat,p.getWafat());
//                intent.putExtra(Utils.tinggi,p.getTinggi());
//
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
        CardView cardView;
        Button btnFavorite, btnShare;

        CardViewViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_foto);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvRemarks = itemView.findViewById(R.id.tv_item_remarks);
            btnFavorite = itemView.findViewById(R.id.btn_set_favorite);
            btnShare = itemView.findViewById(R.id.btn_set_share);
            cardView = itemView.findViewById(R.id.card_view);

//            imgPhoto.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, DetailHokage.class);
//                    context.startActivity(intent);
//                }
//            });


        }
    }
}
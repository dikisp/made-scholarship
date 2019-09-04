package com.diki.myprofile.list_friends;
//  3 Agustus
// 10116352
// DIKI SUPRIADI
// IF-8
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.diki.myprofile.Model.AppDatabase;
import com.diki.myprofile.Model.Friend;
import com.diki.myprofile.Model.FriendData;
import com.diki.myprofile.R;

import java.util.ArrayList;

public class crud_adapter extends RecyclerView.Adapter<crud_adapter.ViewHolder>{

    private ArrayList<FriendData> daftarFriend;
    private Context context;
    private AppDatabase db;

    public crud_adapter(ArrayList<FriendData> friends, Context ctx) {
        daftarFriend = friends;
        context = ctx;

        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "db").allowMainThreadQueries().build();

    }

    class ViewHolder extends RecyclerView.ViewHolder {


        TextView tvTitle,tvAddres,tvPhone;
        LinearLayout cvMain;
        ImageView imageView;

        ViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tv_friend_nama);
            tvAddres = v.findViewById(R.id.tv_item_alamat);
            tvPhone = v.findViewById(R.id.tv_item_phone);
            cvMain = v.findViewById(R.id.cv_friend);
            imageView = v.findViewById(R.id.moreTool);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final String name = daftarFriend.get(position).getNama();
        holder.tvTitle.setText(name);

        final String address = daftarFriend.get(position).getAlamat();
        holder.tvAddres.setText(address);

        final String phone = daftarFriend.get(position).getTelepon();
        holder.tvPhone.setText(phone);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.view_dialog);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button editButton = dialog.findViewById(R.id.bt_edit_data);
                Button delButton = dialog.findViewById(R.id.bt_delete_data);

                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                onEditBarang(position);
                            }
                        }
                );

                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                onDeteleBarang(position);
                            }
                        }
                );
            }
        });

        holder.tvTitle.setText(name);
    }

    private void onDeteleBarang(int position) {
        db.dao().deleteFriend(daftarFriend.get(position));
        daftarFriend.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, daftarFriend.size());
    }

    private void onEditBarang(int position) {
        context.startActivity(AddFriend.getActIntent((Activity) context).putExtra("data", daftarFriend.get(position)));
    }


    @Override
    public int getItemCount() {

        return daftarFriend.size();
    }
}

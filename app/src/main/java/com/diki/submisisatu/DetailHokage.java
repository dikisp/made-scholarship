package com.diki.submisisatu;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailHokage extends AppCompatActivity {
    ImageView ImgView;
    TextView Nama, position, Deskripsi, lahir, wafat, tinggi;
    CircleImageView circleImageView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hokage);


//        ImgView.findViewById(R.id.img_foto);
        circleImageView = findViewById(R.id.img_foto);
        Nama = findViewById(R.id.tv_name);
        position = findViewById(R.id.tv_position);
        Deskripsi = findViewById(R.id.deskripsi);
        lahir = findViewById(R.id.val_lahir);
        wafat = findViewById(R.id.val_wafat);
        tinggi = findViewById(R.id.val_tinggi);

        Intent intent = getIntent();
        President data = intent.getParcelableExtra(Utils.parcel);

        Glide.with(this).load(data.getPhoto())
                .into(circleImageView);

        Log.d("Naga", "nama: " + intent.getStringExtra(Utils.name) + " Position : " + intent.getStringExtra(Utils.position)
                + "URL GAMBAR : " + intent.getStringExtra(Utils.imagePhoto));

        Nama.setText("Nama : " + data.getName());
        position.setText("Jabatan : " + data.getRemarks());
        Deskripsi.setText("Deskripsi : " + data.getDeskripsi());
        lahir.setText("Lahir : " + data.getLahir());
        wafat.setText("Wafat : " + data.getWafat());
        tinggi.setText("Tinggi : " + data.getTinggi());


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

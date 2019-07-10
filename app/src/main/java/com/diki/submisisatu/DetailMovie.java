package com.diki.submisisatu;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailMovie extends AppCompatActivity {
    TextView Nama, position, Deskripsi, lahir, wafat, tinggi;
    ImageView imageView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);


        imageView = findViewById(R.id.img_foto);
        Nama = findViewById(R.id.tv_name);
        position = findViewById(R.id.tv_position);
        Deskripsi = findViewById(R.id.deskripsi);
        lahir = findViewById(R.id.val_lahir);
        wafat = findViewById(R.id.val_wafat);
        tinggi = findViewById(R.id.val_tinggi);

        Intent intent = getIntent();
        Movie data = intent.getParcelableExtra(Utils.parcel);

        Glide.with(this).load(data.getPoster())
                .into(imageView);

        Log.d("Naga", "nama: " + intent.getStringExtra(Utils.name) + " Position : " + intent.getStringExtra(Utils.position)
                + "URL GAMBAR : " + intent.getStringExtra(Utils.imagePhoto));

        Nama.setText("Judul : " + data.getTitle());
        position.setText("Genre : " + data.getGenre());
        Deskripsi.setText("Deskripsi : " + data.getDeskripsi());
        lahir.setText("Release : " + data.getRealese());
        wafat.setText("Produksi : " + data.getPruduksi());
        tinggi.setText("Rating : " + data.getRating());


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

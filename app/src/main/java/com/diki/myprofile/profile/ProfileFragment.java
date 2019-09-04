package com.diki.myprofile.profile;
// 17 MEI
// 10116352
// DIKI SUPRIADI
// IF-8
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.diki.myprofile.Model.Profile;
import com.diki.myprofile.R;

import java.util.List;

public class ProfileFragment extends Fragment implements ProfileContract.View {

    private ProfileContract.Presenter mPresenter;
    private ProgressBar progressBar;
    private TextView nim, nama, kelas, deskripsi;
    private Button button1;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ProfilePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.profil_fragment, container, false);

        nim = view.findViewById(R.id.tvprofile_nim);
        nama = view.findViewById(R.id.tvprofile_nama);
        kelas = view.findViewById(R.id.tvprofile_kelas);
        deskripsi = view.findViewById(R.id.tvprofile_deskripsi);
        button1 = view.findViewById(R.id.button);

        progressBar = view.findViewById(R.id.pb_profile);


        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String phoneNumber = "082316901623";
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));

                startActivity(dialPhoneIntent);
            }
        });



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProfile(List<Profile> profile) {
        nim.setText(profile.get(0).getNim());
        nama.setText(profile.get(0).getNama());
        kelas.setText(profile.get(0).getKelas());
        deskripsi.setText(profile.get(0).getDeskripsi());
    }
}

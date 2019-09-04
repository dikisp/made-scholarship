package com.diki.myprofile.contact;
// 17 MEI
// 10116352
// DIKI SUPRIADI
// IF-8

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.diki.myprofile.Model.Friend;
import com.diki.myprofile.R;

import java.util.List;

public class ContactFragment extends Fragment implements ContactContract.View {
    private ProgressBar progressBar;
    private ContactContract.Presenter mPresenter;
    private TextView telepon,email,instagram,twitter,facebook;
    private Button button1,button2,button3,button4,button5,button6;
    private ImageView imageView;


    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ContactPresenter(this);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_contact, container, false);

        progressBar = view.findViewById(R.id.pb_contact);

        button1 = view.findViewById(R.id.telp);
        button2 = view.findViewById(R.id.email);
        button3 = view.findViewById(R.id.inst);
        button4 = view.findViewById(R.id.twitt);
        button5 = view.findViewById(R.id.fb);
        button6 = view.findViewById(R.id.git);
        imageView = view.findViewById(R.id.maps);

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


        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Uri uri = Uri.parse("http://instagram.com/suara_hati69");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/suara_hati69")));
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","dikisupriadi021@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Uri uri = Uri.parse("http://m.twitter.com/suara_hati69");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.twitter.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://m.twitter.com/suara_hati69")));
                }
            }
        });


        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Uri uri = Uri.parse("http://m.facebook.com/diki.supriadi");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.facebook.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://m.facebook.com/diki.supriadi")));
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Uri uri = Uri.parse("http://m.facebook.com/diki.supriadi");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.facebook.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://github.com/dikisp")));
                }
            }
        });


        imageView.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/place/Bangbayang+Squad/@-6.8810619,107.6174528,18z/data=!3m1!4b1!4m5!3m4!1s0x2e68e6fc2ddeed57:0xac57bbb9f323ea1!8m2!3d-6.8810619!4d107.6180215");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                startActivity(mapIntent);
            }

        });

        return view;
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
    public void showContact(List<Friend> friends) {
        telepon.setText(friends.get(0).getTelepon());
        email.setText(friends.get(0).getEmail());
        instagram.setText(friends.get(0).getInstagram());
        twitter.setText(friends.get(0).getTwitter());
        facebook.setText(friends.get(0).getFacebook());
    }

    @Override
    public void setPresenter(ContactContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }
}

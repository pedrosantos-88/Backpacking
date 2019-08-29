package com.android.firebaseapp.backpacking;

import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.firebaseapp.backpacking.Model.Cities;
import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AboutCityActivity extends AppCompatActivity {

    private TextView mTextViewAbout;
    private ImageView mImageViewPhotos;
    private Bundle extras;
    private String nameCity;
    private String aboutCity;
    private int photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_city);
        extras = getIntent().getExtras();

        nameCity = extras.getString("name");

        Toolbar myToolbar = findViewById(R.id.my_toolbar);

        myToolbar.setTitle(nameCity);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            myToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        }

        mImageViewPhotos = findViewById(R.id.ImageViewPhoto);
        mTextViewAbout = findViewById(R.id.TextViewAbout);


        if (extras != null) {
            aboutCity = extras.getString("about");
            photo = extras.getInt("photo", 0);
            mTextViewAbout.setText(aboutCity);
            mTextViewAbout.setMovementMethod(new ScrollingMovementMethod());
            Glide.with(getApplicationContext()).load(photo).into(mImageViewPhotos);

        }


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}



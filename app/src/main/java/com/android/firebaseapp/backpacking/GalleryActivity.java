package com.android.firebaseapp.backpacking;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.firebaseapp.backpacking.Fragments.PhotosFragment;
import com.bumptech.glide.Glide;

public class GalleryActivity extends AppCompatActivity {

    private ImageView mGalleryImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);


        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            myToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        }

        mGalleryImageView = findViewById(R.id.imageGallery);

        getIncomingIntent();

    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("image_url")) {
            String imageUrl = getIntent().getStringExtra("image_url");
            Glide.with(this).asBitmap().load(imageUrl).into(mGalleryImageView);
        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}

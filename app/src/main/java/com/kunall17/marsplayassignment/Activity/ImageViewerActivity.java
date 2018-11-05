package com.kunall17.marsplayassignment.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.kunall17.marsplayassignment.R;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;

public class ImageViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);

        ImageViewTouch fullImage = findViewById(R.id.fullImage);
        String url = getIntent().getStringExtra("BitmapURL");
        Glide.with(this).load(url).into(fullImage);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
}

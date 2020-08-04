package com.darji.darjifamilyapp.ui.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.darji.darjifamilyapp.Adapter.GalleryViewAdapter;
import com.darji.darjifamilyapp.R;

public class GalleryViewActivity extends AppCompatActivity {

    RecyclerView galleryview;
    GalleryViewAdapter adapter;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_view);

        setTitle(getIntent().getStringExtra("title"));

        galleryview = findViewById(R.id.gallery_view);
        galleryview.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(GalleryViewActivity.this,2);
        galleryview.setLayoutManager(gridLayoutManager);
        adapter = new GalleryViewAdapter(GalleryViewActivity.this);
        galleryview.setAdapter(adapter);

    }
}

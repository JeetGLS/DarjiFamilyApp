package com.darji.darjifamilyapp.ui.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.darji.darjifamilyapp.Adapter.GalleryViewAdapter;
import com.darji.darjifamilyapp.Model.ApiClient;
import com.darji.darjifamilyapp.Model.ApiInterface;
import com.darji.darjifamilyapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryViewActivity extends AppCompatActivity {

    RecyclerView galleryview;
    GalleryViewAdapter adapter;
    GridLayoutManager gridLayoutManager;
    String id;
    List<String> photosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_view);

        setTitle(getIntent().getStringExtra("title"));
        id = getIntent().getStringExtra("id");

        galleryview = findViewById(R.id.gallery_view);
        galleryview.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(GalleryViewActivity.this,2);
        galleryview.setLayoutManager(gridLayoutManager);


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<String>> call = apiService.getGalleryFiles(Integer.parseInt(id));
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                photosList = response.body();
                if(photosList!=null) {
                    adapter = new GalleryViewAdapter(GalleryViewActivity.this, photosList);
                    galleryview.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
            }
        });
    }
    @Override
    public Intent getParentActivityIntent() {
        Intent parentIntent= getIntent();
        finish();
        return parentIntent;
    }
}

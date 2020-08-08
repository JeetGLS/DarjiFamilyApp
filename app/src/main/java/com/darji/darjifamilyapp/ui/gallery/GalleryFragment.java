package com.darji.darjifamilyapp.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.darji.darjifamilyapp.Adapter.GalleryAdapter;
import com.darji.darjifamilyapp.Model.ApiClient;
import com.darji.darjifamilyapp.Model.ApiInterface;
import com.darji.darjifamilyapp.Model.GalleryData;
import com.darji.darjifamilyapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryFragment extends Fragment {

    RecyclerView gallery;
    GalleryAdapter adapter;
    List<GalleryData> galleryList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        gallery = root.findViewById(R.id.gallery);
        gallery.setHasFixedSize(true);
        gallery.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<GalleryData>> call = apiService.getGallery();

        call.enqueue(new Callback<List<GalleryData>>() {
            @Override
            public void onResponse(Call<List<GalleryData>> call, Response<List<GalleryData>> response) {
                galleryList = response.body();
                if(galleryList!=null) {
                    adapter = new GalleryAdapter(getContext(), galleryList);
                    gallery.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<GalleryData>> call, Throwable t) {

            }
        });

        return root;
    }
}
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
import com.darji.darjifamilyapp.R;

public class GalleryFragment extends Fragment {

    RecyclerView gallery;
    GalleryAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        gallery = root.findViewById(R.id.gallery);
        gallery.setHasFixedSize(true);
        gallery.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GalleryAdapter(getContext());
        gallery.setAdapter(adapter);

        return root;
    }
}
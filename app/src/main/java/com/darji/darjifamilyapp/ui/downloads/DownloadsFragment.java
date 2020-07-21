package com.darji.darjifamilyapp.ui.downloads;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.darji.darjifamilyapp.Adapter.DownloadsAdapter;
import com.darji.darjifamilyapp.R;

public class DownloadsFragment extends Fragment {
    private RecyclerView downloads;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_downloads, container, false);

        downloads = root.findViewById(R.id.downloads);
        downloads.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        downloads.setLayoutManager(layoutManager);

        final DownloadsAdapter adapter = new DownloadsAdapter(getContext());
        downloads.setAdapter(adapter);

        return root;
    }
}
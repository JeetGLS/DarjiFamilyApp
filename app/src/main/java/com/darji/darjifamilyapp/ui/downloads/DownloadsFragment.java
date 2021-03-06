package com.darji.darjifamilyapp.ui.downloads;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.darji.darjifamilyapp.Adapter.DownloadsAdapter;
import com.darji.darjifamilyapp.Model.ApiClient;
import com.darji.darjifamilyapp.Model.ApiInterface;
import com.darji.darjifamilyapp.Model.DownloadsData;
import com.darji.darjifamilyapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadsFragment extends Fragment {
    private RecyclerView downloads;
    private List<DownloadsData> downloadsList;
    private DownloadsAdapter downloadsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_downloads, container, false);

        downloads = root.findViewById(R.id.downloads);
        downloads.setHasFixedSize(true);
        downloads.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DownloadsData>> data = apiService.getDownloads();

        data.enqueue(new Callback<List<DownloadsData>>() {
            @Override
            public void onResponse(Call<List<DownloadsData>> call, Response<List<DownloadsData>> response) {
                downloadsList = response.body();
                if(downloadsList!=null) {
                    downloadsAdapter = new DownloadsAdapter(getActivity(), downloadsList);
                    downloads.setAdapter(downloadsAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<DownloadsData>> call, Throwable t) {
                //Toast.makeText(getContext(),"Failed to load Download List",Toast.LENGTH_LONG).show();
            }
        });

        return root;
    }
}
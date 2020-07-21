package com.darji.darjifamilyapp.ui.bulletin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.darji.darjifamilyapp.Adapter.BulletinAdapter;
import com.darji.darjifamilyapp.Model.ApiClient;
import com.darji.darjifamilyapp.Model.ApiInterface;
import com.darji.darjifamilyapp.Model.BulletinData;
import com.darji.darjifamilyapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BulletinFragment extends Fragment {
    private RecyclerView bulletins;
    private List<BulletinData> bulletinList;
    private BulletinAdapter bulletinAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_bulletin, container, false);

        bulletins = root.findViewById(R.id.bulletins);
        bulletins.setHasFixedSize(true);
        bulletins.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<BulletinData>> data = apiService.getBulletins();

        data.enqueue(new Callback<List<BulletinData>>() {
            @Override
            public void onResponse(Call<List<BulletinData>> call, Response<List<BulletinData>> response) {
                bulletinList = response.body();
                bulletinAdapter = new BulletinAdapter(getActivity(),bulletinList);
                bulletins.setAdapter(bulletinAdapter);
            }

            @Override
            public void onFailure(Call<List<BulletinData>> call, Throwable t) {

            }
        });

        return root;
    }
}
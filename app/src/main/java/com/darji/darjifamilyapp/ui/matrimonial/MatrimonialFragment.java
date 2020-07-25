package com.darji.darjifamilyapp.ui.matrimonial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.darji.darjifamilyapp.Adapter.MatrimonialAdapter;
import com.darji.darjifamilyapp.Model.ApiClient;
import com.darji.darjifamilyapp.Model.ApiInterface;
import com.darji.darjifamilyapp.Model.MatrimonialData;
import com.darji.darjifamilyapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatrimonialFragment extends Fragment {
    private RecyclerView matrimonial;
    private List<MatrimonialData> matrimonialList;
    private MatrimonialAdapter matrimonialAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_matrimonial, container, false);

        matrimonial = root.findViewById(R.id.matrimonial);
        matrimonial.setHasFixedSize(true);
        matrimonial.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<MatrimonialData>> data = apiService.getMatrimonial();

        data.enqueue(new Callback<List<MatrimonialData>>() {
            @Override
            public void onResponse(Call<List<MatrimonialData>> call, Response<List<MatrimonialData>> response) {
                matrimonialList = response.body();
                matrimonialAdapter = new MatrimonialAdapter(getActivity(),matrimonialList);
                matrimonial.setAdapter(matrimonialAdapter);
            }

            @Override
            public void onFailure(Call<List<MatrimonialData>> call, Throwable t) {

            }
        });

        return root;
    }
}
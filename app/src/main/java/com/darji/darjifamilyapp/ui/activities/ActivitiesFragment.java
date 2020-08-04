package com.darji.darjifamilyapp.ui.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.darji.darjifamilyapp.Adapter.ActivitiesAdapter;
import com.darji.darjifamilyapp.Adapter.AdsSwipeAdapter;
import com.darji.darjifamilyapp.Model.ActivitiesData;
import com.darji.darjifamilyapp.Model.ApiClient;
import com.darji.darjifamilyapp.Model.ApiInterface;
import com.darji.darjifamilyapp.Model.JobsData;
import com.darji.darjifamilyapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitiesFragment extends Fragment {
    private RecyclerView activities;
    private ActivitiesAdapter adapter;
    private List<ActivitiesData> activitiesList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_activities, container, false);

        activities = root.findViewById(R.id.activities);
        activities.setHasFixedSize(true);
        activities.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<ActivitiesData>> data = apiService.getActivities();

        data.enqueue(new Callback<List<ActivitiesData>>() {
            @Override
            public void onResponse(Call<List<ActivitiesData>> call, Response<List<ActivitiesData>> response) {
                activitiesList = response.body();
                if(activitiesList!=null) {
                    adapter = new ActivitiesAdapter(getContext(), activitiesList);
                    activities.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<ActivitiesData>> call, Throwable t) {
                //Toast.makeText(getContext(),"Failed to load Activities List",Toast.LENGTH_LONG).show();
            }
        });


        return root;
    }
}
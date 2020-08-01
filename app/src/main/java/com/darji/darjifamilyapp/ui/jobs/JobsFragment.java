package com.darji.darjifamilyapp.ui.jobs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.darji.darjifamilyapp.Adapter.BulletinAdapter;
import com.darji.darjifamilyapp.Adapter.JobsAdapter;
import com.darji.darjifamilyapp.Model.ApiClient;
import com.darji.darjifamilyapp.Model.ApiInterface;
import com.darji.darjifamilyapp.Model.BulletinData;
import com.darji.darjifamilyapp.Model.JobsData;
import com.darji.darjifamilyapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobsFragment extends Fragment {
    TextView jobCount;
    RecyclerView jobs;
    JobsAdapter jobsAdapter;
    List<JobsData> joblist;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_jobs, container, false);

        jobCount = root.findViewById(R.id.jobs_count);

        jobs = root.findViewById(R.id.jobs);
        jobs.setHasFixedSize(true);
        jobs.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<JobsData>> data = apiService.getJobs();

        data.enqueue(new Callback<List<JobsData>>() {
            @Override
            public void onResponse(Call<List<JobsData>> call, Response<List<JobsData>> response) {
                joblist = response.body();
                if(joblist!=null) {
                    jobsAdapter = new JobsAdapter(getActivity(), joblist);
                    jobs.setAdapter(jobsAdapter);
                    jobCount.setText("" + jobsAdapter.getItemCount());
                }
            }
            @Override
            public void onFailure(Call<List<JobsData>> call, Throwable t) {
                Toast.makeText(getContext(),"Failed to load Jobs List",Toast.LENGTH_LONG).show();
            }
        });

        return root;
    }
}
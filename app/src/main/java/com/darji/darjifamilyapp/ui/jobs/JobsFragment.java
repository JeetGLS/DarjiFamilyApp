package com.darji.darjifamilyapp.ui.jobs;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
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

    ConstraintLayout expandableView;
    Button arrowBtn;
    CardView cardView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_jobs, container, false);

        jobCount = root.findViewById(R.id.jobs_count);

        jobs = root.findViewById(R.id.jobs);
        jobs.setHasFixedSize(true);
        jobs.setLayoutManager(new LinearLayoutManager(getContext()));

        expandableView = root.findViewById(R.id.expandableView);
        arrowBtn = root.findViewById(R.id.arrowBtn);
        cardView = root.findViewById(R.id.cardView);

        arrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.VISIBLE);
                    arrowBtn.setBackgroundResource(R.drawable.ic_launcher_foreground);
                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.GONE);
                    arrowBtn.setBackgroundResource(R.drawable.ic_launcher_foreground);
                }
            }
        });


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<JobsData>> data = apiService.getJobs();

        data.enqueue(new Callback<List<JobsData>>() {
            @Override
            public void onResponse(Call<List<JobsData>> call, Response<List<JobsData>> response) {
                joblist = response.body();
                jobsAdapter = new JobsAdapter(getActivity(),joblist);
                jobs.setAdapter(jobsAdapter);
                jobCount.setText(""+jobsAdapter.getItemCount());
            }
            @Override
            public void onFailure(Call<List<JobsData>> call, Throwable t) {

            }
        });

        return root;
    }
}
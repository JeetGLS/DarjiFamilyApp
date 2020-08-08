package com.darji.darjifamilyapp.ui.jobs;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.darji.darjifamilyapp.Adapter.JobsAdapter;
import com.darji.darjifamilyapp.Model.ApiClient;
import com.darji.darjifamilyapp.Model.ApiInterface;
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
    View controller;
    ImageView arrow;
    CardView cardView;

    //Filtering
    Button fSearch;
    Spinner fSector;
    EditText fTitle,fLocation,fSkill,fSalFrom,fSalTo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_jobs, container, false);

        jobCount = root.findViewById(R.id.jobs_count);

        jobs = root.findViewById(R.id.jobs);
        jobs.setHasFixedSize(true);
        jobs.setLayoutManager(new LinearLayoutManager(getContext()));

        expandableView = root.findViewById(R.id.jobExpandableView);
        controller = root.findViewById(R.id.jobfilter);
        arrow = root.findViewById(R.id.jobSearchArrow);
        cardView = root.findViewById(R.id.jobCardView);

        controller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.mipmap.collapse);
                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.GONE);
                    arrow.setImageResource(R.mipmap.expand);
                }
            }
        });


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<JobsData>> data = apiService.getJobs();

        data.enqueue(new Callback<List<JobsData>>() {
            @Override
            public void onResponse(Call<List<JobsData>> call, Response<List<JobsData>> response) {
                joblist = response.body();
                if(joblist!=null) {
                    jobsAdapter = new JobsAdapter(getActivity(), joblist);
                    jobs.setAdapter(jobsAdapter);
                    setJobCount();
                }
            }
            @Override
            public void onFailure(Call<List<JobsData>> call, Throwable t) {
                //Toast.makeText(getContext(),"Failed to load Jobs List",Toast.LENGTH_LONG).show();
            }
        });


        fTitle = root.findViewById(R.id.jobsearch_title);
        fLocation = root.findViewById(R.id.jobsearch_location);
        fSkill = root.findViewById(R.id.jobsearch_skills);
        fSector = root.findViewById(R.id.jobsearch_sector);
        fSalFrom = root.findViewById(R.id.jobsearch_salarayfrom);
        fSalTo = root.findViewById(R.id.jobsearch_salarayto);
        fSearch = root.findViewById(R.id.job_search_button);
        fSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(jobsAdapter!=null)
                {
                    jobsAdapter.setFilters(
                            fTitle.getText().toString(),
                            fLocation.getText().toString(),
                            fSkill.getText().toString(),
                            fSector.getSelectedItem().toString(),
                            fSalFrom.getText().toString(),
                            fSalTo.getText().toString());
                    jobsAdapter.notifyDataSetChanged();
                    setJobCount();
                }
            }
        });


        return root;
    }

    private void setJobCount()
    {
        jobCount.setText("" + jobsAdapter.getItemCount());
    }
}
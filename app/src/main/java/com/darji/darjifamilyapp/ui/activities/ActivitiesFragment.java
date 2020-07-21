package com.darji.darjifamilyapp.ui.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.darji.darjifamilyapp.Adapter.ActivitiesAdapter;
import com.darji.darjifamilyapp.Adapter.AdsSwipeAdapter;
import com.darji.darjifamilyapp.R;

public class ActivitiesFragment extends Fragment {
    private RecyclerView activities;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_activities, container, false);

        activities = root.findViewById(R.id.activities);
        activities.setHasFixedSize(true);
        RecyclerView.LayoutManager managerOfLayouts = new LinearLayoutManager(getContext());
        activities.setLayoutManager(managerOfLayouts);

        ActivitiesAdapter adapter = new ActivitiesAdapter(getContext());
        activities.setAdapter(adapter);

        return root;
    }
}
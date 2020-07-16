package com.darji.darjifamilyapp.ui.jobs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.darji.darjifamilyapp.R;

public class JobsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_jobs, container, false);

        //final TextView textView = root.findViewById(R.id.t_jobs);
        //textView.setText("Hello World");

        return root;
    }
}
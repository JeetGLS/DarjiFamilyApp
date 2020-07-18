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
import com.darji.darjifamilyapp.Model.BulletinData;
import com.darji.darjifamilyapp.R;

import java.util.ArrayList;
import java.util.List;

public class BulletinFragment extends Fragment {
    private RecyclerView bulletins;
    private List<BulletinData> list = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_bulletin, container, false);
        bulletins = root.findViewById(R.id.bulletins);
        bulletins.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        bulletins.setLayoutManager(layoutManager);

        final BulletinAdapter bulletinAdapter = new BulletinAdapter(getContext());
        bulletins.setAdapter(bulletinAdapter);

        return root;
    }
}
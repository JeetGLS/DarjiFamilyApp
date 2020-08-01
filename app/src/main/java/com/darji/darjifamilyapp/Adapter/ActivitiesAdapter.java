package com.darji.darjifamilyapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.darji.darjifamilyapp.Model.ActivitiesData;
import com.darji.darjifamilyapp.R;

import java.util.List;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.ActivitiesHolder> {
    private Context context;
    List<ActivitiesData> list;

    public  ActivitiesAdapter(Context context,List<ActivitiesData> list)
    {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ActivitiesHolder onCreateViewHolder(@NonNull ViewGroup container, int viewType) {
        View root= LayoutInflater.from(container.getContext()).inflate(R.layout.activities_list,container,false);
        ActivitiesAdapter.ActivitiesHolder holder = new ActivitiesAdapter.ActivitiesHolder(root);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ActivitiesHolder holder, int position) {
        final ActivitiesData data = list.get(position);
        holder.title.setText(data.getActivityTitle());
        holder.desc.setText(data.getActivityDescription());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class ActivitiesHolder extends RecyclerView.ViewHolder
    {
        TextView title,desc;
        public ActivitiesHolder(@NonNull View root) {
            super(root);
            title = root.findViewById(R.id.activity_title);
            desc = root.findViewById(R.id.activity_desc);
        }
    }
}

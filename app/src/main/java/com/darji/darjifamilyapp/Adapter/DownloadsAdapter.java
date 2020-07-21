package com.darji.darjifamilyapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.darji.darjifamilyapp.Model.DownloadsData;
import com.darji.darjifamilyapp.R;

import java.util.List;


public class DownloadsAdapter extends RecyclerView.Adapter<DownloadsAdapter.MyHolder> {
    private Context context;
    private List<DownloadsData> finalDownload;


    public DownloadsAdapter(Context context,List<DownloadsData> finalDownload)
    {
        this.context = context;
        this.finalDownload = finalDownload;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup container, int viewType) {
        View root= LayoutInflater.from(container.getContext()).inflate(R.layout.download_list,container,false);
        DownloadsAdapter.MyHolder myHolder=new DownloadsAdapter.MyHolder(root);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {

        final DownloadsData ddata = finalDownload.get(position);

        holder.name.setText(ddata.getName());
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.shri76goldarjikelavanimandal.com/Uploads/Downloads/"+ddata.getFileName()));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return finalDownload.size();
    }

    class MyHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        Button download;
        public MyHolder(@NonNull View root) {
            super(root);
            name = root.findViewById(R.id.download_name);
            download = root.findViewById(R.id.button_download);
            download.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction())
                    {
                        case MotionEvent.ACTION_DOWN:
                            view.setBackground(context.getResources().getDrawable(R.drawable.button_download1_pressed));
                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            view.setBackground(context.getResources().getDrawable(R.drawable.button_download1));
                            break;
                    }
                    return false;
                }
            });
        }
    }
}

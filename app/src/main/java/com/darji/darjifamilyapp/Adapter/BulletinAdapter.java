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

import com.darji.darjifamilyapp.R;

public class BulletinAdapter extends RecyclerView.Adapter<BulletinAdapter.MyHolder> {
    private Context context;
    private int names[]={R.string.bulletin_1,R.string.bulletin_2};
    private String links[]={"https://www.shri76goldarjikelavanimandal.com/Uploads/Bulletins/517c7d1bd055704f77c20b18b25d44f5.pdf","https://www.shri76goldarjikelavanimandal.com/Uploads/Bulletins/1350f505c9a4099618e3847b29b80def.pdf"};

    public BulletinAdapter(Context context)
    {
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup container, int viewType) {
        View root= LayoutInflater.from(container.getContext()).inflate(R.layout.bulletin_list,container,false);
        BulletinAdapter.MyHolder myHolder=new BulletinAdapter.MyHolder(root);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.name.setText(context.getResources().getString(names[position]));
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(links[position]));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    class MyHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        Button download;
        public MyHolder(@NonNull View root) {
            super(root);
            name = root.findViewById(R.id.bulletin_name);
            download = root.findViewById(R.id.bulletin_download);
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

package com.darji.darjifamilyapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.darji.darjifamilyapp.R;
import com.darji.darjifamilyapp.ui.gallery.GalleryViewActivity;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryHolder> {
    Context context;

    int thumbs[] = {R.mipmap.g1,R.mipmap.g2,R.mipmap.g3,R.mipmap.g4,R.mipmap.g5};
    String titles[] = {"CORONA WARRIORS"," સ્નેહ સમ્મેલન - 2019","કેરીઅર ગાઈડન્સ સેમિનાર -2019","નવદંપતી (2018-2020)","વિદેશ અભ્યાસાર્થે જતા વિદ્યાર્થીઓ નું સન્માન"};
    String dates[] = {"01 Jan 2020","01 Jan 2020","01 Jan 2020","01 Jan 2020","01 Jan 2020"};

    public GalleryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public GalleryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallerylist,parent,false);
        GalleryHolder galleryHolder = new GalleryHolder(root);
        return galleryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryHolder holder, final int position) {
        holder.thumb.setImageResource(thumbs[position]);
        holder.title.setText(titles[position]);
        holder.date.setText(dates[position]);

        holder.main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(context, GalleryViewActivity.class);
                i.putExtra("id","1"); //Add ID Here
                i.putExtra("title",titles[position]);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    class GalleryHolder extends RecyclerView.ViewHolder{
        ImageView thumb;
        TextView title,date;
        View main;
        public GalleryHolder(@NonNull View itemView) {
            super(itemView);
            thumb = itemView.findViewById(R.id.gallery_thumb);
            title = itemView.findViewById(R.id.gallery_title);
            date = itemView.findViewById(R.id.gallery_date);
            main = itemView;
        }
    }
}

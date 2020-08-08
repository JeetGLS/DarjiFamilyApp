package com.darji.darjifamilyapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.darji.darjifamilyapp.Model.ApiClient;
import com.darji.darjifamilyapp.Model.ApiInterface;
import com.darji.darjifamilyapp.Model.GalleryData;
import com.darji.darjifamilyapp.R;
import com.darji.darjifamilyapp.ui.gallery.GalleryViewActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryHolder> {
    Context context;
    List<GalleryData> list;

    public GalleryAdapter(Context context,List<GalleryData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GalleryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallerylist,parent,false);
        GalleryHolder galleryHolder = new GalleryHolder(root);
        return galleryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final GalleryHolder holder, final int position) {

        final GalleryData data = list.get(position);


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<String>> call = apiService.getGalleryFiles(Integer.parseInt(data.getGalleryId()));
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> imageList = response.body();
                if(imageList!=null) {
                    final String thumb = imageList.get(0);
                    //Log.d("Gallery", "Data: " + thumb);
                    Glide.with(context)
                            .load(Uri.parse(ApiClient.BASE_URL + thumb))
                            .into(holder.thumb);
                }
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {}
        });

        holder.title.setText(data.getGalleryName());
        holder.date.setText(data.getProgramDate());

        holder.main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(context, GalleryViewActivity.class);
                i.putExtra("id",data.getGalleryId()); //Add ID Here
                i.putExtra("title",data.getGalleryName());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
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

package com.darji.darjifamilyapp.Adapter;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.darji.darjifamilyapp.Model.ApiClient;
import com.darji.darjifamilyapp.R;
import com.stfalcon.imageviewer.StfalconImageViewer;
import com.stfalcon.imageviewer.loader.ImageLoader;

import java.util.List;

public class GalleryViewAdapter extends RecyclerView.Adapter<GalleryViewAdapter.PhotosHolder> {

    Context context;
    List<String> list;


    public GalleryViewAdapter(Context context,List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PhotosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.galleryviewlist,parent,false);
        PhotosHolder photosHolder = new PhotosHolder(root);
        return photosHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PhotosHolder holder, final int position) {

        final String imageURL = ApiClient.BASE_URL+list.get(position);

        //Image List
        Glide.with(context).load(Uri.parse(imageURL)).into(holder.image);
        //Image Viewer
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new StfalconImageViewer.Builder<>(context, list, new ImageLoader<String>() {
                    @Override
                    public void loadImage(ImageView imageView, String image) {
                        Glide.with(context).load(Uri.parse(ApiClient.BASE_URL+image)).into(imageView);
                    }
                }).withStartPosition(position).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PhotosHolder extends RecyclerView.ViewHolder{
        ImageView image;
        public PhotosHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.gallery_image);
        }
    }
}

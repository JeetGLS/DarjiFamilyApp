package com.darji.darjifamilyapp.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.darji.darjifamilyapp.Model.GalleryData;
import com.darji.darjifamilyapp.R;
import com.stfalcon.imageviewer.StfalconImageViewer;
import com.stfalcon.imageviewer.loader.ImageLoader;

public class GalleryViewAdapter extends RecyclerView.Adapter<GalleryViewAdapter.PhotosHolder> {

    Context context;
    Integer images[] = {R.mipmap.gv1,R.mipmap.gv2,R.mipmap.gv3,R.mipmap.gv4,R.mipmap.gv5,R.mipmap.gv6,R.mipmap.gv7,R.mipmap.gv8,R.mipmap.gv9,R.mipmap.gv10,R.mipmap.gv11,R.mipmap.gv12,R.mipmap.gv13,R.mipmap.gv14,R.mipmap.gv15};
    public GalleryViewAdapter(Context context) {
        this.context = context;
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

        Glide.with(context).load(context.getDrawable(images[position])).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new StfalconImageViewer.Builder<>(context, images, new ImageLoader<Integer>() {
                    @Override
                    public void loadImage(ImageView imageView, Integer image) {
                        Glide.with(context).load(context.getDrawable(image)).into(imageView);
                    }
                }).withStartPosition(position).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    class PhotosHolder extends RecyclerView.ViewHolder{
        ImageView image;
        public PhotosHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.gallery_image);
        }
    }
}

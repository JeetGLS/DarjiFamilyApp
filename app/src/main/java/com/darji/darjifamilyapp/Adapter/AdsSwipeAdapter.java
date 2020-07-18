package com.darji.darjifamilyapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.darji.darjifamilyapp.R;

public class AdsSwipeAdapter extends PagerAdapter {

    //Temp: For testing
    private int[] image_resources = {R.drawable.ad1,R.drawable.ad2,R.drawable.ad3,R.drawable.ad4};
    private String[] image_links = {"https://www.visazone.in/","https://kishorinstitute.com/","http://www.risinggujarat.com/","https://www.gulfelevators.co.in/"};
    private Context context;

    private LayoutInflater layoutInflater;

    public AdsSwipeAdapter(Context context)
    {
        this.context = context;
    }

    @Override
    public int getCount() {
        return image_resources.length;
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.ads_swipe_layout,container,false);
        ImageView adImage = itemView.findViewById(R.id.adimage);
        adImage.setImageResource(image_resources[position]);
        adImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(image_links[position]));
                context.startActivity(i);
            }
        });
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}

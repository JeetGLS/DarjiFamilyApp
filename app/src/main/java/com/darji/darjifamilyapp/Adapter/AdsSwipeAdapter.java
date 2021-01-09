package com.darji.darjifamilyapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.darji.darjifamilyapp.Model.AdvertisementData;
import com.darji.darjifamilyapp.Model.ApiClient;
import com.darji.darjifamilyapp.R;
import com.darji.darjifamilyapp.ui.home.HomeFragment;

import java.util.List;

public class AdsSwipeAdapter extends PagerAdapter {

    private Context context;
    private List<AdvertisementData> list;

    private LayoutInflater layoutInflater;

    public AdsSwipeAdapter(Context context,List<AdvertisementData> list)
    {
        this.context = context;
        this.list = list;
    }

    private int getDataCount() {
        return list.size();
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.ads_swipe_layout,container,false);
        ImageView adImage = itemView.findViewById(R.id.adimage);

        //Handling Current Position
        final int DataPosition = position%getDataCount();

        //Setting Data
        final AdvertisementData data = list.get(DataPosition);

        //Log.d("AdsAdapter","Pos:"+position+", Data_position:"+ (DataPosition) );
        //Log.d("AdsAdapter",ApiClient.BASE_URL+"Uploads/Advertisement/"+data.getBannerImage());


        //Glide
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.ad_placeholder);
        requestOptions.error(R.mipmap.ad_placeholder);
        Glide.with(context)
                .load(Uri.parse(ApiClient.BASE_URL+"Uploads/Advertisement/"+data.getBannerImage()))
                .apply(requestOptions)
                .into(adImage);


        adImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(data.getWebsite()));
                try {
                    context.startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }

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

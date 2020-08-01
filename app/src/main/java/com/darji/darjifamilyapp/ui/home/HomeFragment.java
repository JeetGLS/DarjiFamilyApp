package com.darji.darjifamilyapp.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.darji.darjifamilyapp.Adapter.AdsSwipeAdapter;
import com.darji.darjifamilyapp.Adapter.NewsAdapter;
import com.darji.darjifamilyapp.Adapter.OccasionsSwipeAdapter;
import com.darji.darjifamilyapp.Model.AdvertisementData;
import com.darji.darjifamilyapp.Model.ApiClient;
import com.darji.darjifamilyapp.Model.ApiInterface;
import com.darji.darjifamilyapp.Model.NewsEventsData;
import com.darji.darjifamilyapp.Model.OccassionsData;
import com.darji.darjifamilyapp.R;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private ViewPager ads;
    private AdsSwipeAdapter adsAdapter;
    private List<AdvertisementData> adsData;
    private Timer timer;

    public static int AD_POSITION=0;

    private View occasion_holder;
    private ViewPager occasions;
    private View occasionsContainer;
    private OccasionsSwipeAdapter occasionsAdapter;
    private List<OccassionsData> occassionsData;

    public static int OCCASION_POSITION = 0;

    private RecyclerView news;
    private NewsAdapter newsAdapter;
    private List<NewsEventsData> newsData;
    private TextView nonews;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ///Retrofit API Interface
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);


        ////Ads Showing//////////////////////////////////////////////////////////////////////////////////////////////////
        ads = root.findViewById(R.id.ads_slider);
        Call<List<AdvertisementData>> adsCall = apiInterface.getAdvertisement();
        adsCall.enqueue(new Callback<List<AdvertisementData>>() {
            @Override
            public void onResponse(Call<List<AdvertisementData>> call, Response<List<AdvertisementData>> response) {
                adsData = response.body();
                if(adsData != null) {
                    adsAdapter = new AdsSwipeAdapter(getContext(), adsData);
                    ads.setAdapter(adsAdapter);
                    adsAutoScroll();
                }

            }
            @Override
            public void onFailure(Call<List<AdvertisementData>> call, Throwable t) {
                Toast.makeText(getContext(),"Failed to load Ads Data",Toast.LENGTH_SHORT).show();
            }
        });


        ////Occasions Showing/////////////////////////////////////////////////////////////////////////////////////////////
        occasion_holder = root.findViewById(R.id.occasion_holder);
        occasionsContainer = root.findViewById(R.id.occasions);
        occasions = root.findViewById(R.id.occasion_slider);
        Call<List<OccassionsData>> occasionsCall = apiInterface.getOccassions();
        occasionsCall.enqueue(new Callback<List<OccassionsData>>() {
            @Override
            public void onResponse(Call<List<OccassionsData>> call, Response<List<OccassionsData>> response) {
                occassionsData = response.body();
                if (occassionsData != null) {
                    occasionsAdapter = new OccasionsSwipeAdapter(getContext(), occassionsData);
                    occasions.setAdapter(occasionsAdapter);
                    occasionsContainer.setVisibility(View.VISIBLE);
                    occasion_holder.setVisibility(View.GONE);
                } else {
                    occasionsContainer.setVisibility(View.GONE);
                    occasion_holder.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<OccassionsData>> call, Throwable t) {
                Toast.makeText(getContext(),"Failed to load Occasions Data",Toast.LENGTH_SHORT).show();
            }
        });


        /////News Showing////////////////////////////////////////////////////////////////////////////////////////////////////////
        news = root.findViewById(R.id.news);
        nonews = root.findViewById(R.id.nonews);
        news.setLayoutManager(new LinearLayoutManager(getContext()));
        Call<List<NewsEventsData>> newsCall = apiInterface.getNewsEvents();

        newsCall.enqueue(new Callback<List<NewsEventsData>>() {
            @Override
            public void onResponse(Call<List<NewsEventsData>> call, Response<List<NewsEventsData>> response) {
                newsData = response.body();
                if(newsData==null) {
                    nonews.setVisibility(View.VISIBLE);
                    news.setVisibility(View.GONE);
                }
                else {
                    newsAdapter = new NewsAdapter(getContext(), newsData);
                    news.setAdapter(newsAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<NewsEventsData>> call, Throwable t) {
                Toast.makeText(getContext(),"Failed to load News Data",Toast.LENGTH_SHORT).show();
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        return root;
    }

    private void adsAutoScroll()
    {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(AD_POSITION==(adsAdapter.getCount()))
                    AD_POSITION=0;
                ads.setCurrentItem(AD_POSITION++,true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },250,5000);
    }
}
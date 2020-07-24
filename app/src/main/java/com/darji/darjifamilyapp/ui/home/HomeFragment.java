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
import com.darji.darjifamilyapp.Adapter.EndlessPagerAdapter;
import com.darji.darjifamilyapp.Adapter.NewsAdapter;
import com.darji.darjifamilyapp.MainActivity;
import com.darji.darjifamilyapp.Model.ApiClient;
import com.darji.darjifamilyapp.Model.ApiInterface;
import com.darji.darjifamilyapp.Model.NewsEventsData;
import com.darji.darjifamilyapp.R;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private ViewPager ads,occasions;
    private AdsSwipeAdapter adsAdapter;
    private EndlessPagerAdapter endlessPagerAdapter;
    private  Timer timer;
    private int current_position = 1;

    private RecyclerView news;
    private NewsAdapter newsAdapter;
    private List<NewsEventsData> newsData;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ///Retrofit API Interface
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        ////Ads Showing///////////////////////////////////////////////////
        ads = root.findViewById(R.id.ads_slider);
        adsAdapter = new AdsSwipeAdapter(getContext());
        endlessPagerAdapter = new EndlessPagerAdapter(adsAdapter,ads);
        ads.setAdapter(endlessPagerAdapter);
        //ads.setCurrentItem((int) (Math.random()*adsAdapter.getCount()));
        adsAutoScroll();


        ////Occasions Showing/////////////////////////////////////////
        occasions = root.findViewById(R.id.occasion_slider);


        /////News Showing////////////////////////////////////////////////////////////////////////////////////////////
        news = root.findViewById(R.id.news);
        news.setLayoutManager(new LinearLayoutManager(getContext()));
        Call<List<NewsEventsData>> call = apiInterface.getNewsEvents();

        call.enqueue(new Callback<List<NewsEventsData>>() {
            @Override
            public void onResponse(Call<List<NewsEventsData>> call, Response<List<NewsEventsData>> response) {
                newsData = response.body();
                newsAdapter = new NewsAdapter(getContext(),newsData);
                news.setAdapter(newsAdapter);
            }
            @Override
            public void onFailure(Call<List<NewsEventsData>> call, Throwable t) {
                Toast.makeText(getContext(),"Failed to load News Data",Toast.LENGTH_LONG).show();
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
                if(current_position==endlessPagerAdapter.getCount())
                    current_position=1;
                ads.setCurrentItem(current_position++,true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },250,3000);
    }
}
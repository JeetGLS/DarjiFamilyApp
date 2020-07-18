package com.darji.darjifamilyapp.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.darji.darjifamilyapp.Adapter.AdsSwipeAdapter;
import com.darji.darjifamilyapp.Adapter.EndlessPagerAdapter;
import com.darji.darjifamilyapp.MainActivity;
import com.darji.darjifamilyapp.R;

import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {
    private ViewPager ads;
    private AdsSwipeAdapter adsAdapter;
    private EndlessPagerAdapter endlessPagerAdapter;

    private  Timer timer;
    private int current_position = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ads = root.findViewById(R.id.ads_slider);
        adsAdapter = new AdsSwipeAdapter(getContext());
        endlessPagerAdapter = new EndlessPagerAdapter(adsAdapter,ads);
        ads.setAdapter(endlessPagerAdapter);
        //ads.setCurrentItem((int) (Math.random()*adsAdapter.getCount()));
        adsAutoScroll();
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
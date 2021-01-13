package com.darji.darjifamilyapp.ui.vasti;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.darji.darjifamilyapp.FullScreenMediaController;
import com.darji.darjifamilyapp.R;

public class VastiFragment extends Fragment {
    private Button vasti,vasti_view;
    private VideoView videoView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_vasti, container, false);
        final String site = "https://docs.google.com/forms/d/e/1FAIpQLSe7ccNXqGnF77mLcFAuLEtoIasS_SW54zJkKQfbnbe6_CxCiQ/viewform";
        final String viewSite = "https://docs.google.com/spreadsheets/d/1lKnVLsrgXi1b0XofyJ2i2-i5m8jWKWygQFjPnDKA5zw/";

        vasti_view = root.findViewById(R.id.vasti_view_button);
        vasti = root.findViewById(R.id.vasti_button);
        vasti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add Link Here
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(site));
                startActivity(i);
            }
        });
        vasti.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        view.setBackground(getContext().getResources().getDrawable(R.drawable.button_download1_pressed));
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        view.setBackground(getContext().getResources().getDrawable(R.drawable.button_download1));
                        break;
                }
                return false;
            }
        });

        vasti_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add Link Here
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(viewSite));
                startActivity(i);
            }
        });
        vasti_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        view.setBackground(getContext().getResources().getDrawable(R.drawable.button_download2_pressed));
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        view.setBackground(getContext().getResources().getDrawable(R.drawable.button_download2));
                        break;
                }
                return false;
            }
        });



        videoView = root.findViewById(R.id.video_player);
        FullScreenMediaController f = new FullScreenMediaController(root.getContext());
        f.setAnchorView(videoView);
        videoView.setMediaController(f);
        videoView.setKeepScreenOn(true);
        videoView.setVideoURI(Uri.parse("android.resource://" +getActivity().getPackageName()+ "/"+R.raw.video));
        videoView.start();
        videoView.requestFocus();


        return root;
    }
}
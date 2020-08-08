package com.darji.darjifamilyapp.ui.donate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.darji.darjifamilyapp.R;

public class DonateFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_donate, container, false);

        ImageView btnDonate = root.findViewById(R.id.btn_donate);
        final String site = "https://www.shri76goldarjikelavanimandal.com/#popup_register";

        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(site));
                startActivity(i);
            }
        });

        return root;
    }
}
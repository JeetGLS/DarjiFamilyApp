package com.darji.darjifamilyapp.ui.contact;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.darji.darjifamilyapp.Model.ApiClient;
import com.darji.darjifamilyapp.Model.ApiInterface;
import com.darji.darjifamilyapp.Model.MatrimonialData;
import com.darji.darjifamilyapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;

    ScrollView mainScrollView;
    EditText name,email,phone,message;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_contact, container, false);

        //For scroll handling
        mainScrollView = mView.findViewById(R.id.contactsscroll);
        /*
        ImageView mapLayout;
        mapLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        // Disallow ScrollView to intercept touch events.
                        Log.d("Map","down");
                        mainScrollView.requestDisallowInterceptTouchEvent(true);
                        // Disable touch on transparent view
                        return true;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        // Allow ScrollView to intercept touch events.
                        Log.d("Map","up");
                        mainScrollView.requestDisallowInterceptTouchEvent(false);
                        return true;
                }
                return true;
            }
        });*/


        Button submit = mView.findViewById(R.id.button_mail);
        name = mView.findViewById(R.id.name);
        email = mView.findViewById(R.id.email);
        phone = mView.findViewById(R.id.phone);
        message = mView.findViewById(R.id.message);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });


        return mView;
    }

    public void sendData(){
        String nm = name.getText().toString();
        String em = email.getText().toString();
        String ph = phone.getText().toString();
        String mg = message.getText().toString();
        if(nm.isEmpty() || em.isEmpty() || ph.isEmpty() || mg.isEmpty()){
            Toast.makeText(getContext(),"All Fields Required",Toast.LENGTH_LONG).show();
        }else{
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<Integer> a = apiService.submitData(nm, em, mg, ph);
            a.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {

                }
                @Override
                public void onFailure(Call<Integer> call, Throwable t) {

                }
            });
            Toast.makeText(getContext(),"Submitted",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = mView.findViewById(R.id.mapview);
        if(mMapView != null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(23.055331,72.545035)).title("Shri 76 Gol Darji Kelavani Mandal, Satellite"));
        CameraPosition Library = CameraPosition.builder().target(new LatLng(23.055331,72.545035)).zoom(16).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Library));
    }
}
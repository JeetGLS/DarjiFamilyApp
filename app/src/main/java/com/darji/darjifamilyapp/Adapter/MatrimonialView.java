package com.darji.darjifamilyapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.darji.darjifamilyapp.Model.ApiClient;
import com.darji.darjifamilyapp.Model.MatrimonialData;
import com.darji.darjifamilyapp.R;
import com.google.gson.Gson;
import com.stfalcon.imageviewer.StfalconImageViewer;
import com.stfalcon.imageviewer.loader.ImageLoader;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MatrimonialView extends AppCompatActivity {
    List<String> s = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matrimonial_view);

        TextView canName = findViewById(R.id.candidatefull_name),
                canEmail = findViewById(R.id.candidate_email),
                canDob = findViewById(R.id.candidate_dob),
                canCity = findViewById(R.id.candidate_city);

        ImageView canPhoto = findViewById(R.id.candidateV_photo);

        TextView fathername = findViewById(R.id.mcFather),
                nativeplace = findViewById(R.id.mcNative),
                mothername = findViewById(R.id.mcMother),
                mothernative = findViewById(R.id.mcMotherNative),
                address = findViewById(R.id.mcAddress),
                brother = findViewById(R.id.mcBrotherCount),
                sister = findViewById(R.id.mcSisterCount),
                aboutme = findViewById(R.id.mcAbout),
                higerstedu = findViewById(R.id.mcQaulification),
                specialization = findViewById(R.id.mcSpecialization),
                occupationa = findViewById(R.id.mcOccupation),
                annualincome = findViewById(R.id.mcIncome),
                height = findViewById(R.id.mcHeight),
                weight = findViewById(R.id.mcWeight),
                age = findViewById(R.id.mcAge),
                expectation = findViewById(R.id.mcExpectation);

        Gson gson = new Gson();
        final MatrimonialData data = gson.fromJson(getIntent().getStringExtra("data"),MatrimonialData.class);

        canName.setText(data.getFirstName()+" "+data.getMiddleName()+" "+data.getLastName());
        canEmail.setText(data.getEmailId());
        canCity.setText(data.getCityName()+ " , "+data.getCountryName());

        String dateStr = data.getBirthDte();
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date d = format.parse(dateStr);
            @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
            dateStr = dateFormat.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        canDob.setText(dateStr);

        final Uri image = Uri.parse(ApiClient.BASE_URL+"Uploads/MatrimonialCandidates/"+data.getPhoto());
        Glide.with(this)
                .load(image)
                .into(canPhoto);
        fathername.setText(data.getMiddleName());
        nativeplace.setText(data.getNativePlace());
        mothername.setText(data.getMotherName());
        mothernative.setText(data.getMotherNativePlace());
        address.setText(data.getAddress());
        brother.setText(data.getBrothersHeadCount());
        sister.setText(data.getSistersHeadCount());
        aboutme.setText(data.getAboutMe());
        higerstedu.setText(data.getQualification());
        specialization.setText(data.getSpecialization());
        occupationa.setText(data.getOccupationName());
        annualincome.setText(data.getAnnualIncome());
        height.setText(data.getHeight() + " cms");
        weight.setText(data.getWeight() + " Kg");
        expectation.setText(data.getExpectationForPartner());

        String bdate = "";
        String ar[] = data.getBirthDte().split("-",3);
        bdate = getAge(Integer.parseInt(ar[0]),Integer.parseInt(ar[1]),Integer.parseInt(ar[2]));
//        bdate = testDate(data.getBirthDte());
        age.setText(bdate);

        s.add("holder");
        canPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new StfalconImageViewer.Builder<>(MatrimonialView.this, s , new ImageLoader<String>() {
                    @Override
                    public void loadImage(ImageView imageView, String img) {
                        Glide.with(MatrimonialView.this).load(image).into(imageView);
                    }
                }).show();
            }
        });
    }

/*
    private String testDate(String finalDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.parse(finalDate, formatter);
        Period p = Period.between(birthday, today);
        return p.getYears() + " years, " + p.getMonths() + " months";
    }*/

    private String getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        dob.set(year, month, day);
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        String ageString = age + " Years";
        return ageString;
    }

    @Override
    public Intent getParentActivityIntent() {
        finish();
        return getIntent();
    }
}

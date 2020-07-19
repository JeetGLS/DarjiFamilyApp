package com.darji.darjifamilyapp.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCallBase {

    @GET("ActivitiesAPI.php")
    Call<List<ActivitiesData>> getActivities();

    @GET("BulletinsAPI.php")
    Call<List<BulletinData>> getBulletins();

    @GET("Jobs.php")
    Call<List<JobsData>> getJobs();

    @GET("MatrimonialCandidatesAPI.php")
    Call<List<MatrimonialData>> getMatrimonial();

}

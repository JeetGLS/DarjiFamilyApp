package com.darji.darjifamilyapp.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("ActivitiesAPI.php")
    Call<List<ActivitiesData>> getActivities();

    @GET("BulletinsAPI.php")
    Call<List<BulletinData>> getBulletins();

    @GET("JobsAPI.php")
    Call<List<JobsData>> getJobs();

    @GET("MatrimonialCandidatesAPI.php")
    Call<List<MatrimonialData>> getMatrimonial();

    @GET("AdvertisementAPI.php")
    Call<List<AdvertisementData>> getAdvertisement();

    @GET("DownloadsAPI.php")
    Call<List<DownloadsData>> getDownloads();

    @GET("NewsEventsAPI.php")
    Call<List<NewsEventsData>> getNewsEvents();

    @GET("OccassionsAPI.php")
    Call<List<OccassionsData>> getOccassions();

    @GET("GalleryAPI.php")
    Call<List<GalleryData>> getGallery();

    @GET("GalleryFilesAPI.php")
    Call<List<String>> getGalleryFiles(@Query("id") int galleryID);

    @FormUrlEncoded
    @POST("contact-process.php")
    Call<Integer> submitData(@Field("name")String name, @Field("email")String email, @Field("message")String message, @Field("phone")String phone);

}

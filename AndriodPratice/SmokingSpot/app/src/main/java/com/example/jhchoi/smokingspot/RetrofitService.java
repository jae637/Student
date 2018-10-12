package com.example.jhchoi.smokingspot;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetrofitService {
    @GET("/api/v1/{userId}")
    Call<RetrofitRepo> getFirst(
            @Path("userId") String name
    );

    @FormUrlEncoded
    @POST("post.php")
    Call<RetrofitRepo> getPost(
            @Field("name") String name
    );
}
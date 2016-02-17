package com.example.shoumik.facebooklikefeedview.API;

import com.example.shoumik.facebooklikefeedview.model.HiveModel;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by shoumik on 2/4/16.
 */
public interface HivaAPI {

    @GET("/feed.json")
    public void getFeed(Callback<HiveModel> response);
}

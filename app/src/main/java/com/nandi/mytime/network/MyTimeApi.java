package com.nandi.mytime.network;

import com.nandi.mytime.model.CompaniesList;

import java.util.Map;

import retrofit.http.GET;
import retrofit.http.QueryMap;

/**
 * Created by nandi_000 on 11-11-2015.
 */
public interface MyTimeApi {

    @GET("/deals.json")
    CompaniesList getCompanies(@QueryMap Map<String, String> options);

}

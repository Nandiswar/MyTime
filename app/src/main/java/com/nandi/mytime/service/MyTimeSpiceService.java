package com.nandi.mytime.service;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

/**
 * Created by nandi_000 on 11-11-2015.
 */
public class MyTimeSpiceService extends RetrofitGsonSpiceService {

    private final String BASE_URL = "http://www.mytime.com/api/v1";

    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }
}

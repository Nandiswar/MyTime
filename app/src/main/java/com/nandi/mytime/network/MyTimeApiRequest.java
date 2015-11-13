package com.nandi.mytime.network;

import com.nandi.mytime.model.CompaniesList;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.Map;

/**
 * API request which fetches the list of companies
 * Created by nandi_000 on 11-11-2015.
 */
public class MyTimeApiRequest extends RetrofitSpiceRequest<CompaniesList,MyTimeApi> {

    Map<String,String> queryParams;

    public MyTimeApiRequest(Map<String,String> queryParams) {
        super(CompaniesList.class, MyTimeApi.class);
        this.queryParams = queryParams;
    }

    @Override
    public CompaniesList loadDataFromNetwork() throws Exception {
        return getService().getCompanies(queryParams);
    }
}

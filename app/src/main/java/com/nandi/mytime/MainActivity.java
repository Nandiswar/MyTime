package com.nandi.mytime;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.nandi.mytime.Adapter.CompanyAdapter;
import com.nandi.mytime.model.CompaniesList;
import com.nandi.mytime.model.CompanyOverview;
import com.nandi.mytime.network.MyTimeApiRequest;
import com.nandi.mytime.service.MyTimeSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    SpiceManager spiceManager = new SpiceManager(MyTimeSpiceService.class);
    MyTimeApiRequest request;
    private ProgressDialog progressDialog;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<CompanyOverview> companyOverviewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews(); // initialize the views
        fetchData(); // create api request
    }

    private void bindViews() {
        progressDialog = new ProgressDialog(this, R.style.dialogTheme);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        companyOverviewList = new ArrayList<CompanyOverview>();
        mAdapter = new CompanyAdapter(companyOverviewList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void fetchData() {
        //?what=Massage&when=Anytime&where=34.052200,-118.242800
        Map<String,String> queryParams = new HashMap<String, String>();
        queryParams.put("what", "Massage");
        queryParams.put("when", "Anytime");
        queryParams.put("where", "34.052200,-118.242800");

        // create an api request
        request = new MyTimeApiRequest(queryParams);
        // display loading bar when fetching the data
        showProgressDialog();
        // make the api call
        spiceManager.execute(request, "Companies List", DurationInMillis.ONE_MINUTE,
                new MyTimeApiRequestListener());
    }

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    /**
     * Listener to the api call
     */
    private class MyTimeApiRequestListener implements RequestListener<CompaniesList> {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
            closeProgressDialog(); // close the loading bar upon finishing the api call
            Toast.makeText(MainActivity.this, "Please check your data connection!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(CompaniesList companyOverviews) {
            closeProgressDialog(); // close the loading bar upon finishing the api call
            if(companyOverviews != null && companyOverviews.size()!= 0) {
                companyOverviewList.clear();
                companyOverviewList.addAll(companyOverviews); // add the response to the list
                mAdapter.notifyDataSetChanged(); // notify adapter about data change
            }
        }
    }

    /**
     * show the loading bar
     */
    private void showProgressDialog() {
        if(!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    /**
     * close the loading bar
     */
    private void closeProgressDialog() {
        if(progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}

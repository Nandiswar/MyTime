package com.nandi.mytime.Adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nandi.mytime.R;
import com.nandi.mytime.model.CompanyOverview;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nandi_000 on 11-11-2015.
 */
public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {

    List<CompanyOverview> companyOverviewList;

    public CompanyAdapter(List<CompanyOverview> companyOverviewList) {
        this.companyOverviewList = companyOverviewList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.company_list_row, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CompanyOverview companyOverview = companyOverviewList.get(position);
        String imgUrl = companyOverview.photo_url;
        if(imgUrl != null && imgUrl != "") {
            Picasso.with(holder.mImageView.getContext()).load(Uri.parse(imgUrl))
                    .into(holder.mImageView);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public ViewHolder(View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.imageView);
        }
    }


    @Override
    public int getItemCount() {
        return companyOverviewList.size();
    }
}

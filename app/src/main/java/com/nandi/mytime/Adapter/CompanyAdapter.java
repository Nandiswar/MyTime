package com.nandi.mytime.Adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nandi.mytime.R;
import com.nandi.mytime.Utility;
import com.nandi.mytime.model.CompanyOverview;
import com.nandi.mytime.model.Location;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter which provides recycle views to the list
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

    /**
     * Load the data into each view
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CompanyOverview companyOverview = companyOverviewList.get(position); // company information

        holder.companyNameView.setText(companyOverview.name); // set company name
        holder.serviceNameView.setText(companyOverview.service_name); // set service name

        // load image into the view
        String imgUrl = companyOverview.photo_url;
        if (imgUrl != null && imgUrl != "") {
            Picasso.with(holder.mImageView.getContext())
                    .load(Uri.parse(imgUrl))
                    .into(holder.mImageView);
        }

        // fetch the first appointment date and convert into required display format
        String apptDate = Utility.getDateFormat(companyOverview.next_appointment_times[0]);
        if (apptDate != null) {
            holder.nextAppointmentView.setText("Next appt " + apptDate);
        }

        // fetch location values - lat, lon
        Location loc = companyOverview.location;
        if (loc != null) {
            String distance = Utility.calculateDistance(loc.lat, loc.lon); // get the distance in miles
            holder.distanceView.setText(distance + " mi away");
        }

        // load yelp ratings image
        if (companyOverview.yelp_rating_image_url != null) {
            Picasso.with(holder.ratingsView.getContext()).load(Uri.parse(companyOverview.yelp_rating_image_url))
                    .into(holder.ratingsView);
        }
        // set the price
        holder.priceView.setText("$" + (int) companyOverview.min_price + " - $" + (int) companyOverview.max_price);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView companyNameView;
        public TextView serviceNameView;
        public TextView distanceView;
        public TextView nextAppointmentView;
        public TextView priceView;
        public ImageView ratingsView;

        public ViewHolder(View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.imageView);
            companyNameView = (TextView) v.findViewById(R.id.companyNameView);
            serviceNameView = (TextView) v.findViewById(R.id.serviceNameView);
            distanceView = (TextView) v.findViewById(R.id.distanceView);
            nextAppointmentView = (TextView) v.findViewById(R.id.nextAppointmentView);
            priceView = (TextView) v.findViewById(R.id.priceView);
            ratingsView = (ImageView) v.findViewById(R.id.yelpRatingImg);
        }
    }

    @Override
    public int getItemCount() {
        return companyOverviewList.size();
    }
}

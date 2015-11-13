package com.nandi.mytime;

import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by nandi_000 on 13-11-2015.
 */
public class Utility {

    private static final Double METERS_TO_MILES_FACTOR = 0.000621371;
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"; //2015-11-13T20:00:00Z

    /**
     * Method to accept the date in DATE_FORMAT and return in MM/dd/yy format
     * @param apptDate
     * @return date in MM/dd/yy format
     */
    public static String getDateFormat(String apptDate) {
        if(!apptDate.equals("")) {
            try {
                SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
                apptDate = apptDate.replace("Z", "+00:00");
                Date dt = format.parse(apptDate);
                SimpleDateFormat output_format = new SimpleDateFormat("MM/dd/yy");
                return output_format.format(dt);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Method to calculate distance between two location points
     * @param latitude
     * @param longitude
     * @return distance in miles
     */
    public static String calculateDistance(String latitude, String longitude) {
        Double sourceLat = 34.052200;
        Double sourceLong = -118.242800;

        Location source = new Location("source");
        source.setLatitude(sourceLat);
        source.setLongitude(sourceLong);

        Location destination = new Location("destination");
        destination.setLatitude(Double.parseDouble(latitude));
        destination.setLongitude(Double.parseDouble(longitude));

        // find distance & convert from meters to miles
        Double distance = source.distanceTo(destination) * METERS_TO_MILES_FACTOR ;
        return String.format("%.1f", distance);
    }

}

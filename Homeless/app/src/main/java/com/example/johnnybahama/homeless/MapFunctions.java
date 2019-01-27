package com.example.johnnybahama.homeless;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class MapFunctions {

    public static int getOldness(Date currentDate, Date postDate){

        int tempDays = currentDate.getDate() - postDate.getDate();
        int tempMonths = (currentDate.getMonth() + 1) - (postDate.getMonth() + 1);
        int tempYears = (currentDate.getYear() + 1900) - (postDate.getYear() + 1900);
        int totalDaysSince = (tempYears*365) + (tempMonths*30) + tempDays;
        return totalDaysSince;
    }

    public static int generateWidth(Date currentDate, Date postDate, int likes, String sortPref) {

        int ratio = 1;
        if (sortPref == "likes") {
            if (likes < 0) {
                return (100 + likes);
            } else {
                return (100) + (likes * ratio);
            }
        }
        if (sortPref == "date") {
            int timeBetween =  MapFunctions.getOldness(currentDate, postDate);
            return 200 - timeBetween;
        }
        return 200;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

//    public static boolean isInRegion(double lat, double lng, int radius){
////        if(thisproperty == false)return false
//
//        if(calculateDistance(lat,lng) < radius ){
//            return true;
//        }
//        else{
//            return false;
//        }
//
//    }

    public static float calculateDistance(double newlat, double newlng, double currentLat, double currentLng){

        float distanceFloats[] = new float[] {1};
        Location.distanceBetween(currentLat,currentLng, newlat, newlng, distanceFloats);
        return distanceFloats[0];
    }

    public static void dropPinEffect(final Marker marker) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        final long duration = 1500;

        final Interpolator interpolator = new BounceInterpolator();

        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = Math.max(
                        1 - interpolator.getInterpolation((float) elapsed
                                / duration), 0);
                marker.setAnchor(0.5f, 1.0f + 14 * t);

                if (t > 0.0) {
                    // Post again 15ms later.
                    handler.postDelayed(this, 15);
                } else {
                    //marker.showInfoWindow();

                }
            }
        });
    }

    public static Bitmap resizeMapIcons(String iconName, int width, int height, Context context){
        Bitmap imageBitmap = BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier(iconName, "drawable", context.getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    public static void loadShelters (final GoogleMap gMap, final double currentLat, final double currentLng, CustomInfoWindowGoogleMap customInfoWindow, final Context currentContext){
        gMap.clear();
        gMap.setInfoWindowAdapter(customInfoWindow);

        FirebaseDatabase.getInstance().getReference().child("Shelters")
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Toast notLongError = Toast.makeText(currentContext, "Just double dookied ", Toast.LENGTH_LONG);
                        notLongError.show();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            LatLng MLatLng = new LatLng(Double.parseDouble(snapshot.child("lat").getValue().toString()), Double.parseDouble(snapshot.child("lng").getValue().toString()));
                            String MName = snapshot.child("name").getValue().toString();
                            String MDescription = snapshot.child("description").getValue().toString();
                            int MNumPosts = Integer.parseInt(snapshot.child("numPosts").getValue().toString());




                            Shelter dummyShelter = new Shelter(MLatLng.latitude, MLatLng.longitude, MName, MNumPosts, MDescription);

                                Marker placeHolderPin = gMap.addMarker(new MarkerOptions().position(MLatLng).title(MName).snippet(MDescription).icon(BitmapDescriptorFactory.fromBitmap(MapFunctions.resizeMapIcons("temppin", MNumPosts, MNumPosts, currentContext))));
                                MapFunctions.dropPinEffect(placeHolderPin);
                                placeHolderPin.setTag(dummyShelter);

                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }


}

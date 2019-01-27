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

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;

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

//    public static void loadPins (final GoogleMap gMap, final int mapRadius, final double currentLat, final double currentLng, final Date currentDate, final String viewSort, CustomInfoWindowGoogleMap customInfoWindow, final ArrayList<Marker> modelMarkers, final Context currentContext){
//        gMap.clear();
//        gMap.setInfoWindowAdapter(customInfoWindow);
//        gMap.addCircle(new CircleOptions().center(new LatLng(currentLat, currentLng))
//                .radius(mapRadius)
//                .strokeWidth(100.0f)
//                .fillColor(0x220000FF)
//                .strokeColor(Color.GRAY));
//
//        FirebaseDatabase.getInstance().getReference().child("Pins")
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//
//                            LatLng MLatLng = new LatLng(Double.parseDouble(snapshot.child("lat").getValue().toString()), Double.parseDouble(snapshot.child("lng").getValue().toString()));
//                            String MTitle = snapshot.child("body").getValue().toString();
//                            String MBody = snapshot.child("title").getValue().toString();
//                            String MPostType = snapshot.child("postType").getValue().toString();
//                            String MPoster = snapshot.child("originalPoster").getValue().toString();
//                            Date MDate = new Date();
//                            MDate.setHours(Integer.valueOf(snapshot.child("date").child("hours").getValue().toString()));
//                            MDate.setDate(Integer.valueOf(snapshot.child("date").child("date").getValue().toString()));
//                            MDate.setMonth(Integer.valueOf(snapshot.child("date").child("month").getValue().toString()));
//                            int MLikes = Integer.parseInt(snapshot.child("likes").getValue().toString());
//                            String MID = snapshot.getKey();
//                            //  String MDate = snapshot.child("date").getValue().toString();
//                            int MWidth = MapFunctions.generateWidth(currentDate, MDate, MLikes, "date");
//
//                            int daysSince = MapFunctions.getOldness(currentDate, MDate);
//                            Pin dummyPin = new Pin(MDate, MBody, MTitle, MLatLng.latitude, MLatLng.longitude, MPostType, MPoster, MLikes, MID);
//                            if(MPostType.equals(viewSort) || viewSort.equals("All") ) {
//
//                                Marker placeHolderPin = gMap.addMarker(new MarkerOptions().position(MLatLng).title(MTitle).snippet(MBody).icon(BitmapDescriptorFactory.fromBitmap(MapFunctions.resizeMapIcons(MPostType,MWidth, MWidth, currentContext))));
//                                modelMarkers.add(placeHolderPin);
//                                if(MapFunctions.calculateDistance(MLatLng.latitude,MLatLng.longitude, currentLat, currentLng) > mapRadius){
//                                    placeHolderPin.setAlpha(0.4f);
//                                }
//                                MapFunctions.dropPinEffect(placeHolderPin);
//                                placeHolderPin.setTag(dummyPin);
//                            }
//                        }
//                    }
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                    }
//                });
//    }


}

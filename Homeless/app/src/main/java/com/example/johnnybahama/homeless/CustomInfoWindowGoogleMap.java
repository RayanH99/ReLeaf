package com.example.johnnybahama.homeless;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public CustomInfoWindowGoogleMap(Context ctx){
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.map_custom_infowindo, null);

        TextView name_tv = view.findViewById(R.id.name);
        TextView details_tv = view.findViewById(R.id.details);
        ImageView img = view.findViewById(R.id.pic);

        TextView likes_tv = view.findViewById(R.id.likes);
        TextView date_tv = view.findViewById(R.id.date);
        TextView poster_tv = view.findViewById(R.id.poster);

        name_tv.setText(marker.getTitle());
        likes_tv.setText(marker.getSnippet());

        Shelter infoPin = (Shelter) marker.getTag();


        details_tv.setText("");
        date_tv.setText("Number of donators is " + infoPin.getNumPosts());
        poster_tv.setText("");

        return view;
    }
}

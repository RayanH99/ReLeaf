package com.example.johnnybahama.homeless;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MapsView extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng currentLatLng;
    private Marker shelter1;
    private Marker shelter2;
    private Marker shelter3;
    private Shelter ass;
    private ImageView checkout;
    private ImageView profile;
    private Context currentContext = this;
    private SharedPreferences currentPrefs;

    DatabaseReference databasePins;

    private int woolblankets;
    private int barrillapasta;
    private int oralbtoothbrushes;
    private int delmontebanana;

    private int beans;
    private int renovation;


    private CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(this);
    private ArrayList<Marker> modelMarkers = new ArrayList<Marker>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps_view);
        overridePendingTransition(17432576, 17432576);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        currentPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);
        SharedPreferences.Editor currenPrefEditor = currentPrefs.edit();




        Intent retrievecart= getIntent();
        if(retrievecart.getExtras() != null){

            if(retrievecart.getExtras().get("Shelter").equals("Mission Services")){

                beans = Integer.valueOf(retrievecart.getExtras().getString("beans"));
                woolblankets = Integer.valueOf(retrievecart.getExtras().getString("woolblankets"));
                barrillapasta = Integer.valueOf(retrievecart.getExtras().getString("barrillapasta"));
                renovation = Integer.valueOf(retrievecart.getExtras().getString("renovation"));

                int oldbeans = Integer.valueOf(currentPrefs.getString("beans","nobeans"));
                int oldwoolblankets = Integer.valueOf(currentPrefs.getString("woolblankets","nobeans"));
                int oldbarrillapasta = Integer.valueOf(currentPrefs.getString("barrillapasta","nobeans"));
                int oldrenovation = Integer.valueOf(currentPrefs.getString("renovation","nobeans"));

                currenPrefEditor.putString("beans", String.valueOf(beans + oldbeans));
                currenPrefEditor.putString("woolblankets", String.valueOf(woolblankets + oldwoolblankets));
                currenPrefEditor.putString("barrillapasta", String.valueOf(barrillapasta + oldbarrillapasta));
                currenPrefEditor.putString("renovation", String.valueOf(renovation + oldrenovation));
                currenPrefEditor.apply();

                        Toast notLongError = Toast.makeText(this, String.valueOf(String.valueOf(beans + oldbeans)), Toast.LENGTH_LONG);
                        notLongError.show();

            }

            if(retrievecart.getExtras().get("Shelter").equals("Good Shepard")){


                woolblankets = Integer.valueOf(retrievecart.getExtras().getString("woolblankets"));
                barrillapasta = Integer.valueOf(retrievecart.getExtras().getString("barrillapasta"));
                delmontebanana = Integer.valueOf(retrievecart.getExtras().getString("delmontebanana"));
                oralbtoothbrushes = Integer.valueOf(retrievecart.getExtras().getString("oralbtoothbrushes"));


                int oldwoolblankets = Integer.valueOf(currentPrefs.getString("woolblankets","nobeans"));
                int oldbarrillapasta = Integer.valueOf(currentPrefs.getString("barrillapasta","nobeans"));
                int olddemontebanana = Integer.valueOf(currentPrefs.getString("delmontebanana","nobeans"));
                int oldoralbtoothbrushes = Integer.valueOf(currentPrefs.getString("oralbtoothbrushes","nobeans"));


                currenPrefEditor.putString("woolblankets", String.valueOf(woolblankets + oldwoolblankets));
                currenPrefEditor.putString("barrillapasta", String.valueOf(barrillapasta + oldbarrillapasta));
                currenPrefEditor.putString("renovation", String.valueOf(oralbtoothbrushes + oldoralbtoothbrushes));
                currenPrefEditor.putString("delmontebananas", String.valueOf(delmontebanana + olddemontebanana));
                currenPrefEditor.apply();

            }


//            currentLat = Double.parseDouble(retrieveLatLng.getExtras().getString("Lat"));
//            currentLng = Double.parseDouble(retrieveLatLng.getExtras().getString("Lng"));
//            userEmail = retrieveLatLng.getExtras().getString("userEmail");
//
//            currenPrefEditor.putString("currentLat", String.valueOf(currentLat));
//            currenPrefEditor.putString("currentLng", String.valueOf(currentLng));
//            currenPrefEditor.apply();
//            currentLatLng = new LatLng(currentLat, currentLng);
//            // currenPrefEditor.commit();
        }
        else{
            currenPrefEditor.putString("woolblankets", String.valueOf("0"));
            currenPrefEditor.putString("barrillapasta", String.valueOf("0"));
            currenPrefEditor.putString("renovation", String.valueOf("0"));
            currenPrefEditor.putString("delmontebananas", String.valueOf("0"));
            currenPrefEditor.putString("beans", String.valueOf("0"));
            currenPrefEditor.putString("oldoralbtoothbrushes", String.valueOf("0"));
            currenPrefEditor.apply();

//            Toast notLongError = Toast.makeText(getApplicationContext(), "Loaded new Cart", Toast.LENGTH_SHORT);
//            notLongError.show();
           // currentLatLng = new LatLng(currentLat, currentLng);
        }




        databasePins = FirebaseDatabase.getInstance().getReference("Shelters");


        checkout = findViewById(R.id.imageView13);
        profile = findViewById(R.id.imageView9);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String pinID = databasePins.push().getKey();
                Shelter pin = new Shelter(20.0,20.0,"a",20,"R");
                databasePins.child(pinID).setValue(pin);

//                notLongError.show();

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent goToNewPin = new Intent(MapsView.this, Profile.class);
           //     goToNewPin.putExtra("Shelter", ((Shelter) marker.getTag()).getName());
                startActivity(goToNewPin);

//                notLongError.show();

            }
        });


    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        mMap.setInfoWindowAdapter(customInfoWindow);


        MapFunctions.loadShelters(mMap,currentLatLng.latitude, currentLatLng.longitude,customInfoWindow,this);



     //   MapFunctions.dropPinEffect(shelter1);
    //    MapFunctions.dropPinEffect(shelter2);
      //  MapFunctions.dropPinEffect(shelter3);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                if(((Shelter) marker.getTag()).getName().equals("Mission Services of Hamilton")){
                    Intent goToNewPin = new Intent(MapsView.this, ListView.class);
                    goToNewPin.putExtra("Shelter", ((Shelter) marker.getTag()).getName());
                    startActivity(goToNewPin);
                }

                if(((Shelter) marker.getTag()).getName().equals("Good Sheppard")){
                    Intent goToNewPin = new Intent(MapsView.this, viewList2.class);
                    goToNewPin.putExtra("Shelter", ((Shelter) marker.getTag()).getName());
                    startActivity(goToNewPin);
                }






            }
        });



    }


}

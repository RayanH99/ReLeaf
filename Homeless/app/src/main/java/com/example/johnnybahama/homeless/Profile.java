package com.example.johnnybahama.homeless;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

public class Profile extends AppCompatActivity {

    private ImageView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        overridePendingTransition(17432576, 17432576);
        Bitmap mapBitMap = BitmapFactory.decodeResource(this.getResources(), this.getResources().getIdentifier("mapdesel", "drawable", this.getPackageName()));
        Bitmap profileBitMap = BitmapFactory.decodeResource(this.getResources(), this.getResources().getIdentifier("profilesel", "drawable", this.getPackageName()));


        final TextView seedsButton = findViewById(R.id.seedCount);
        final ImageView map = findViewById(R.id.imageView5);
        final ImageView profile = findViewById(R.id.imageView9);

        map.setImageBitmap(mapBitMap);
        profile.setImageBitmap(profileBitMap);

        seedsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Profile.this, SeedsActivity.class);
                Profile.this.startActivity(myIntent);
            }
        });

        final TextView paymentMethodButton = findViewById(R.id.paymentHeading);
        paymentMethodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Profile.this, paymentMethod.class);
                Profile.this.startActivity(myIntent);
            }
        });


        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent goToNewPin = new Intent(Profile.this, MapView.class);
                //     goToNewPin.putExtra("Shelter", ((Shelter) marker.getTag()).getName());
                startActivity(goToNewPin);

//                Toast notLongError = Toast.makeText(currentContext, "Just shitted", Toast.LENGTH_LONG);
//                notLongError.show();

            }
        });
    }
}

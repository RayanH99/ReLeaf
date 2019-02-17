package com.example.johnnybahama.homeless;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ListView extends AppCompatActivity {

    private soloPost dummy = new soloPost(12,12,12,12, "do");
    Double d1;
    private Context currentContext = this;
    private ImageView checkout;
    private ImageView profile;
    private ImageView returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        overridePendingTransition(17432576,17432576 );


        checkout = findViewById(R.id.imageView13);
        profile = findViewById(R.id.imageView9);

        final TextView q1 = findViewById(R.id.q1);
        final TextView q2 = findViewById(R.id.q2);
        final TextView q3 = findViewById(R.id.q3);
        final TextView q4 = findViewById(R.id.q4);

        returnButton = findViewById(R.id.registerButton);

        final ImageView returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ListView.this, MapView.class);
                ListView.this.startActivity(myIntent);
            }
        });

        final Button addtocart = findViewById(R.id.addtocart);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ListView.this, MapView.class);
                myIntent.putExtra("beans", q1.getText());
                myIntent.putExtra("barrillapasta", q2.getText());
                myIntent.putExtra("woolblankets", q3.getText());
                myIntent.putExtra("renovation", q4.getText());


                ListView.this.startActivity(myIntent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent goToNewPin = new Intent(ListView.this, Profile.class);
                startActivity(goToNewPin);



            }
        });




    }
}

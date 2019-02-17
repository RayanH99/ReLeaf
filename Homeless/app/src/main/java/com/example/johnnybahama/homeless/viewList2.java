package com.example.johnnybahama.homeless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

public class viewList2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list2);

        final TextView q1 = findViewById(R.id.q1);
        final TextView q2 = findViewById(R.id.q2);
        final TextView q3 = findViewById(R.id.q3);
        final TextView q4 = findViewById(R.id.q4);

        TextView profile = findViewById(R.id.imageView9);





        final Button addtocart = findViewById(R.id.addtocart);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(viewList2.this, MapView.class);
                myIntent.putExtra("oralbtoothbrushes", q1.getText());
                myIntent.putExtra("barrillapasta", q2.getText());
                myIntent.putExtra("woolblankets", q3.getText());
                myIntent.putExtra("delmontebanana", q4.getText());


                viewList2.this.startActivity(myIntent);
            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent goToNewPin = new Intent(viewList2.this, Profile.class);
                //     goToNewPin.putExtra("Shelter", ((Shelter) marker.getTag()).getName());
                startActivity(goToNewPin);


            }
        });
    }
}

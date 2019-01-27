package com.example.johnnybahama.homeless;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    private soloPost dummy = new soloPost(12,12,12,12, "doodoo");
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

        returnButton = findViewById(R.id.registerButton);

        final ImageView returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ListView.this, MapView.class);
                ListView.this.startActivity(myIntent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent goToNewPin = new Intent(ListView.this, Profile.class);
                //     goToNewPin.putExtra("Shelter", ((Shelter) marker.getTag()).getName());
                startActivity(goToNewPin);

//                Toast notLongError = Toast.makeText(currentContext, "Just shitted", Toast.LENGTH_LONG);
//                notLongError.show();

            }
        });

        Intent retrieveEmail= getIntent();
        String shelterName = retrieveEmail.getStringExtra("Shelter");
        Toast notLongError = Toast.makeText(this, shelterName, Toast.LENGTH_LONG);
        notLongError.show();
//        final TextView name1 = findViewById(R.id.name1);
//
//        final TextView desc1 = findViewById(R.id.description1);
//
//

//        FirebaseDatabase.getInstance().getReference().child("Shelters").child("-LVv-NoCkZ3m0cLksW95").child("soloPosts")
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//
//                    @Override
//
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//
//
//                            String MName = snapshot.child("name").getValue().toString();
//                            Double D1 = Double.valueOf(snapshot.child("price").getValue().toString());
//                            int MnumRequired = Integer.parseInt(snapshot.child("numRequired").getValue().toString());
//
//                            dummy.setName(MName);
////                            dummy.setPrice(MPrice);
//                            dummy.setPoint(MnumRequired);
//                            Toast notLongError = Toast.makeText(currentContext,String.valueOf(D1), Toast.LENGTH_LONG);
//                            notLongError.show();
//
//                            name1.setText(MName);
//                            desc1.setText("$" + String.valueOf(d1) + " (" + String.valueOf(dummy.getNumRequired()) + " Needed)" );
//
//
//
//                        }
//                    }
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                    }
//                });
//




       // name1.setText("REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");




    }
}

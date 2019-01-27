package com.example.johnnybahama.homeless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class paymentMethod extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        overridePendingTransition(17432576, 17432576);

        final TextView paymentMethodButton = findViewById(R.id.paymentHeading);
        paymentMethodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(paymentMethod.this, Profile.class);
                paymentMethod.this.startActivity(myIntent);
            }
        });
    }
}

package com.example.johnnybahama.homeless;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class registration extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
       // FirebaseApp.initializeApp(this);


        mAuth = FirebaseAuth.getInstance();



        final ImageView returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(registration.this, LoginScreen.class);
                registration.this.startActivity(myIntent);
            }
        });

        final Button registrationInitiation = findViewById(R.id.registerButton);
        registrationInitiation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // creates three variables with each respective response in the registration
                final EditText firstName = (EditText) findViewById(R.id.firstNameRegister);
                final EditText lastName = (EditText) findViewById(R.id.lastNameRegister);
                final EditText email = (EditText) findViewById(R.id.emailRegister);

                // activates a toast if any of the strings are null
                if (firstName.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "First name cannot be null!",
                            Toast.LENGTH_SHORT).show();
                } else if (lastName.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Last name cannot be null!",
                            Toast.LENGTH_SHORT).show();
                } else if (email.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Email cannot be null!",
                            Toast.LENGTH_SHORT).show();
                } else {    // creates a new profile object if none of them are null
                    profileObject newProfile = new profileObject(firstName.getText().toString(),
                            lastName.getText().toString(), email.getText().toString());
                    // activates toast to let user know an account has been created successfully
                    Toast.makeText(getApplicationContext(), "Account successfully created!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

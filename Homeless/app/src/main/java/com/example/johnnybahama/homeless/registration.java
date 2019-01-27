package com.example.johnnybahama.homeless;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registration extends AppCompatActivity {

    private FirebaseAuth mAuth;
    ProgressBar registerProgress;
    Button registerButton;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        registerButton = findViewById(R.id.registerButton);
        registerProgress = findViewById(R.id.progressBar);
        registerProgress.setVisibility(View.INVISIBLE);



        final ImageView returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(registration.this, LoginScreen.class);
                registration.this.startActivity(myIntent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // creates three variables with each respective response in the registration
                final EditText firstName = (EditText) findViewById(R.id.firstNameRegister);
                final EditText lastName = (EditText) findViewById(R.id.lastNameRegister);
                final EditText email = (EditText) findViewById(R.id.emailRegister);
                final EditText password = (EditText) findViewById(R.id.passwordRegister);


                // activates a toast if any of the strings are null
                if (firstName.getText().toString().equals("") || lastName.getText().toString().equals("") ||
                email.getText().toString().equals("") || password.getText().toString().equals("")) {

                    Toast.makeText(getApplicationContext(), "Please verify entries", Toast.LENGTH_SHORT).show();


                } else {    // creates a new profile object if none of them are null

                    profileObject newProfile = new profileObject(firstName.getText().toString(),
                            lastName.getText().toString(), email.getText().toString());


                    //Firebase User authentication
                    String userEmail = email.getText().toString();
                    String userPass = password.getText().toString();

                    mAuth.createUserWithEmailAndPassword(userEmail, userPass)
                            .addOnCompleteListener(registration.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        registerProgress.setVisibility(View.VISIBLE);
                                        registerButton.setVisibility(View.INVISIBLE);
                                        Toast.makeText(registration.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                        handler=new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent=new Intent(registration.this,LoginScreen.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        },1500);

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(registration.this, "Registration Failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                     }
            }
        });
    }
}

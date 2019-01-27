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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;

    ProgressBar loginProgress;
    Button loginButton;
    Button registerButton;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        overridePendingTransition(17432576,17432576 );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);
        loginProgress = findViewById(R.id.progressBar);
        loginProgress.setVisibility(View.INVISIBLE);

        
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LoginScreen.this, registration.class);
                LoginScreen.this.startActivity(myIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText email = (EditText) findViewById(R.id.login_user);
                final EditText password = (EditText) findViewById(R.id.login_password);

                if(email.getText().toString().equals("") || password.getText().toString().equals("")){

                    Toast.makeText(getApplicationContext(), "Please verify entries", Toast.LENGTH_SHORT).show();

                } else {

                    mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        loginProgress.setVisibility(View.VISIBLE);
                                        loginButton.setVisibility(View.INVISIBLE);
                                        registerButton.setVisibility(View.INVISIBLE);
                                        Toast.makeText(LoginScreen.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                        handler=new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent=new Intent(LoginScreen.this,MapsView.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        },1500);

                                    } else {
                                        // If sign in fails, display a message to the user.
                                         Toast.makeText(LoginScreen.this, "Either username or password is incorrect.",
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

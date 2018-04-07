package com.example.salmanmapkar.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splashScreen(2500);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            FirebaseUser user = mAuth.getCurrentUser();
            if (user.isEmailVerified()) {
                splashScreen(2500);
                finish();
                Intent intent = new Intent(getApplicationContext(), althomepage.class);
                startActivity(intent);

            } else {
                splashScreen(2500);
                finish();
                startActivity(new Intent(getApplicationContext(),VerifyAccount.class));
            }
        }
        else
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }
    public void splashScreen(final int x)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(x);
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }).run();
    }
}

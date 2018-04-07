package com.example.salmanmapkar.demo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VerifyAccount extends AppCompatActivity {
    FirebaseUser user;
    FirebaseAuth mAuth1;
    String email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth1.getInstance();
        setContentView(R.layout.activity_verify_account);
        Button btSignout = (Button)findViewById(R.id.btSignout);
        btSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signout();
                finish();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        Button btContinue = (Button)findViewById(R.id.btContinue);
        btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkIfAccountIsVerified();
            }
        });
    }
    public void signout()
    {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        // Get auth credentials from the user for re-authentication. The example below shows
        // email and password credentials but there are multiple possible providers,
        // such as GoogleAuthProvider or FacebookAuthProvider.
        AuthCredential credential = EmailAuthProvider
                .getCredential("user@example.com", "password1234");

        // Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Log.d("TAG", "User account deleted.");
                            }
                        }
                    });

                    }
                });
    }
    private void checkIfAccountIsVerified() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        user.reload();
        if (user.isEmailVerified()) {
            finish();
            startActivity(new Intent(getApplicationContext(),althomepage.class));

        } else {
            Toast.makeText(getApplicationContext(),"Verify your account to continue.",Toast.LENGTH_SHORT).show();
        }
    }
}

package com.example.salmanmapkar.demo;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ProviderQueryResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;
    EditText editTextPassword, editTextEmail;
    String email, password;
    TextView facultylogin;
    FirebaseUser user;
    Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        btLogin = (Button) findViewById(R.id.buttonLogin);
        btLogin.setOnClickListener(this);
        findViewById(R.id.facultylogin).setOnClickListener(this);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

    }

    private void userLogin() {
        password = editTextPassword.getText().toString().trim();
        email = editTextEmail.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Minimum lenght of password should be 6");
            editTextPassword.requestFocus();
            return;
        }
        mAuth.fetchProvidersForEmail(email).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                boolean check =!task.getResult().getProviders().isEmpty();
                if(!check)
                {
                    CreateAccount();
                }
                else
                {
                    SignInAccount();
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.facultylogin:
                finish();
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                break;

            case R.id.buttonLogin:
                userLogin();
                break;

        }
    }

    public void CreateAccount() {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            Toast.makeText(getApplicationContext(), "Account has been created.",
                                    Toast.LENGTH_SHORT).show();
                            VerifyAccount();
                            finish();
                            startActivity(new Intent(getApplicationContext(),VerifyAccount.class));

                        } else {
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });
    }

    public void VerifyAccount() {
        user=FirebaseAuth.getInstance().getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        // Re-enable button
                        findViewById(R.id.buttonLogin).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), "Click on Submit if Account has been Verified.",
                                    Toast.LENGTH_LONG).show();

                        } else {
                            Log.e("TAG", "sendEmailVerification", task.getException());
                            Toast.makeText(getApplicationContext(),
                                    "Failed to send verification email. Check if you have correctly enterred your email.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    public void SignInAccount() {
        final FirebaseAuth auth;
        auth=FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "signInWithEmail:success");
                             user = auth.getCurrentUser();
                            if (user.isEmailVerified()) {
                                finish();
                                Intent intent = new Intent(getApplicationContext(), althomepage.class);
                                startActivity(intent);


                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Verify your email to continue to app .",
                                        Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(),VerifyAccount.class));
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }

                        // ...
                    }
                });
    }
}

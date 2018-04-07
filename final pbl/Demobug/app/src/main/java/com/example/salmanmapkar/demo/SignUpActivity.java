package com.example.salmanmapkar.demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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

import java.security.Provider;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText editTextPassword, editTextEmail;
    String email, password;
    TextView studentlogin;
    FirebaseUser user;
    Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword= (EditText) findViewById(R.id.editTextPassword);
        mAuth = FirebaseAuth.getInstance();
        Button buttonsignup = (Button)findViewById(R.id.buttonSignUp);
        buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                registerUser();
            }
        });
        studentlogin=(TextView)findViewById(R.id.StudentLogin);
        studentlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }

    private void registerUser() {
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!email.contains("@mes.ac.in")) {
            editTextEmail.setError("Please enter a valid mes email");
            Toast.makeText(getApplicationContext(),"For Faculty Mes ids are mandatory",Toast.LENGTH_LONG).show();
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Minimum lenghth of password should be atlesat 6 characters");
            editTextPassword.requestFocus();
            return;
        }
        mAuth.fetchProvidersForEmail(email).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                boolean check =!task.getResult().getProviders().isEmpty();
                if(!check)
                {
                    if(email.contains("@mes.ac.in"))
                        CreateAccount();
                }
                else
                {
                    if(email.contains("@mes.ac.in"))
                        SignInAccount();
                }
            }
        });
    }

    public void VerifyAccount()
    {
        user=FirebaseAuth.getInstance().getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        // Re-enable button
                        findViewById(R.id.buttonSignUp).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(), "Click on Submit if Account has been Verified.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("TAG", "sendEmailVerification", task.getException());
                            Toast.makeText(getApplicationContext(),
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void CreateAccount()
    {
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
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

    public void SignInAccount() {
        FirebaseAuth auth;
        auth=FirebaseAuth.getInstance();
        final FirebaseUser Fuser = auth.getCurrentUser();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            if (Fuser.isEmailVerified()) {
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
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }




}

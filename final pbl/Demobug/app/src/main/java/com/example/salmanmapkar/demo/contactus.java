package com.example.salmanmapkar.demo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class contactus extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);


    }
    public void  BtnSetEmergency_onClick(View view){
        String number = "02227456100";
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+ number));
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            //TODO: Consider calling
            //ActivityCompat#requestPermissions
            // here to request the missing permission, and then overriding
            // public void onRequestPermissionResult(int reqCode , String[] permissions,
            //                                                      int[] grantResults)
            // to handle the case where the user grants the permission. See tje documentation
            // for ActivityCompat#requestPermssion for more details.
            return;
        }
        startActivity(intent);
    }

}

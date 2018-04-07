package com.example.salmanmapkar.demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import uk.co.senab.photoview.PhotoViewAttacher;

public class DownloadActivity extends AppCompatActivity {
    ImageView imageview;
    Bundle bundle;
    FirebaseAuth mAuth;
    FirebaseUser user;
    Button btUpload, btShare;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        mAuth = FirebaseAuth.getInstance();
        bundle = getIntent().getExtras();
        btUpload = (Button) findViewById(R.id.bupload);
        btUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UploadActivity.class);
                intent.putExtra("UploadName", bundle.getString("StandardName"));
                startActivity(intent);
            }
        });
        imageview = (ImageView) findViewById(R.id.timetableView);
        PhotoViewAttacher photoview = new PhotoViewAttacher(imageview);
        photoview.update();
    }

    @Override
    protected void onStart() {
        super.onStart();
        user = mAuth.getCurrentUser();
        String email = user.getEmail();
        if (!(email.contains("@mes.ac.in") || email !="salmansmapkar@gmail.com")) {
            btUpload.setVisibility(View.GONE);
        }

        storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference islandRef = storageRef.child("Timetable/"+bundle.getString("StandardName"));
        downloadInMemory(islandRef);
    }
    private void downloadInMemory(StorageReference fileRef) {
        if (fileRef != null) {
        try {
            final File localFile = File.createTempFile("images", "jpg");

            fileRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bmp = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    imageview.setImageBitmap(bmp);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {

                    Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        Toast.makeText(getApplicationContext(), "Upload file before downloading", Toast.LENGTH_LONG).show();
    }
    }

    private void DownloadTimetable()
    {
        
    }
}

package com.example.salmanmapkar.demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

public class UploadActivity extends AppCompatActivity {
    Uri uri;
    private Button selectImage , uploadImage;
    private StorageReference mStorage;
    private static final int GALLERY_INTENT =71;
    Bundle bundle;
    ImageView previewImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        bundle = getIntent().getExtras();
        mStorage = FirebaseStorage.getInstance().getReference();
        previewImage = (ImageView) findViewById(R.id.btuploadttview);
        selectImage = (Button) findViewById(R.id.btchoosefile);
        selectImage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Choose an image."),GALLERY_INTENT);
            }
        });
        uploadImage = (Button) findViewById(R.id.btuploadfile);
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadImage();
            }
        });
    }

    private void UploadImage() {
        if(uri != null)
        {
            final ProgressDialog pDailog = new ProgressDialog(this);
            pDailog.setTitle("Uploading image ...");
            pDailog.show();
            StorageReference childReference = mStorage.child("Timetable/" + bundle.getString("UploadName"));
            childReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pDailog.dismiss();
                    Toast.makeText(getApplicationContext(), "Uploaded.", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pDailog.dismiss();
                    Toast.makeText(getApplicationContext(),"Failed to upload image.",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_INTENT  &&  resultCode == RESULT_OK)
        {
            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                previewImage.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

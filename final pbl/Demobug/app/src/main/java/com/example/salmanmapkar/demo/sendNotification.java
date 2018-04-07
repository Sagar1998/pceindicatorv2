package com.example.salmanmapkar.demo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sendNotification extends AppCompatActivity {
    int nameCode;
    ArrayAdapter<CharSequence> adapter_year;
    ArrayAdapter<CharSequence> adapter_branch;
    ArrayAdapter<CharSequence> adapter_div;
    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText Editview;
    String Data;
    SharedPreferences sharedp ;
    int Not1;
    int Not2;
    int Not3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notification);
        sharedp = getSharedPreferences("mp",0);
        Not1 =sharedp.getInt("Noot1",1);
        Not2 =sharedp.getInt("Noot2",1);
        Not3 =sharedp.getInt("Noot3",1);
        Editview = (EditText) findViewById(R.id.NotifiData);
        final Button sendNotification = (Button) findViewById(R.id.sendNotifi);
        final Spinner spinner_year = (Spinner)findViewById(R.id.spYear);
        final Spinner spinner_branch = (Spinner)findViewById(R.id.spBranch);
        final Spinner spinner_div = (Spinner)findViewById(R.id.spDiv);
        adapter_year = ArrayAdapter.createFromResource(this,R.array.nYears,android.R.layout.simple_spinner_item);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_year.setAdapter(adapter_year);
        adapter_branch = ArrayAdapter.createFromResource(this,R.array.branch,android.R.layout.simple_spinner_item);
        adapter_branch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_branch.setAdapter(adapter_branch);
        adapter_div = ArrayAdapter.createFromResource(this,R.array.nDiv,android.R.layout.simple_spinner_item);
        adapter_div.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_div.setAdapter(adapter_div);
        spinner_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1)
                {
                    nameCode=2;
                }
                if(i==2)
                {
                    nameCode=3;
                }
                if(i==3)
                {
                    nameCode=4;
                }
                if(i>0)
                 spinner_branch.setVisibility(View.VISIBLE);
                else
                    spinner_branch.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1)
                {
                    nameCode=nameCode*10+i;
                }
                if(i==2)
                {
                    nameCode=nameCode*10+i;
                }
                if(i>0)
                    spinner_div.setVisibility(View.VISIBLE);
                else
                    spinner_div.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_div.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1)
                {
                    nameCode=nameCode*10+i;
                }
                if(i==2)
                {
                    nameCode=nameCode*10+i;
                }
                if(i>0)
                {
                    Editview.setVisibility(View.VISIBLE);
                    sendNotification.setVisibility(View.VISIBLE);
                }
                else
                {
                    Editview.setVisibility(View.GONE);
                    sendNotification.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data = Editview.getText().toString();
                if(nameCode > 200 && nameCode< 300)
                {
                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference("Notification/200");
                    myRef.child(String.valueOf(nameCode*10+Not1)).setValue(Data);
                    Toast.makeText(getApplicationContext(), "Notification send", Toast.LENGTH_SHORT).show();
                    Not1++;
                    SharedPreferences.Editor editor = sharedp.edit();
                    editor.putInt("Noot1",Not1);
                    editor.commit();
                }
                if(nameCode >300 && nameCode< 400)
                {
                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference("Notification/300");
                    myRef.child(String.valueOf(nameCode*10+Not2)).setValue(Data);
                    Toast.makeText(getApplicationContext(), "Notification send", Toast.LENGTH_SHORT).show();
                    Not2++;
                    SharedPreferences.Editor editor = sharedp.edit();
                    editor.putInt("Noot2",Not2);
                    editor.commit();
                }
                if(nameCode > 400)
                {
                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference("Notification/400");
                    myRef.child(String.valueOf(nameCode*10+Not3)).setValue(Data);
                    Toast.makeText(getApplicationContext(), "Notification send", Toast.LENGTH_SHORT).show();
                    Not3++;
                    SharedPreferences.Editor editor = sharedp.edit();
                    editor.putInt("Noot3",Not3);
                    editor.commit();
                }
            }
        });

    }
}

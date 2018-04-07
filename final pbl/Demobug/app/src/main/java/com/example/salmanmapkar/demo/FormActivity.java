package com.example.salmanmapkar.demo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {
    ArrayAdapter<CharSequence> adapter_year;
    ArrayAdapter<CharSequence> adapter_branch;
    ArrayAdapter<CharSequence> adapter_div;
    int nameCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        final Button sendNotification = (Button) findViewById(R.id.sendNotifi);
        final Spinner spinner_year = (Spinner)findViewById(R.id.spfYear);
        final Spinner spinner_branch = (Spinner)findViewById(R.id.spfBranch);
        final Spinner spinner_div = (Spinner)findViewById(R.id.spfDiv);
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button sub = (Button) findViewById(R.id.btSubmitForm);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("mp",0);
                int userCode  = preferences.getInt("userCode",0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("userCode",nameCode);
                editor.commit();
                Toast.makeText(getApplicationContext(), "Your Data has been stored!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

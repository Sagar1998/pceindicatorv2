package com.example.salmanmapkar.demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.storage.FirebaseStorage;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NotificationActivity extends AppCompatActivity {
    Button btSendnt;
    SharedPreferences sharedp2;
    ArrayList<String> list;
    String string2 = "Welcome";
    SharedPreferences.Editor editor;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ListView lv;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        sharedp2 = getSharedPreferences("mp",0);
        editor = sharedp2.edit();
        Log.d("My Token",Common.currentToken);
        lv = (ListView) findViewById(R.id.notListview);
        btSendnt = (Button) findViewById(R.id.sendnt);
        btSendnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(),sendNotification.class);
                startActivity(intent2);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        if(!email.contains("@mes.ac.in") || email != "salmansmapkar@gmail.com")
        {
            btSendnt.setVisibility(View.VISIBLE);
        }
        RetrieveData();

    }


    private void RetrieveData() {
        database = FirebaseDatabase.getInstance();
        SharedPreferences sh = getSharedPreferences("mp" , 0);
        int userCode = sh.getInt("userCode",0);
        if(userCode > 200 && userCode< 300)
        {
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("Notification/200");
            myRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String message = dataSnapshot.getValue(String.class);
                    list = new ArrayList<String>(Arrays.asList(message));
                    setStringArrayPref(getApplicationContext(), "key", list);
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if(userCode >300 && userCode< 400)
        {
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("Notification/300");
            myRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String message = dataSnapshot.getValue(String.class);
                    list = new ArrayList<String>(Arrays.asList(message));
                    setStringArrayPref(getApplicationContext(), "key", list);
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        if(userCode > 400)
        {
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("Notification/400");
            myRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String message = dataSnapshot.getValue(String.class);
                    list = new ArrayList<String>(Arrays.asList(message));
                    setStringArrayPref(getApplicationContext(), "key", list);
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        ArrayList<String> st = getStringArrayPref(getApplicationContext(), "key");
        st.add("Welcome");
        adapter = new ArrayAdapter<String>(this,R.layout.layout_listview,st);
        lv.setAdapter(adapter);



    }

    public static void setStringArrayPref(Context context, String key, ArrayList<String> values) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            a.put(values.get(i));
        }
        if (!values.isEmpty()) {
            editor.putString(key, a.toString());
        } else {
            editor.putString(key, null);
        }
        editor.commit();
    }

    public static ArrayList<String> getStringArrayPref(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString(key, null);
        ArrayList<String> urls = new ArrayList<String>();
        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);
                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    urls.add(url);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }

}

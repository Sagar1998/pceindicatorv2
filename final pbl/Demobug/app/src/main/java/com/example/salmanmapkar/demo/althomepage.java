package com.example.salmanmapkar.demo;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class althomepage extends AppCompatActivity {

    public Button timetable;
    public Button maps;
    public Button result;
    public Button aboutus;
    public Button faculty;
    private DrawerLayout mDrwr;
    private ActionBarDrawerToggle Dtoggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_althomepage);
        timetable = (Button) findViewById(R.id.timetable);
        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toy = new Intent(getApplicationContext(), timetable.class);
                startActivity(toy);
            }
        });
        maps = (Button) findViewById(R.id.maps);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toy = new Intent(getApplicationContext(), timetable.class);
                startActivity(toy);
            }
        });

        Button result = (Button) findViewById(R.id.result);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.piitexamresults.piit.ac.in/"));
                startActivity(viewIntent);
            }
        });
        Button faculty = (Button) findViewById(R.id.faculty);
        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://pce.ac.in/faculty/faculty-directory/"));
                startActivity(viewIntent);
            }
        });
        Button NotificationButton = (Button)findViewById(R.id.notifcation);
        NotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivity( new Intent(getBaseContext(),NotificationActivity.class));
            }
        });
        Button menuButton = (Button)findViewById(R.id.menu_button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                mDrwr.openDrawer(Gravity.START);
            }
        });
        mDrwr = (DrawerLayout) findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()){

                            case R.id.logout:
                                FirebaseAuth.getInstance().signOut();
                                finish();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                break;

                            case R.id.contactus:
                                Intent intent1 = new Intent(getApplicationContext(),contactus.class);
                                startActivity(intent1);
                                break;

                            case R.id.aboutus:
                                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://pce.ac.in/about-us/contact-us/"));
                                startActivity(viewIntent);
                                break;

                            case R.id.setng:
                                Intent intent2 = new Intent(getApplicationContext(),FormActivity.class);
                                startActivity(intent2);
                                break;

                            case R.id.chat:
                                Intent intent3 = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent3);
                                break;

                        }
                        mDrwr.closeDrawers();
                        return true;
                    }
                });
    }
}

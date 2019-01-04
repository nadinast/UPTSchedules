package com.apps.uptschedules;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.uptschedules.model.FacultyClass;
import com.apps.uptschedules.model.Option;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainScheduleActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // firebase
    private DatabaseReference databaseReference;
    private ValueEventListener databaseListener;

    ListView listSubjects;
    List<FacultyClass> classes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<Option> op1 = new ArrayList<>();
        op1.add(new Option("opday", "ophours", "opRoom"));
        FacultyClass fc1 = new FacultyClass("CP", "day", "hours", "name", "room", "website", op1);

        classes.add(fc1);

        List<Option> op2 = new ArrayList<>();
        op1.add(new Option("opday", "ophours", "opRoom"));
        FacultyClass fc2 = new FacultyClass("CP", "day", "hours", "name", "room", "website", op2);

        classes.add(fc2);

        List<Option> op3 = new ArrayList<>();
        op1.add(new Option("opday", "ophours", "opRoom"));
        FacultyClass fc3 = new FacultyClass("CP", "day", "hours", "name", "room", "website", op3);

        classes.add(fc3);

        List<Option> op4 = new ArrayList<>();
        op1.add(new Option("opday", "ophours", "opRoom"));
        FacultyClass fc4 = new FacultyClass("CP", "day", "hours", "name", "room", "website", op4);

        classes.add(fc4);

        List<Option> op5 = new ArrayList<>();
        op1.add(new Option("opday", "ophours", "opRoom"));
        FacultyClass fc5 = new FacultyClass("CP", "day", "hours", "name", "room", "website", op5);

        classes.add(fc5);

        List<Option> op6 = new ArrayList<>();
        op1.add(new Option("opday", "ophours", "opRoom"));
        FacultyClass fc6 = new FacultyClass("CP", "day", "hours", "name", "room", "website", op6);

        classes.add(fc6);

        List<Option> op7 = new ArrayList<>();
        op1.add(new Option("opday", "ophours", "opRoom"));
        FacultyClass fc7 = new FacultyClass("CP", "day", "hours", "name", "room", "website", op7);

        classes.add(fc7);


        listSubjects = (ListView) findViewById(R.id.listSubjects);
        final MyAdapter adapter = new MyAdapter(this, R.layout.fragment_item, classes);
        listSubjects.setAdapter(adapter);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();


/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Context context = getApplicationContext();
            CharSequence text;
            int duration = Toast.LENGTH_SHORT;
            text = "clicked import menu button";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        } else if (id == R.id.nav_gallery) {
            Context context = getApplicationContext();
            CharSequence text;
            int duration = Toast.LENGTH_SHORT;
            text = "clicked gallery menu button";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else if (id == R.id.nav_slideshow) {
            Context context = getApplicationContext();
            CharSequence text;
            int duration = Toast.LENGTH_SHORT;
            text = "clicked slideshow menu button";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

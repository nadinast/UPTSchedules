package com.apps.uptschedules;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.apps.uptschedules.model.FacultyClass;
import com.apps.uptschedules.model.Lab;
import com.apps.uptschedules.model.User;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class MainScheduleActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int RC_SIGN_IN = 123;

    DatabaseReference dbRef;
    FirebaseDatabase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createSignInIntent();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Signed out", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                signOut();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Firebase initialization
        firebase = FirebaseDatabase.getInstance();
        dbRef = firebase.getReference();
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

        if (id == R.id.nav_my_schedule) {
            // Handle the camera action
        } else if (id == R.id.nav_poll) {

        } else if (id == R.id.nav_locations) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void clicked(View view){
        addUserDBListener();
        addFacultyClassDBListener();
        addLabsDBListener();
    }

    private void addUserDBListener(){
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    User user = dataSnapshot.getValue(User.class);
                    Log.i("DBFirebase OK", user.toString());
                }
                catch(Exception e){
                    Log.e("DBFirebase Error", e.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("DBFirebase OnCancelled", databaseError.toException().toString());
            }
        };
        dbRef.child("users").child("0").addValueEventListener(userListener);
    }

    private void addFacultyClassDBListener(){
        ValueEventListener facultyClassListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    FacultyClass facultyClass = dataSnapshot.getValue(FacultyClass.class);
                    Log.i("DBFirebase OK", facultyClass.toString());
                }
                catch(Exception e){
                    Log.e("DBFirebase Error", e.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("DBFirebase OnCancelled", databaseError.toException().toString());
            }
        };
        dbRef.child("classes").child("0").addValueEventListener(facultyClassListener);
    }

    private void addLabsDBListener(){
        ValueEventListener labsListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    Lab lab = dataSnapshot.getValue(Lab.class);
                    Log.i("DBFirebase OK", lab.toString());
                }
                catch(Exception e){
                    Log.e("DBFirebase Error", e.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("DBFirebase OnCancelled", databaseError.toException().toString());
            }
        };
        dbRef.child("classes").child("0").child("labs").addValueEventListener(labsListener);
    }

    public void createSignInIntent(){
        List<AuthUI.IdpConfig> providers = Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build());
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }

    public void signOut(){
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i("SIGN_OUT","signed out");
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
}

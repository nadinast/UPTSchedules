package com.apps.uptschedules;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.apps.uptschedules.model.Classes;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.apps.uptschedules.model.Lab;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class MainScheduleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseReference dbRef;
    FirebaseDatabase firebase;

    ListView listSubjects;
    ArrayList<Classes> classes = new ArrayList<>();
    List<Long> courseIds =  new ArrayList<>();
    List<Classes> allClasses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listSubjects = (ListView) findViewById(R.id.listSubjects);
        final ClassesAdapter adapter = new ClassesAdapter(this, R.layout.fragment_item, classes);
        listSubjects.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Signed out", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View layoutView = navigationView.getHeaderView(0);
        TextView usernameTextView = (TextView) layoutView.findViewById(R.id.usernameTextView);

        usernameTextView.setText(AppState.getLoggedInUser().getDisplayName());

        //Firebase initialization
        firebase = FirebaseDatabase.getInstance();
        dbRef = firebase.getReference();

        if(AppState.isIsNewUser()){
            AppState.setIsNewUser(false);
            setCoursesForLoggedInUser();
        }
        addUserCourseDBListener(AppState.getLoggedInUser().getUid(), adapter);
        addFacultyClassDBListener(AppState.getLoggedInUser().getUid(), adapter);
        //addLabsDBListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    private void setCoursesForLoggedInUser() {
        List<Long> courses = new ArrayList<>();
        courses.add((long)0);
        courses.add((long)1);
        dbRef.child("userCourses").child(AppState.getLoggedInUser().getUid()).setValue(courses);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        int id = item.getItemId();

        if (id == R.id.nav_my_schedule) {

        } else if (id == R.id.nav_poll) {
            Intent pollIntent = new Intent(this, LabsEnrollmentActivity.class);
            pollIntent.putParcelableArrayListExtra("classes", classes);
            startActivity(pollIntent);
        } else if (id == R.id.nav_locations) {
            startActivity(new Intent(this, LocationsActivity.class));
        } else if (id == R.id.nav_settings){
            startActivity(new Intent(this, SettingsActivity.class));
        } else if(id == R.id.nav_sign_out){
            signOut();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addUserCourseDBListener(String uid, final ClassesAdapter adapter) {
        dbRef.child("userCourses").child(uid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                try {
                    Long courseId = dataSnapshot.getValue(Long.class);
                    courseIds.add(courseId);
                    if(allClasses.size() > courseId){
                        classes.add(allClasses.get(courseId.intValue()));
                        adapter.notifyDataSetChanged();
                    }
                    //Log.i("DBFirebase OK", courseId.toString());
                } catch (Exception e) {
                    Log.e("DBFirebase Error", e.toString());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Long courseId = dataSnapshot.getValue(Long.class);
                for(Long course : courseIds)
                    if(course.equals(courseId)) {
                        courseIds.remove(course);
                        classes.remove(courseId.intValue());
                        adapter.notifyDataSetChanged();
                    }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addFacultyClassDBListener(final String uid, final ClassesAdapter adapater) {
        dbRef.child("classes").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                try {
                    Classes oneClass = dataSnapshot.getValue(Classes.class);
                    allClasses.add(oneClass);
                    long idOfClass = allClasses.size() - 1;
                    oneClass.setId((int)idOfClass);
                    if(courseIds.contains(idOfClass)) {
                        classes.add(oneClass);
                        adapater.notifyDataSetChanged();
                    }
                   //Log.i("DBFirebase OK", oneClass.getId()+ "");

                } catch (Exception e) {
                    Log.e("DBFirebase Error", e.toString());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Classes oneClass = dataSnapshot.getValue(Classes.class);
                int courseIdOfRemovedClass = allClasses.indexOf(oneClass);
                allClasses.remove(oneClass);
                classes.remove(courseIdOfRemovedClass);
                courseIds.remove(courseIdOfRemovedClass);
                dbRef.child("userCourses").child(uid).child(courseIdOfRemovedClass + "").removeValue();
                adapater.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addLabsDBListener() {
        ValueEventListener labsListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    Lab lab = dataSnapshot.getValue(Lab.class);
                    Log.i("DBFirebase OK", lab.toString());
                } catch (Exception e) {
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

    public void signOut(){
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(MainScheduleActivity.this, SignInActivity.class));
                        AppState.setLoggedInUser(null);
                        MainScheduleActivity.this.finish();
                    }
                });
    }
}

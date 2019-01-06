package com.apps.uptschedules;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.apps.uptschedules.model.Option;

import java.util.ArrayList;
import java.util.List;

public class LabsEnrollmentActivity extends AppCompatActivity {

    RecyclerView recyclerViewLLabOptions;
    List<Option> labOptions= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labs_enrollment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_labs_enrollment);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                onBackPressed();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                onBackPressed();
            }
        });

        labOptions.add(new Option("Monday", "12-14", "B512"));
        labOptions.add(new Option("Monday", "12-14", "B512"));
        labOptions.add(new Option("Monday", "12-14", "B512"));
        labOptions.add(new Option("Monday", "12-14", "B512"));
        labOptions.add(new Option("Monday", "12-14", "B512"));
        labOptions.add(new Option("Monday", "12-14", "B512"));
        labOptions.add(new Option("Monday", "12-14", "B512"));
        labOptions.add(new Option("Monday", "12-14", "B512"));
        labOptions.add(new Option("Monday", "12-14", "B512"));
        labOptions.add(new Option("Monday", "12-14", "B512"));
        labOptions.add(new Option("Monday", "12-14", "B512"));
        labOptions.add(new Option("Monday", "12-14", "B512"));
        labOptions.add(new Option("Monday", "12-14", "B512"));
        labOptions.add(new Option("Monday", "12-14", "B512"));
        labOptions.add(new Option("Monday", "12-14", "B512"));
        labOptions.add(new Option("Monday", "12-14", "B512"));
        labOptions.add(new Option("Monday", "12-14", "B512"));
        labOptions.add(new Option("Monday", "12-14", "B512"));
        labOptions.add(new Option("Monday", "12-14", "B512"));
        labOptions.add(new Option("Monday", "12-14", "B512"));

        recyclerViewLLabOptions = (RecyclerView) findViewById(R.id.listLabOptions);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerViewLLabOptions.setLayoutManager(mLayoutManager);
        recyclerViewLLabOptions.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        final LabOptionsAdapter adapter = new LabOptionsAdapter(labOptions);
        recyclerViewLLabOptions.setAdapter(adapter);
    }
}

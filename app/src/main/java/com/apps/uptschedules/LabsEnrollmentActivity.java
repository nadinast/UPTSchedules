package com.apps.uptschedules;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.apps.uptschedules.model.Classes;
import com.apps.uptschedules.model.Lab;
import com.apps.uptschedules.model.Option;
import com.apps.uptschedules.model.UILabOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class LabsEnrollmentActivity extends AppCompatActivity {

    RecyclerView recyclerViewLLabOptions;
    ArrayList<Classes> classes;

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

        classes = getIntent().getParcelableArrayListExtra("classes");
        Log.i("ENROLLMENTOK", classes.toString());
        final ArrayList<UILabOptions> labOptions = new ArrayList<>();
        for(Classes oneClass : classes) {
            labOptions.add(new UILabOptions(oneClass.getLabs().getOptions(), oneClass.getCourse().getAbbreviation(), oneClass.getId()));
        }

        recyclerViewLLabOptions = (RecyclerView) findViewById(R.id.listLabOptions);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerViewLLabOptions.setLayoutManager(mLayoutManager);
        recyclerViewLLabOptions.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        final LabOptionsAdapter adapter = new LabOptionsAdapter(labOptions, getApplicationContext(), findViewById(R.id.coordinator_layout_labs_enrollment));
        recyclerViewLLabOptions.setAdapter(adapter);
    }
}

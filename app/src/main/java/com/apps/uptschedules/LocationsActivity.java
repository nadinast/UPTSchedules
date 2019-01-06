package com.apps.uptschedules;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class LocationsActivity extends AppCompatActivity {

    RecyclerView recyclerViewLocations;
    List<Locations> locations= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_locations);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                onBackPressed();
            }
        });


        Locations l1 = new Locations("Rectorship UPT","Piata Victoriei, nr. 2",R.drawable.rectorat);
        Locations l3 = new Locations("ASPC","Str. Petru Ramneantu, nr. 2a",R.drawable.aspc_front);
        Locations l2 = new Locations("Electro","Bvd. Vasile Parvan, nr. 2",R.drawable.electro);
        Locations l4 = new Locations("Chimie Centru","Str. Carol Telbisz, nr. 2",R.drawable.chimie_centru);
        Locations l5 = new Locations("SPM","Str. Sfanta Rozalia, inside the UPT campus",R.drawable.spm);
        Locations l6 = new Locations("Constructii","Str. Traian Lalescu, nr. 2", R.drawable.constructii);
        Locations l7 = new Locations("Chimie","Bvd. Vasile Pârvan, nr. 6", R.drawable.chimie);
        Locations l8 = new Locations("Mecanica","Bvd. Mihai Viteazu, nr. 1", R.drawable.mecanica);
        Locations l9 = new Locations("Library","Bvd. Vasile Pârvan, nr. 2", R.drawable.bcupt);

        locations.add(l1);
        locations.add(l2);
        locations.add(l3);
        locations.add(l4);
        locations.add(l5);
        locations.add(l6);
        locations.add(l7);
        locations.add(l8);
        locations.add(l9);

        recyclerViewLocations = (RecyclerView) findViewById(R.id.listLocations);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerViewLocations.setLayoutManager(mLayoutManager);

        final LocationsAdapter adapter = new LocationsAdapter(locations, this);
        recyclerViewLocations.setAdapter(adapter);

    }
}

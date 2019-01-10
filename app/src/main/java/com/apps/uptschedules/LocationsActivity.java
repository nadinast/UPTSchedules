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

        List<Locations> locations= new ArrayList<>();
        locations.add(new Locations("Rectorat UPT","Piata Victoriei, nr. 2",R.drawable.rectorat));
        locations.add(new Locations("ASPC","Str. Petru Ramneantu, nr. 2a",R.drawable.aspc_front));
        locations.add(new Locations("Electro","Bvd. Vasile Parvan, nr. 2",R.drawable.electro));
        locations.add(new Locations("Chimie Centru","Str. Carol Telbisz, nr. 2",R.drawable.chimie_centru));
        locations.add(new Locations("SPM","Str. Sfanta Rozalia, inside the UPT campus",R.drawable.spm));
        locations.add(new Locations("Constructii","Str. Traian Lalescu, nr. 2", R.drawable.constructii));
        locations.add(new Locations("Chimie","Bvd. Vasile Pârvan, nr. 6", R.drawable.chimie));
        locations.add(new Locations("Mecanica","Bvd. Mihai Viteazu, nr. 1", R.drawable.mecanica));
        locations.add(new Locations("Library","Bvd. Vasile Pârvan, nr. 2", R.drawable.bcupt));

        final RecyclerView recyclerViewLocations = (RecyclerView) findViewById(R.id.listLocations);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerViewLocations.setLayoutManager(mLayoutManager);

        final LocationsAdapter adapter = new LocationsAdapter(locations, this);
        recyclerViewLocations.setAdapter(adapter);

    }
}

package com.apps.uptschedules;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.apps.uptschedules.R;
import com.apps.uptschedules.model.FacultyClass;

import java.util.ArrayList;
import java.util.List;

public class LocationsActivity extends AppCompatActivity {

    ListView listLocations;
    List<Locations> locations= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_locations);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                onBackPressed();
            }
        });


        Locations l1 = new Locations("ASPC","sajdasldasd",R.drawable.aspc_front);
        Locations l2 = new Locations("B512","B512address",R.drawable.aspc_front);
        Locations l3 = new Locations("A101","str Coriolan",R.drawable.aspc_front);
        Locations l4 = new Locations("SPM","str. Brediceanu",R.drawable.aspc_front);
//        Locations l1 = new Locations("ASPC","sajdasldasd");
//        Locations l2 = new Locations("B512","B512address");
//        Locations l3 = new Locations("A101","str Coriolan");
//        Locations l4 = new Locations("SPM","str. Brediceanu");

        locations.add(l1);
        locations.add(l2);
        locations.add(l3);
        locations.add(l4);

        Toast.makeText(getApplicationContext(),"loaded locations", Toast.LENGTH_LONG);

        listLocations = (ListView) findViewById(R.id.listLocations);
        final LocationsAdapter adapter = new LocationsAdapter(this, R.layout.locations_item, locations);
        listLocations.setAdapter(adapter);

    }
}

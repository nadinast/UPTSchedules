package com.apps.uptschedules;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.uptschedules.model.FacultyClass;

import java.util.List;

public class LocationsAdapter extends ArrayAdapter<Locations> {

    private Context context;
    private List<Locations> locations;
    private int layoutResID;

    public LocationsAdapter(@NonNull Context context, int resource, @NonNull List<Locations> locations) {
        super(context, resource, locations);

        this.context = context;
        this.layoutResID = resource;
        this.locations = locations;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LocationsAdapter.ItemHolder itemHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            itemHolder = new LocationsAdapter.ItemHolder();
            view = inflater.inflate(layoutResID, parent, false);
            itemHolder.tName= (TextView) view.findViewById(R.id.locationName);
            itemHolder.tAddress = (TextView) view.findViewById(R.id.locationAddress);
            itemHolder.iImage= (ImageView) view.findViewById(R.id.locationImage);

            view.setTag(itemHolder);
        } else {
            itemHolder = (LocationsAdapter.ItemHolder) view.getTag();
        }

        final Locations sItem = locations.get(position);

        itemHolder.tName.setText(sItem.name);
        itemHolder.tAddress.setText(sItem.address);
        itemHolder.iImage.setImageResource(sItem.imageId);

        return view;

    }

    private static class ItemHolder {
        TextView tName;
        TextView tAddress;
        ImageView iImage;
    }
}

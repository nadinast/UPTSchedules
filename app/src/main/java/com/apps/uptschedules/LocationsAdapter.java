package com.apps.uptschedules;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.uptschedules.model.FacultyClass;

import java.util.List;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.ItemHolder> {
    private List<Locations> locations;
    private Context context;

    public LocationsAdapter(@NonNull List<Locations> locations, Context context) {
        this.locations = locations;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locations_item, parent, false);
        return new ItemHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.tName.setText(locations.get(position).getName());
        holder.tAddress.setText(locations.get(position).getAddress());
        holder.iImage.setImageResource(locations.get(position).getImageId());
        holder.iDirectionsIcon.setTag(position);
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder{
        public TextView tName;
        public TextView tAddress;
        public ImageView iImage;
        public ImageView iDirectionsIcon;
        public final Context context;

        public ItemHolder(View itemView, Context receivedContext) {
            super(itemView);
            tName = itemView.findViewById(R.id.locationName);
            tAddress = itemView.findViewById(R.id.locationAddress);
            iImage = itemView.findViewById(R.id.locationImage);
            iDirectionsIcon = itemView.findViewById(R.id.maps_icon);
            this.context = receivedContext;

            iDirectionsIcon.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = (int) v.getTag();
                    String latLong  = AppState.getLatLong(position);
                    Uri gmmIntentUri = Uri.parse(latLong);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    context.startActivity(mapIntent);
                }
            });
        }
    }
}

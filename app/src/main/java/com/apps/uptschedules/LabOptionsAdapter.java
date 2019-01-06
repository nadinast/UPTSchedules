package com.apps.uptschedules;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apps.uptschedules.model.Option;

import java.util.List;

class LabOptionsAdapter extends RecyclerView.Adapter<LabOptionsAdapter.LabOptionsViewHolder> {
    List<Option> labOptions;

    public LabOptionsAdapter(List<Option> options){
        this.labOptions = options;
    }

    @NonNull
    @Override
    public LabOptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lab_option_item, parent, false);
        return new LabOptionsAdapter.LabOptionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LabOptionsViewHolder holder, int position) {
        holder.tView.setText(labOptions.get(position).getHours());
    }

    @Override
    public int getItemCount() {
        return labOptions.size();
    }

    public static class LabOptionsViewHolder extends RecyclerView.ViewHolder{
        public TextView tView;

        public LabOptionsViewHolder(View itemView) {
            super(itemView);
            tView = itemView.findViewById(R.id.textView);
        }
    }
}

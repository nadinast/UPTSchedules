package com.apps.uptschedules;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.apps.uptschedules.model.Option;
import com.apps.uptschedules.model.UILabOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

class LabOptionsAdapter extends RecyclerView.Adapter<LabOptionsAdapter.LabOptionsViewHolder> {
    List<UILabOptions> labOptions;
    final Context context;
    HashMap<String, Integer> radioButtonIds = new HashMap<>();
    final View layoutView;
    final DatabaseReference dbRef;

    public LabOptionsAdapter(List<UILabOptions> options, Context context, View view){
        this.labOptions = options;
        this.context = context;
        this.layoutView = view;
        FirebaseDatabase firebase = FirebaseDatabase.getInstance();
        dbRef = firebase.getReference();
    }

    @NonNull
    @Override
    public LabOptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lab_option_item, parent, false);
        return new LabOptionsAdapter.LabOptionsViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull LabOptionsViewHolder holder, final int position) {
        holder.tView.setText(labOptions.get(position).getName());
        final List<Option> options = labOptions.get(position).getOptions();
        int optionId = 0;
        for(Option option : options){
            RadioButton button = new RadioButton(context);
            int id = View.generateViewId();
            button.setId(id);
            button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            String optionInfo = option.getDay() +  ", " + option.getHours() + ", " + option.getRoom();
            button.setText(optionInfo);
            radioButtonIds.put(optionInfo, optionId);
            if(option.getCapacity() == 0)
                button.setEnabled(false);
            holder.radioGroup.addView(button);
            optionId++;
        }

        final RadioGroup radioGroup = holder.radioGroup;
        final Button confirmButton = holder.confirmButton;

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int checkedId = radioGroup.getCheckedRadioButtonId();
                if(checkedId == -1) {
                    Snackbar mySnackbar = Snackbar.make(layoutView, "Please select a lab slot before confirming", Snackbar.LENGTH_LONG);
                    mySnackbar.show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Confirm your choice!");
                    builder.setMessage("Are you sure you want to proceed with the selection?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dbRef.child("enrolledUsers").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.hasChild(AppState.getLoggedInUser().getUid())) {
                                        if (dataSnapshot.child(AppState.getLoggedInUser().getUid()).hasChild("courseId" + labOptions.get(position).getCourseId())) {
                                            confirmButton.setEnabled(false);
                                            Snackbar mySnackbar = Snackbar.make(layoutView, "You already chose a slot for this lab", Snackbar.LENGTH_LONG);
                                            mySnackbar.show();
                                            return;
                                        }
                                    }
                                    RadioButton radioButton = radioGroup.findViewById(checkedId);
                                    int optionId = radioButtonIds.get(radioButton.getText().toString());
                                    dbRef.child("enrolledUsers").child(AppState.getLoggedInUser().getUid())
                                            .child("courseId" + labOptions.get(position).getCourseId())
                                            .child("option")
                                            .setValue(optionId);
                                    int optionCapacity = options.get(optionId).getCapacity().intValue() - 1;
                                    options.get(optionId).setCapacity((long)optionCapacity);
                                    if(optionCapacity == 0)
                                        radioButton.setEnabled(false);
                                    dbRef.child("classes")
                                            .child(labOptions.get(position).getCourseId()+"")
                                            .child("labs")
                                            .child("options")
                                            .child(optionId + "")
                                            .child("capacity")
                                            .setValue(optionCapacity);
                                    Snackbar mySnackbar = Snackbar.make(layoutView, "Your slot choice was confirmed", Snackbar.LENGTH_LONG);
                                    mySnackbar.show();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Snackbar mySnackbar = Snackbar.make(layoutView, "Selected lab slot not cofirmed", Snackbar.LENGTH_LONG);
                            mySnackbar.show();
                        }
                    });
                    builder.show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return labOptions.size();
    }


    public static class LabOptionsViewHolder extends RecyclerView.ViewHolder {
        public TextView tView;
        public RadioGroup radioGroup;
        public Button confirmButton;
        public Context context;

        public LabOptionsViewHolder(View itemView, final Context context) {
            super(itemView);
            this.context = context;
            tView = itemView.findViewById(R.id.courseName);
            radioGroup = itemView.findViewById(R.id.options_radio_group);
            confirmButton = itemView.findViewById(R.id.confirmButton);
        }
    }

}

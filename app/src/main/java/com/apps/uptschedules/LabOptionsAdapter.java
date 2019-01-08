package com.apps.uptschedules;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.apps.uptschedules.model.Option;
import com.apps.uptschedules.model.UILabOptions;

import java.util.HashMap;
import java.util.List;

class LabOptionsAdapter extends RecyclerView.Adapter<LabOptionsAdapter.LabOptionsViewHolder> {
    List<UILabOptions> labOptions;
    final Context context;
    HashMap<String, Integer> radioButtonIds = new HashMap<>();


    public LabOptionsAdapter(List<UILabOptions> options, Context context){
        this.labOptions = options;
        this.context = context;
    }

    @NonNull
    @Override
    public LabOptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lab_option_item, parent, false);
        return new LabOptionsAdapter.LabOptionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LabOptionsViewHolder holder, int position) {
        holder.tView.setText(labOptions.get(position).getName());
        List<Option> options = labOptions.get(position).getOptions();
        for(Option option : options){
            RadioButton button = new RadioButton(context);
            int id = View.generateViewId();
            button.setId(id);
            button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            String optionInfo = option.getDay() +  ", " + option.getHours() + ", " + option.getRoom();
            button.setText(optionInfo);
            radioButtonIds.put(optionInfo, id);
            holder.radioGroup.addView(button);
        }
    }

    @Override
    public int getItemCount() {
        return labOptions.size();
    }

    public static class LabOptionsViewHolder extends RecyclerView.ViewHolder{
        public TextView tView;
        public RadioGroup radioGroup;

        public LabOptionsViewHolder(View itemView) {
            super(itemView);
            tView = itemView.findViewById(R.id.textView);
            radioGroup = itemView.findViewById(R.id.options_radio_group);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    RadioButton radioButton = radioGroup.findViewById(i);
                    Log.e("RadioGroup", radioButton.getText().toString());

                }
            });
        }
    }
}

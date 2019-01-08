package com.apps.uptschedules;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apps.uptschedules.model.Classes;
import com.apps.uptschedules.model.Course;
import com.apps.uptschedules.model.Lab;

import java.util.List;

public class ClassesAdapter extends ArrayAdapter<Classes> {

    private Context context;
    private List<Classes> subjects;
    private int layoutResID;

    public ClassesAdapter(@NonNull Context context, int layoutResID, @NonNull List<Classes> subjects) {
        super(context, layoutResID, subjects);

        this.context = context;
        this.layoutResID = layoutResID;
        this.subjects = subjects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ItemHolder itemHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            itemHolder = new ItemHolder();
            view = inflater.inflate(layoutResID, parent, false);
            itemHolder.tTimeInterval = (TextView) view.findViewById(R.id.time_interval);
            itemHolder.tSubjectName = (TextView) view.findViewById(R.id.subject_name);
            itemHolder.tSubjectSession = (TextView) view.findViewById(R.id.subject_session);
            itemHolder.tSessionRoom = (TextView) view.findViewById(R.id.session_room);
            itemHolder.tSessionProf = (TextView) view.findViewById(R.id.session_prof);

            view.setTag(itemHolder);
        } else {
            itemHolder = (ItemHolder) view.getTag();
        }

        final Classes sItem = subjects.get(position);

        Course course = sItem.getCourse();

        itemHolder.tTimeInterval.setText(course.getHours());
        itemHolder.tSubjectName.setText(course.getAbbreviation());
        itemHolder.tSubjectSession.setText("Course");
        itemHolder.tSessionRoom.setText(course.getRoom());
        itemHolder.tSessionProf.setText("Prof. " + course.getProfName());

        return view;
    }

    private static class ItemHolder {
        TextView tTimeInterval;
        TextView tSubjectName;
        TextView tSubjectSession;
        TextView tSessionRoom;
        TextView tSessionProf;
    }
}

package com.apps.uptschedules;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apps.uptschedules.model.FacultyClass;

import java.util.List;

public class MyAdapter extends ArrayAdapter<FacultyClass> {

    private Context context;
    private List<FacultyClass> subjects;
    private int layoutResID;

    public MyAdapter(@NonNull Context context, int layoutResID, @NonNull List<FacultyClass> subjects) {
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

        final FacultyClass sItem = subjects.get(position);

        itemHolder.tTimeInterval.setText(sItem.hours);
        itemHolder.tSubjectName.setText(sItem.abbreviation);
//        itemHolder.tSubjectSession.setText(sItem.subjectSession);
        itemHolder.tSessionRoom.setText(sItem.room);
//        itemHolder.tSessionProf.setText(sItem.sessionProf);

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

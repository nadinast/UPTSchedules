package com.apps.uptschedules;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.apps.uptschedules.model.ClassType;
import com.apps.uptschedules.model.Classes;
import com.apps.uptschedules.model.Course;
import com.apps.uptschedules.model.CourseClassType;
import com.apps.uptschedules.model.Lab;
import com.apps.uptschedules.model.LabClassType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ExpandableClassListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> daysList;
    //private HashMap<String, List<Classes>> classesMap;
    private HashMap<String, List<ClassType>> classesTypeMap;
    //private HashMap<String, Integer> labOptions;

    public ExpandableClassListAdapter(Context context, List<String> daysList,  HashMap<String, List<ClassType>> classesTypeMap) {
        this.context = context;
        this.daysList = daysList;
        this.classesTypeMap = classesTypeMap;
    }

    @Override
    public int getGroupCount() {
        return this.daysList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.classesTypeMap.get(this.daysList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.daysList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.classesTypeMap.get(daysList.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup viewGroup) {
        String headerTitle = (String) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_group, null);
        }
        TextView listHeader = (TextView) view.findViewById(R.id.listHeader);
        listHeader.setText(headerTitle);

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {
         ClassType classType = (ClassType) getChild(groupPosition, childPosition);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_item, null);
        }

        TextView tTimeInterval = (TextView) view.findViewById(R.id.time_interval);
        TextView  tSubjectName = (TextView) view.findViewById(R.id.subject_name);
        TextView  tSubjectSession = (TextView) view.findViewById(R.id.subject_session);
        TextView  tSessionRoom = (TextView) view.findViewById(R.id.session_room);
        TextView  tSessionProf = (TextView) view.findViewById(R.id.session_prof);

        if(classType.getType().equals("Course")) {
            tTimeInterval.setText(((CourseClassType)classType).hours);
            tSubjectName.setText(((CourseClassType)classType).abbreviation);
            tSubjectSession.setText(classType.getType());
            tSessionRoom.setText(((CourseClassType)classType).room);
            tSessionProf.setText("Prof. " + ((CourseClassType)classType).profName);
        }else
            if(classType.getType().equals("Lab")){
                tTimeInterval.setText(((LabClassType)classType).hours);
                tSubjectName.setText(((LabClassType)classType).abbreviation);
                tSubjectSession.setText(classType.getType());
                tSessionRoom.setText(((LabClassType)classType).room);
                tSessionProf.setText("");
        }

        return view;

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}

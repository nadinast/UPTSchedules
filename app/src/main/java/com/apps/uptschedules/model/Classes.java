package com.apps.uptschedules.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Classes implements Parcelable {
    public Lab labs;
    public Course course;

    public Classes() {

    }

    public Classes(Lab labs, Course course) {
        this.labs = labs;
        this.course = course;
    }

    public Lab getLabs() {
        return labs;
    }

    public void setLabs(Lab labs) {
        this.labs = labs;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "labs=" + labs +
                ", course=" + course +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classes classes = (Classes) o;
        return course.getAbbreviation().equals(classes.getCourse().getAbbreviation())
                && course.getWebsite().equals(classes.getCourse().getWebsite()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(labs, course);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(labs);
        parcel.writeSerializable(course);
    }

    public static final Parcelable.Creator<Classes> CREATOR = new Parcelable.Creator<Classes>() {
        public Classes createFromParcel(Parcel in) {
            return new Classes(in);
        }

        public Classes[] newArray(int size) {
            return new Classes[size];
        }
    };

    private Classes(Parcel in) {
        labs = (Lab)in.readSerializable();
        course = (Course)in.readSerializable();
    }
}

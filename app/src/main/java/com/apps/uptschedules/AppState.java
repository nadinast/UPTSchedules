package com.apps.uptschedules;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class AppState {
    private static FirebaseUser loggedInUser = null;
    private static boolean isNewUser = false;
    private static String[] locationsLatLong = {
            "geo:0,0?q=Universitatea+Politehnica+Timisoara",
            "geo:0,0?q=Facultatea+de+Electronică+și+Telecomunicații",
            "geo:0,0?q=Atelier+Școlar+pentru+Proiectare+și+Cercetare+(ASPC)",
            "geo:0,0?q=Strada+Carol+Telbisz+2,+Timișoara",
            "geo:0,0?q=Cămin+Studențesc+1MV",
            "geo:0,0?q=Facultatea+de+Construcții",
            "geo:0,0?q=Facultatea+de+Chimie+Industrială+și+Ingineria+Mediului",
            "geo:0,0?q=Bulevardul+Mihai+Viteazu+1,+Timișoara",
            "geo:0,0?q=Biblioteca+Centrală+a+Universității+Politehnica+Timișoara"
    };

    public static FirebaseUser getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(FirebaseUser loggedInUser) {
        AppState.loggedInUser = loggedInUser;
    }

    public static boolean isIsNewUser() {
        return isNewUser;
    }

    public static void setIsNewUser(boolean isNewUser) {
        AppState.isNewUser = isNewUser;
    }


    public static String getLatLong(int position){
        return locationsLatLong[position];
    }

}

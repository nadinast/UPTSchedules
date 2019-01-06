package com.apps.uptschedules;

import com.google.firebase.auth.FirebaseUser;

public class AppState {
    private static FirebaseUser loggedInUser = null;

    public static FirebaseUser getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(FirebaseUser loggedInUser) {
        AppState.loggedInUser = loggedInUser;
    }

}

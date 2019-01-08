package com.apps.uptschedules;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {

    EditText oldPassword;
    EditText newPassword;
    EditText confirmedNewPassword;
    EditText enteredPassword;
    Button changePasswordButton;

    boolean newPasswordOk = false;
    boolean oldPasswordOk = false;
    boolean enteredPasswordOk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_settings);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                onBackPressed();
            }
        });

        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);
        confirmedNewPassword = findViewById(R.id.newPasswordConfirmation);
        enteredPassword = findViewById(R.id.password);
        changePasswordButton = findViewById(R.id.changePasswordButton);

        newPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString())) {
                    if (confirmedNewPassword.getText() != null && editable.toString().equals(confirmedNewPassword.getText().toString()))
                        newPasswordOk = true;
                    else
                        newPasswordOk = false;
                    if (isPasswordReadyToBeChanged())
                        changePasswordButton.setEnabled(true);
                    else
                        changePasswordButton.setEnabled(false);
                } else
                    changePasswordButton.setEnabled(false);
            }
        });

        confirmedNewPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!TextUtils.isEmpty(editable.toString())) {
                    if (newPassword.getText() != null && editable.toString().equals(newPassword.getText().toString()))
                        newPasswordOk = true;
                    else
                        newPasswordOk = false;
                    if (isPasswordReadyToBeChanged())
                        changePasswordButton.setEnabled(true);
                    else
                        changePasswordButton.setEnabled(false);
                }else {
                    changePasswordButton.setEnabled(false);
                    newPasswordOk = false;
                }
            }
        });
    }

    public boolean isPasswordReadyToBeChanged(){
        return  newPasswordOk;
    }

    public void onChangePassword(View view) {

        AuthCredential credential = EmailAuthProvider.
                getCredential(FirebaseAuth.getInstance().getCurrentUser().getEmail(), oldPassword.getText().toString());
        FirebaseAuth.getInstance().getCurrentUser().reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            AppState.getLoggedInUser().updatePassword(newPassword.getText().toString())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.linearLayoutSettings), "Password updated", Snackbar.LENGTH_SHORT);
                                                mySnackbar.show();
                                            }
                                        }
                                    });
                        }
                        else {
                            Snackbar mySnackbar = Snackbar.make(findViewById(R.id.linearLayoutSettings), "Old Password is incorrect", Snackbar.LENGTH_LONG);
                            mySnackbar.show();
                        }
                    }
                });

    }

    public void onDeleteAccount(View view) {
        AuthCredential credential = EmailAuthProvider.
                getCredential(AppState.getLoggedInUser().getEmail(), enteredPassword.getText().toString());
        AppState.getLoggedInUser().reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            AppState.getLoggedInUser().delete()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                AppState.setLoggedInUser(null);
                                                startActivity(new Intent(SettingsActivity.this, SignInActivity.class));
                                                SettingsActivity.this.finish();
                                            }
                                        }
                                    });
                        }
                        else{
                            Snackbar mySnackbar = Snackbar.make(findViewById(R.id.linearLayoutSettings), "Entered Password is incorrect", Snackbar.LENGTH_LONG);
                            mySnackbar.show();
                        }
                    }
                });


    }
}
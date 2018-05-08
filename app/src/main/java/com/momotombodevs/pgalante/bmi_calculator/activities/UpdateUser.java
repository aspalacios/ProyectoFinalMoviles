package com.momotombodevs.pgalante.bmi_calculator.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.content.Intent;

public class UpdateUser extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText username;
    private String userId;
    private String userFirstName;
    private String userLastName;
    private String updUsername;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.momotombodevs.pgalante.bmi_calculator.R.layout.activity_update_user);

        Intent updateUser = getIntent();
        this.token = updateUser.getStringExtra("TOKEN_STRING");
    }

}

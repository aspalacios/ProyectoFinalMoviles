package com.momotombodevs.pgalante.bmi_calculator.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.momotombodevs.pgalante.bmi_calculator.Api.Api;

import com.momotombodevs.pgalante.bmi_calculator.R;
import com.momotombodevs.pgalante.bmi_calculator.models.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUser extends AppCompatActivity {

    private final String title = "Update user";
    private UserModel user;

    //Vistas
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;

    //Extras recuperados
    private String userId;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        getExtras();
        initializeViews();
    }

    private void getExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getString("Id");
            userFirstName = extras.getString("First Name");
            userLastName = extras.getString("Last Name");
            userEmail = extras.getString("Email");
            userPassword = extras.getString("Password");
        }


    }

    public void initializeViews() {
        firstName = findViewById(R.id.firstNameEditText);
        lastName = findViewById(R.id.lastNameEditText);
        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);

        firstName.setHint(userFirstName);
        lastName.setHint(userLastName);
        email.setHint(userEmail);
        password.setHint(userPassword);
    }

    public void updateUser() {
        user = new UserModel();

        user.setFirstName(firstName.getText().toString());
        user.setLastName(lastName.getText().toString());
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());

        Call<UserModel> call = Api.instance().updateUser(userId, user);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(@NonNull Call<UserModel> call, @NonNull Response<UserModel> response) {
                if (response.body() != null) {
                    try {

                        assert response != null;
                        assert response.body() != null;

                    } catch (NullPointerException e) {
                        Log.i("Debug: ", e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserModel> call, @NonNull Throwable t) {
                Log.i("Debug: ", t.getMessage());
            }
        });


    }

    public void acceptOnclick(View view) {
        validateData();
    }

    public void cancelOnclick(View view) {
        finish();
    }

    private void validateData() {

        if (!firstName.getText().toString().trim().isEmpty()) {
            if (!lastName.getText().toString().trim().isEmpty()) {
                if (!email.getText().toString().trim().isEmpty()) {
                    if (!password.getText().toString().trim().isEmpty()) {
                        updateUser();
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    } else {
                        password.setError("This field can not be blank");
                    }
                } else {
                    email.setError("This field can not be blank");
                }
            } else {
                lastName.setError("This field can not be blank");
            }
        } else {
            firstName.setError("This field can not be blank");
        }

    }
}
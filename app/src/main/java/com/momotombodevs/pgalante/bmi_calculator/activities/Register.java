package com.momotombodevs.pgalante.bmi_calculator.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.momotombodevs.pgalante.bmi_calculator.Api.Api;
import com.momotombodevs.pgalante.bmi_calculator.R;
import com.momotombodevs.pgalante.bmi_calculator.models.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private UserModel user;
    private EditText username;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeViews();
    }

    private void validateData() {

        if (!firstName.getText().toString().trim().isEmpty()) {
            if (!lastName.getText().toString().trim().isEmpty()) {
                if (!email.getText().toString().trim().isEmpty()) {
                    if (!password.getText().toString().trim().isEmpty()) {
                        createUser();
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    } else {
                        password.setError("Campo de contraseña vacío");
                    }
                } else {
                    email.setError("Campo de correo electrónico vacío");
                }
            } else {
                lastName.setError("Campo de apellido vacío");
            }
        } else {
            firstName.setError("Campo de nombre vacío");
        }

    }

    public void initializeViews() {
        firstName = findViewById(R.id.editFirstNameRegister);
        lastName = findViewById(R.id.editLastNameRegister);
        username = findViewById(R.id.editUsernameRegister);
        email = findViewById(R.id.editEmailRegister);
        password = findViewById(R.id.editPasswordRegister);
    }

    public void createUser() {
        user = new UserModel();


        user.setFirstName(firstName.getText().toString());
        user.setLastName(lastName.getText().toString());
        user.setUsername(username.getText().toString());
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());

        Call<UserModel> call = Api.instance().createUser(user);
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
}
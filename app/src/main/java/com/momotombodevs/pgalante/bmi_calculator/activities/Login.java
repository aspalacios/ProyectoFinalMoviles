package com.momotombodevs.pgalante.bmi_calculator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.momotombodevs.pgalante.bmi_calculator.R;
import com.momotombodevs.pgalante.bmi_calculator.models.LoginModel;
import com.momotombodevs.pgalante.bmi_calculator.models.LoginResult;
import com.momotombodevs.pgalante.bmi_calculator.Api.Api;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private LoginModel loginModel;
    private EditText username;
    private EditText password;
    private String usernameModel;
    private String passwordModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeViews();
        android.widget.Button btnRegisterLogin = findViewById(R.id.btnRegisterLogin);
        Button btnAccessLogin = findViewById(R.id.btnAccessLogin);

        btnAccessLogin.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View v) {
                validateData();
            }
        });

        btnRegisterLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentRegister = new Intent(Login.this, Register.class);
                Login.this.startActivity(intentRegister);
            }
        });
    }

        private void validateData() {

        if (!username.getText().toString().trim().isEmpty() && !password.getText().toString().trim().isEmpty()) {
            login();
        } else if (password.getText().toString().trim().isEmpty()) {
            password.setError("Campo de contraseña vacío");
        } else if (username.getText().toString().trim().isEmpty()) {
            username.setError("Campo de nombre de usuario vacío");
        }
    }

    public void initializeViews() {
        username = findViewById(R.id.editUsernameLogin);
        password = findViewById(R.id.editPasswordLogin);
    }

    private void login() {

        usernameModel = username.getText().toString();
        passwordModel = password.getText().toString();

        loginModel = new com.momotombodevs.pgalante.bmi_calculator.models.LoginModel(usernameModel, passwordModel);

        loginModel.setUsername(username.getText().toString());
        loginModel.setPassword(password.getText().toString());

        Call<LoginResult> call = Api.instance().login(loginModel);

        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(@NonNull Call<LoginResult> call, @NonNull Response<LoginResult> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(Login.this, Index.class);
                    startActivity(loginIntent);
                } else {
                    Toast.makeText(Login.this, "Datos Incorrectos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResult> call, @NonNull Throwable t) {
                Toast.makeText(Login.this, "Favor revisa tu conexión", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

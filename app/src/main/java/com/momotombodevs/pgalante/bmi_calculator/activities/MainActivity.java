package com.momotombodevs.pgalante.bmi_calculator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.momotombodevs.pgalante.bmi_calculator.Api.ApiInterface;
import com.momotombodevs.pgalante.bmi_calculator.R;
import com.momotombodevs.pgalante.bmi_calculator.adapters.UserAdapter;
import com.momotombodevs.pgalante.bmi_calculator.models.LoginModel;
import com.momotombodevs.pgalante.bmi_calculator.models.LoginResult;
import com.momotombodevs.pgalante.bmi_calculator.models.UserModel;
import com.momotombodevs.pgalante.bmi_calculator.Api.Api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

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
        Button btnRegisterLogin = findViewById(R.id.btnRegisterLogin);
        Button btnAccessLogin = findViewById(R.id.btnAccessLogin);


        btnAccessLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                validateData();
            }
        });

        btnRegisterLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentRegister = new Intent(MainActivity.this, Register.class);
                MainActivity.this.startActivity(intentRegister);
            }
        });

    }

    private void validateData() {

        if (!username.getText().toString().trim().isEmpty()) {
            if (!password.getText().toString().trim().isEmpty()) {
                login();
            } else {
                password.setError("Campo de contraseña vacío");
            }
        } else {
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

        loginModel = new LoginModel(usernameModel, passwordModel);

        loginModel.setUsername(username.getText().toString());
        loginModel.setPassword(password.getText().toString());

        Call<LoginResult> call = Api.instance().login(loginModel);

        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(@NonNull Call<LoginResult> call, @NonNull Response<LoginResult> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(MainActivity.this, Index.class);
                    startActivity(loginIntent);
                } else {
                    Toast.makeText(MainActivity.this, "Petición denegada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResult> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "error :)", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initViews() {
        //recyclerView = findViewById(R.id.recycler_view);
    }

    private void configureRecyclerView() {
        //recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void getProducts() {
        /*Call<ArrayList<UserModel>> call = Api.instance().getUser();
        call.enqueue(new Callback<ArrayList<UserModel>>() {
            @Override
            public void onResponse(Call<ArrayList<UserModel>> call, Response<ArrayList<UserModel>> response) {
                if(response.body() != null){
                    UserAdapter userAdapter = new UserAdapter(response.body());
                    recyclerView.setAdapter(userAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserModel>> call, Throwable t) {
                Log.i("Debug: ", t.getMessage());
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item_product clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

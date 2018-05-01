package com.momotombodevs.pgalante.bmi_calculator.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.momotombodevs.pgalante.bmi_calculator.R;
import com.momotombodevs.pgalante.bmi_calculator.models.UserModel;
import com.momotombodevs.pgalante.bmi_calculator.Api.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateUser extends AppCompatActivity {

    private final String title = "New user";
    private UserModel user;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        initializeViews();
    }

    private void validateData(){

        if(!firstName.getText().toString().trim().isEmpty()){
            if(!lastName.getText().toString().trim().isEmpty()){
                if(!email.getText().toString().trim().isEmpty()){
                    if(!password.getText().toString().trim().isEmpty()){
                        createProduct();
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

    public void initializeViews (){
        firstName = findViewById(R.id.firstNameEditText);
        lastName = findViewById(R.id.lastNameEditText);
        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
    }

    public void createProduct() {
        user = new UserModel();

        user.setFirstName(firstName.getText().toString());
        user.setLastName(lastName.getText().toString());
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());

        Call<UserModel> call = Api.instance().createUser(user);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(@NonNull Call<UserModel> call, @NonNull Response<UserModel> response) {
                if(response.body() != null){
                    try {

                        assert response != null;
                        assert response.body() != null;

                    }catch(NullPointerException e){
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
}

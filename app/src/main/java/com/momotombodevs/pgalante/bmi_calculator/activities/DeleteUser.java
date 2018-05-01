package com.momotombodevs.pgalante.bmi_calculator.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.momotombodevs.pgalante.bmi_calculator.R;
import com.momotombodevs.pgalante.bmi_calculator.models.UserModel;
import com.momotombodevs.pgalante.bmi_calculator.Api.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteUser extends AppCompatActivity {

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);
        Context context = this;
        getExtras();
        deleteProduct(context);
        this.startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void getExtras(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            id = extras.getString("Id");
        }
    }

    private void deleteProduct(final Context context){
        Call<UserModel> call = Api.instance().deleteUser(id);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.body() != null) {
                    Toast.makeText(context, "Element deleted", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.i("Debug: ", t.getMessage());

            }
        });
    }
}

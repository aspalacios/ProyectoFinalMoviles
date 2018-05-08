package com.momotombodevs.pgalante.bmi_calculator.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.momotombodevs.pgalante.bmi_calculator.Api.Api;
import com.momotombodevs.pgalante.bmi_calculator.R;
import com.momotombodevs.pgalante.bmi_calculator.models.AdviceModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteAdvice extends AppCompatActivity {
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_advice);
        Context context = this;
        getExtras();
        deleteAdvice(context);
        this.startActivity(new Intent(this, Advice.class));
        finish();
    }

    private void getExtras(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            id = extras.getString("Id");
        }
    }

    private void deleteAdvice(final Context context){
        Call<AdviceModel> call = Api.instance().deleteAdvice(id);
        call.enqueue(new Callback<AdviceModel>() {
            @Override
            public void onResponse(Call<AdviceModel> call, Response<AdviceModel> response) {
                if(response.body() != null){
                    Toast.makeText(context, "Element deleted", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AdviceModel> call, Throwable t) {
                Log.i("Debug: ", t.getMessage());

            }
        });
    }

}

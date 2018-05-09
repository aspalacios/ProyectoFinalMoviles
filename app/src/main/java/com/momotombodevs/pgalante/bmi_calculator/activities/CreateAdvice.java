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
import com.momotombodevs.pgalante.bmi_calculator.models.AdviceModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAdvice extends AppCompatActivity {

    private AdviceModel advice;
    private EditText title;
    private EditText description;
    private EditText category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advice);
        initializeViews();
    }

    private void validateData() {

        if (!title.getText().toString().trim().isEmpty() && !description.getText().toString().trim().isEmpty() && !category.getText().toString().trim().isEmpty()) {
            createAdvice();
            startActivity(new Intent(this, Advice.class));
            finish();
        } else if (title.getText().toString().trim().isEmpty()) {
            title.setError("Este campo no puede ir en blanco");
        } else if (description.getText().toString().trim().isEmpty()) {
            description.setError("Este campo no puede ir en blanco");
        } else if (category.getText().toString().trim().isEmpty()) {
            category.setError("Este campo no puede ir en blanco");
        }
    }

    public void initializeViews() {
        title = findViewById(R.id.ETtitle);
        description = findViewById(R.id.ETdescription);
        category = findViewById(R.id.ETcategory);
    }

    public void createAdvice() {
        advice = new AdviceModel();

        advice.setTitle(title.getText().toString());
        advice.setDescription(description.getText().toString());
        advice.setCategory(category.getText().toString());

        Call<AdviceModel> call = Api.instance().createAdvice(advice);
        call.enqueue(new Callback<AdviceModel>() {
            @Override
            public void onResponse(@NonNull Call<AdviceModel> call, @NonNull Response<AdviceModel> response) {
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
            public void onFailure(@NonNull Call<AdviceModel> call, @NonNull Throwable t) {
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
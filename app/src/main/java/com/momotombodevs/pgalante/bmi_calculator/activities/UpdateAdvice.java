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

import junit.framework.Assert;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateAdvice extends AppCompatActivity {

    private AdviceModel advice;

    private EditText title;
    private EditText description;
    private EditText category;

    private String adviceId;
    private String adviceTitle;
    private String adviceDescription;
    private String adviceCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_advice);
        getExtras();
        initializeViews();
    }

    private void getExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            adviceId = extras.getString("Id");
            adviceTitle = extras.getString("Title");
            adviceDescription = extras.getString("Description");
            adviceCategory = extras.getString("Category");
        }
    }

    public void initializeViews() {
        title = findViewById(R.id.ETtitle);
        description = findViewById(R.id.ETdescription);
        category = findViewById(R.id.ETcategory);
        title.setText(adviceTitle);
        description.setText(adviceDescription);
        category.setText(adviceCategory);
    }

    private void validateData() {

        if (!title.getText().toString().trim().isEmpty()) {
            if (!description.getText().toString().trim().isEmpty()) {
                updateAdvice();
                startActivity(new Intent(this, Advice.class));
                finish();
            } else {
                description.setError("This field can not be blank");
            }
        } else {
            title.setError("This field can not be blank");
        }
    }

    public void updateAdvice() {
        advice = new AdviceModel();

        advice.setTitle(title.getText().toString());
        advice.setDescription(description.getText().toString());
        advice.setCategory( category.getText().toString());

        Call<AdviceModel> call = Api.instance().updateAdvice(adviceId, advice);
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

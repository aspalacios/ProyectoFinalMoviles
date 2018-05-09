package com.momotombodevs.pgalante.bmi_calculator.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.momotombodevs.pgalante.bmi_calculator.Api.Api;
import com.momotombodevs.pgalante.bmi_calculator.R;
import com.momotombodevs.pgalante.bmi_calculator.models.AdviceModel;
import com.momotombodevs.pgalante.bmi_calculator.models.BodyModel;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Result extends AppCompatActivity {
    private BodyModel bodyModel;
    private TextView result;
    private TextView range;
    private String height;
    private String weight;
    private float heightValue, weightValue, bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result = findViewById(R.id.insertResult);
        range = findViewById(R.id.insertRank);
        Button btnHistory = findViewById(R.id.btnHistory);
        Button btnAdvices = findViewById(R.id.btnAdvices);
        getExtras();
        createBodyModel();
        btnAdvices.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentAdvices = new Intent(Result.this, ShowAdvice.class);
                Result.this.startActivity(intentAdvices);
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentBody = new Intent(Result.this, Body.class);
                Result.this.startActivity(intentBody);
            }
        });
    }

    private void getExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            height = extras.getString("Height");
            weight = extras.getString("Weight");
            heightValue = Float.parseFloat(height) / 100;
            weightValue = Float.parseFloat(weight);
            bmi = weightValue / (heightValue * heightValue);
            displayBmi(bmi);
        }
    }

    private void displayBmi(float bmi) {
        String bmilabel = "";
        if (Float.compare(bmi, 15f) <= 0) {
            bmilabel = getString(R.string.peso_inferior_al_menor);
        } else if (Float.compare(bmi, 15f) > 0 && Float.compare(bmi, 16f) <= 0) {
            bmilabel = getString(R.string.peso_inferior_al_menor);
        } else if (Float.compare(bmi, 16f) > 0 && Float.compare(bmi, 18.5f) <= 0) {
            bmilabel = getString(R.string.peso_inferior_al_menor);
        } else if (Float.compare(bmi, 18.5f) > 0 && Float.compare(bmi, 25f) <= 0) {
            bmilabel = getString(R.string.normal);
        } else if (Float.compare(bmi, 25f) > 0 && Float.compare(bmi, 30f) <= 0) {
            bmilabel = getString(R.string.peso_superior_al_normal);
        } else if (Float.compare(bmi, 30f) > 0 && Float.compare(bmi, 35f) <= 0) {
            bmilabel = getString(R.string.obesidad);
        } else if (Float.compare(bmi, 35f) > 0 && Float.compare(bmi, 40f) <= 0) {
            bmilabel = getString(R.string.obesidad);
        }
        result.setText(String.valueOf(bmi));
        range.setText(bmilabel);
    }

    public void createBodyModel() {
        bodyModel = new BodyModel();

        bodyModel.setHeight(height);
        bodyModel.setWeight(weight);

        Call<BodyModel> call = Api.instance().createBodyModel(bodyModel);
        call.enqueue(new Callback<BodyModel>() {
            @Override
            public void onResponse(@NonNull Call<BodyModel> call, @NonNull Response<BodyModel> response) {
                if (response.body() != null) {
                    try {

                        assert response != null;
                        assert response.body() != null;
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.commitTransaction();

                    } catch (NullPointerException e) {
                        Log.i("Debug: ", e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<BodyModel> call, @NonNull Throwable t) {
                Log.i("Debug: ", t.getMessage());
            }
        });
    }
}

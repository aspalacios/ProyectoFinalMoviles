package com.momotombodevs.pgalante.bmi_calculator.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.momotombodevs.pgalante.bmi_calculator.R;

public class Result extends AppCompatActivity {
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
        getExtras();
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
}

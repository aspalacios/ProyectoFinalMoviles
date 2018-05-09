package com.momotombodevs.pgalante.bmi_calculator.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.momotombodevs.pgalante.bmi_calculator.R;

public class Result extends AppCompatActivity {

    private EditText height;
    private EditText weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }
}

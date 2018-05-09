package com.momotombodevs.pgalante.bmi_calculator.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.clans.fab.FloatingActionButton;
import com.momotombodevs.pgalante.bmi_calculator.R;

public class Index extends AppCompatActivity {
    private EditText weight;
    private EditText height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Button btnCalculate = findViewById(R.id.btnCalculate);
        FloatingActionButton btnGetAdvices = findViewById(R.id.getAdvices);
        FloatingActionButton btnLogout = findViewById(R.id.logout);
        weight = findViewById(R.id.editWeight);
        height = findViewById(R.id.editHeight);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentBody = new Intent(Index.this, Result.class);
                intentBody.putExtra("Weight", weight.getText().toString());
                intentBody.putExtra("Height", height.getText().toString());
                Index.this.startActivity(intentBody);
            }
        });

        btnGetAdvices.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentAdvices = new Intent(Index.this, Advice.class);
                Index.this.startActivity(intentAdvices);
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentLogin = new Intent(Index.this, MainActivity.class);
                Index.this.startActivity(intentLogin);
            }
        });
    }
}

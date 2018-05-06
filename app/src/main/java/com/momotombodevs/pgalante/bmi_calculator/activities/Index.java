package com.momotombodevs.pgalante.bmi_calculator.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.clans.fab.FloatingActionButton;
import com.momotombodevs.pgalante.bmi_calculator.R;

public class Index extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        FloatingActionButton btnEditUser = findViewById(R.id.editUser);
        FloatingActionButton btnDeleteUser = findViewById(R.id.deleteUser);
        FloatingActionButton btnLogout = findViewById(R.id.logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentLogin = new Intent(Index.this, Login.class);
                Index.this.startActivity(intentLogin);
            }
        });
    }
}

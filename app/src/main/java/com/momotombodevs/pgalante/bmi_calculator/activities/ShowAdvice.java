package com.momotombodevs.pgalante.bmi_calculator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.momotombodevs.pgalante.bmi_calculator.Api.Api;
import com.momotombodevs.pgalante.bmi_calculator.R;
import com.momotombodevs.pgalante.bmi_calculator.adapters.AdviceAdapter;
import com.momotombodevs.pgalante.bmi_calculator.models.AdviceModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAdvice extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice_vanilla);
        initViews();
        configureRecyclerView();
        getAdvices();
    }
    private void initViews() {
        recyclerView = findViewById(R.id.recycler_view_vanilla);
    }

    private void configureRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void getAdvices (){
        Call<ArrayList<AdviceModel>> call = Api.instance().getAdvices();
        call.enqueue(new Callback<ArrayList<AdviceModel>>() {
            @Override
            public void onResponse(Call<ArrayList<AdviceModel>> call, Response<ArrayList<AdviceModel>> response) {
                if(response.body() != null){
                    AdviceAdapter adviceAdapter = new AdviceAdapter(response.body());
                    recyclerView.setAdapter(adviceAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<AdviceModel>> call, Throwable t) {
                Log.i("Debug: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item_product clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

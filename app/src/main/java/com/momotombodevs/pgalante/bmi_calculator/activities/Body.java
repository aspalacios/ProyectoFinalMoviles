package com.momotombodevs.pgalante.bmi_calculator.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.momotombodevs.pgalante.bmi_calculator.Api.Api;
import com.momotombodevs.pgalante.bmi_calculator.R;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.momotombodevs.pgalante.bmi_calculator.adapters.BodyFromDatabaseAdapter;
import com.momotombodevs.pgalante.bmi_calculator.models.BodyModel;
import com.tumblr.remember.Remember;


public class Body extends AppCompatActivity {

    private static final String IS_FIRST_TIME = "is_first_time";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);
        initViews();

        // Only call fetchProducts on first time
        if (!isFirstTime()) {
            fetchProducts();
            storeFirstTime();
        } else {
            getFromDataBase();
        }

    }

    private void storeFirstTime() {
        Remember.putBoolean(IS_FIRST_TIME, true);
    }

    private boolean isFirstTime() {
        return Remember.getBoolean(IS_FIRST_TIME, false);
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerviewBody);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayoutBody);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchProducts();
            }
        });


    }

    private void fetchProducts() {
        Call<List<BodyModel>> call = Api.instance().bodiesAll();
        call.enqueue(new Callback<List<BodyModel>>() {
            @Override
            public void onResponse(Call<List<BodyModel>> call, Response<List<BodyModel>> response) {
                //mAdapter = new ProductsAdapter(response.body());
                //mRecyclerView.setAdapter(mAdapter);

                sync(response.body());
                getFromDataBase();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<BodyModel>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void sync(List<BodyModel> bodyModels) {
        for(BodyModel bodyModel : bodyModels) {
            store(bodyModel);
        }
    }

    private void store(BodyModel bodyModelFromApi) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        BodyModel bodyModel = realm.createObject(BodyModel.class); // Create a new object

        bodyModel.setId(bodyModelFromApi.getId());
        bodyModel.setHeight(bodyModelFromApi.getHeight());
        bodyModel.setWeight(bodyModelFromApi.getWeight());

        realm.commitTransaction();
    }

    private void getFromDataBase() {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<BodyModel> query = realm.where(BodyModel.class);

        RealmResults<BodyModel> results = query.findAll();

        mAdapter = new BodyFromDatabaseAdapter(results);
        mRecyclerView.setAdapter(mAdapter);
    }
}

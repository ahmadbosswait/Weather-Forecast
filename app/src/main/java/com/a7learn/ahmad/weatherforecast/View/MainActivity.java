package com.a7learn.ahmad.weatherforecast.View;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.a7learn.ahmad.weatherforecast.Adapter.AppFeaturesAdapter;
import com.a7learn.ahmad.weatherforecast.DataFakeGenerator;
import com.a7learn.ahmad.weatherforecast.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private AppFeaturesAdapter appFeaturesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 2;
                } else {
                    return 2;
                }
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        appFeaturesAdapter = new AppFeaturesAdapter(this);
        recyclerView.setAdapter(appFeaturesAdapter);
        appFeaturesAdapter.setAppFeatures(DataFakeGenerator.getAppFeatures(this));

    }
}

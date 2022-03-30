package com.unknown.developer.countryflags;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.unknown.developer.countryflags.adapter.CountryListAdapter;
import com.unknown.developer.countryflags.core.CountryContract;
import com.unknown.developer.countryflags.core.CountryPresenter;
import com.unknown.developer.countryflags.model.Country;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CountryContract.View {

    public CountryPresenter mPresenter;
    List<Country> countryList;
    CountryListAdapter countryListAdapter;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new CountryPresenter(this);
        mPresenter.getCountry();
        countryList = new ArrayList<>();


        recyclerView = findViewById(R.id.activity_main_flag_list);



    }


    @Override
    public void onSuccess(List<Country> categoryModels) {
        countryList = categoryModels;


        countryListAdapter = new CountryListAdapter(getApplicationContext(), countryList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(countryListAdapter);

    }

    @Override
    public void onFailed(Throwable throwable) {

    }

    @Override
    public void ProcessStart() {

    }

    @Override
    public void ProcessEnd() {

    }
}
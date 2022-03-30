package com.unknown.developer.countryflags;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.unknown.developer.countryflags.core.CountryContract;
import com.unknown.developer.countryflags.core.CountryPresenter;
import com.unknown.developer.countryflags.model.Country;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CountryContract.View {

    public CountryPresenter mPresenter;

    List<Country> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new CountryPresenter(this);
        mPresenter.getCountry();
        countryList = new ArrayList<>();

    }


    @Override
    public void onSuccess(List<Country> categoryModels) {
        countryList = categoryModels;

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
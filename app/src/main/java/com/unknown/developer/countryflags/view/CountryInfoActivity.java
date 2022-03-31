package com.unknown.developer.countryflags.view;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.unknown.developer.countryflags.R;
import com.unknown.developer.countryflags.constant.ApplicationString;
import com.unknown.developer.countryflags.helper.CurrencyHelper;
import com.unknown.developer.countryflags.model.Country;

import java.util.Objects;

public class CountryInfoActivity extends AppCompatActivity {
    Country country;
    CurrencyHelper currencyHelper;
    ImageView countryInfoImage;
    TextView countryInfoName, countryInfoAbbreviation, countryInfoCapital, countryInfoPopulations, countryInfoCurrencies;
    Gson gson;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_info_layout);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        initObject();
        setValue();


    }

    void initObject() {
        countryInfoImage = findViewById(R.id.countryInfoFlag);
        countryInfoName = findViewById(R.id.countryInfoName);
        countryInfoAbbreviation = findViewById(R.id.countryInfoAbbreviation);
        countryInfoCapital = findViewById(R.id.countryInfoCapital);
        countryInfoPopulations = findViewById(R.id.countryInfoPopulations);
        countryInfoCurrencies = findViewById(R.id.countryInfoCurrencies);



    }

    void setValue(){
        gson = new Gson();
        currencyHelper = new CurrencyHelper();
        String studentDataObjectAsAString = getIntent().getStringExtra(ApplicationString.COUNTRY);
        country = gson.fromJson(studentDataObjectAsAString, Country.class);


        Glide.with(getApplicationContext()).load(country.getFlags().getPng()).dontTransform().into(countryInfoImage);
        countryInfoName.setText(country.getName());
        countryInfoAbbreviation.setText(country.getAlpha2Code());
        countryInfoCapital.setText(country.getCapital());
        countryInfoPopulations.setText(String.valueOf(country.getPopulation()));

        if (country.getCurrencies()!=null) {
            countryInfoCurrencies.setText(currencyHelper.getCurrencyValue(country.getCurrencies()));
        }
    }




}

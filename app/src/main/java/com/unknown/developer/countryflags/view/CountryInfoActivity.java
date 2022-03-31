package com.unknown.developer.countryflags.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.unknown.developer.countryflags.R;
import com.unknown.developer.countryflags.model.Country;

import java.util.List;

public class CountryInfoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_info_layout);

        Intent intent = getIntent();
        String countryName = intent.getStringExtra("CountryName");
        Log.e("TEST", countryName);

    }
}

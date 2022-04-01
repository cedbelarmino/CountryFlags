package com.unknown.developer.countryflags.view;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.unknown.developer.countryflags.R;
import com.unknown.developer.countryflags.constant.ApplicationString;
import com.unknown.developer.countryflags.helper.StringListHelper;
import com.unknown.developer.countryflags.model.Country;

import java.util.Objects;

public class CountryInfoActivity extends AppCompatActivity implements OnMapReadyCallback {
    Country country;
    StringListHelper stringListHelper;
    ImageView countryInfoImage;
    TextView countryInfoName,
            countryInfoAbbreviation,
            countryInfoCapital,
            countryInfoPopulations,
            countryInfoCurrencies,
            countryInfoLanguages,
            countryInfoTranslations,
            countryInfoCallingCodes,
            countryInfoRegion,
            countryInfoBorders;
    Gson gson;
    double lat = 0.0;
    double lng = 0.0;

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
        countryInfoLanguages = findViewById(R.id.countryInfoLanguages);
        countryInfoTranslations = findViewById(R.id.countryInfoTranslations);
        countryInfoCallingCodes = findViewById(R.id.countryInfoCallingCodes);
        countryInfoRegion = findViewById(R.id.countryInfoRegion);
        countryInfoBorders = findViewById(R.id.countryInfoBorders);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);


    }

    void setValue() {
        gson = new Gson();
        stringListHelper = new StringListHelper();
        String studentDataObjectAsAString = getIntent().getStringExtra(ApplicationString.COUNTRY);
        country = gson.fromJson(studentDataObjectAsAString, Country.class);


        Glide.with(getApplicationContext()).load(country.getFlags().getPng()).dontTransform().into(countryInfoImage);
        countryInfoName.setText(country.getName());
        countryInfoAbbreviation.setText(country.getAlpha2Code());
        countryInfoCapital.setText(country.getCapital());
        countryInfoPopulations.setText(String.valueOf(country.getPopulation()));

        if (country.getCurrencies() != null) {
            countryInfoCurrencies.setText(stringListHelper.getCurrencyValue(country.getCurrencies()));
        }
        countryInfoLanguages.setText(stringListHelper.getLanguages(country.getLanguages()));
        countryInfoTranslations.setText(stringListHelper.getTranslation(country.getTranslations()));
        countryInfoCallingCodes.setText(stringListHelper.getCallingCodes(country.getCallingCodes()));
        countryInfoRegion.setText(country.getRegion());
        if (country.getBorders() != null) {
            countryInfoBorders.setText(stringListHelper.getBorders(country.getBorders()));
        } else {
            countryInfoBorders.setText(this.getResources().getString(R.string.country_info_no_border));
        }
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

      if (country.getLatlng()!=null){
           lat = country.getLatlng().get(0);
           lng = country.getLatlng().get(1);
      }

        LatLng latLng = new LatLng(lat, lng);
        googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(country.getName()));
        googleMap.stopAnimation();
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(4.5f)
                .build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}

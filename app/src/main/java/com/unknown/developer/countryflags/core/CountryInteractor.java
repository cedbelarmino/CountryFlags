package com.unknown.developer.countryflags.core;
import androidx.annotation.NonNull;

import com.unknown.developer.countryflags.api.ApiInterface;
import com.unknown.developer.countryflags.constant.ApplicationString;
import com.unknown.developer.countryflags.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryInteractor implements  CountryContract.Interactor{
    private final CountryContract.onOperationListener operationListener;
    public CountryInteractor(CountryContract.onOperationListener mListner) {
        this.operationListener = mListner;
    }

    @Override
    public void perFormReadCountry() {
        operationListener.onStart();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApplicationString.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<List<Country>> countryResultCall = apiInterface.getCountry();

        countryResultCall.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(@NonNull Call<List<Country>> call, @NonNull Response<List<Country>> response) {
                if (response.isSuccessful()){

                    operationListener.onSuccess(response.body());
                    operationListener.onEnd();
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<Country>> call, @NonNull Throwable t) {
                operationListener.onEnd();

            }
        });

    }
}

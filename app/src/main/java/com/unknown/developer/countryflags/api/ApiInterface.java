package com.unknown.developer.countryflags.api;

import com.unknown.developer.countryflags.model.Country;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface  ApiInterface {

    @GET("v2/all")
    Call<List<Country>> getCountry();

}

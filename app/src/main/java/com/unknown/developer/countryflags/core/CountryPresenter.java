package com.unknown.developer.countryflags.core;
import com.unknown.developer.countryflags.model.Country;

import java.util.List;

public class CountryPresenter implements CountryContract.Presenter, CountryContract.onOperationListener{


    private final CountryContract.View mView;
    private final CountryInteractor countryInteractor;

    public CountryPresenter(CountryContract.View mView) {
        this.mView = mView;
        countryInteractor = new CountryInteractor(this);
    }

    @Override
    public void onSuccess(List<Country> country) {
        mView.onSuccess(country);
    }

    @Override
    public void onFailure(Throwable t) {
        mView.onFailed(t);
    }

    @Override
    public void onStart() {
        mView.ProcessStart();
    }

    @Override
    public void onEnd() {
        mView.ProcessEnd();
    }

    @Override
    public void getCountry() {
        countryInteractor.perFormReadCountry();

    }
}

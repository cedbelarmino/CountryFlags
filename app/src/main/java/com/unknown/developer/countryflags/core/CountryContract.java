package com.unknown.developer.countryflags.core;

import com.unknown.developer.countryflags.model.Country;

import java.util.List;

public class CountryContract {
    public interface  View{
        void onSuccess(List<Country> country);
        void onFailed(Throwable throwable);
        void ProcessStart();
        void ProcessEnd();
    }


    interface  Presenter{
        void getCountry();
    }

    interface Interactor{
        void perFormReadCountry();
    }

    interface onOperationListener {
        void onSuccess(List<Country> countryResultList);

        void onFailure(Throwable t);

        void onStart();

        void onEnd();

    }

}

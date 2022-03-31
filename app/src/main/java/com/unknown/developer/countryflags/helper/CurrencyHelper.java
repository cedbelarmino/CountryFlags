package com.unknown.developer.countryflags.helper;

import com.unknown.developer.countryflags.model.Currency;

import java.util.List;

public class CurrencyHelper {


    public String getCurrencyValue(List<Currency> currency){
        StringBuilder builder = new StringBuilder();

        for (Currency details : currency) {
            builder.append(details.getName()).append("\n").append(details.getCode()).append("\n").append(details.getSymbol());
        }



        return builder.toString();
    }
}

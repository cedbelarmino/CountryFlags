package com.unknown.developer.countryflags.helper;

import com.unknown.developer.countryflags.constant.ApplicationString;
import com.unknown.developer.countryflags.model.Currency;
import com.unknown.developer.countryflags.model.Language;
import com.unknown.developer.countryflags.model.Translations;

import java.util.List;

public class StringListHelper {


    public String getCurrencyValue(List<Currency> currency) {
        StringBuilder builder = new StringBuilder();

        for (Currency details : currency) {
            builder.append(ApplicationString.CURRENCY_NAME).append(details.getName()).append("\n").append(ApplicationString.CURRENCY_CODE).append(details.getCode()).append("\n").append(ApplicationString.CURRENCY_SYMBOL).append(details.getSymbol());
        }
        return builder.toString();
    }

    public String getLanguages(List<Language> languages) {
        StringBuilder builder = new StringBuilder();

        for (Language details : languages) {
            builder.append(ApplicationString.LANGUAGES_ISO1).append(details.getIso6391()).append("\n").append(ApplicationString.LANGUAGES_ISO2).append(details.getIso6392()).append("\n").append(ApplicationString.LANGUAGES_NAME).append(details.getName()).append("\n").append(ApplicationString.LANGUAGES_NATIVE_NAME).append(details.getNativeName()).append("\n");
        }
        return builder.toString();
    }

    public String getTranslation(Translations translations) {


        return ApplicationString.TRANSLATIONS_BR + translations.getBr() + "\n" + ApplicationString.TRANSLATIONS_PT + translations.getPt() + "\n" + ApplicationString.TRANSLATIONS_NL + translations.getNl() + "\n" + ApplicationString.TRANSLATIONS_HR + translations.getHr() + "\n" + ApplicationString.TRANSLATIONS_FA + translations.getFa() + "\n" + ApplicationString.TRANSLATIONS_DE + translations.getDe() + "\n" + ApplicationString.TRANSLATIONS_ES + translations.getEs() + "\n" + ApplicationString.TRANSLATIONS_FR + translations.getFr() + "\n" + ApplicationString.TRANSLATIONS_JA + translations.getJa() + "\n" + ApplicationString.TRANSLATIONS_IT + translations.getIt() + "\n" + ApplicationString.TRANSLATIONS_HU + translations.getHu();
    }

    public String getBorders(List<String> borders) {
        StringBuilder builder = new StringBuilder();

        for (String details : borders) {
            builder.append(details);
            builder.append("," + "\n");
        }

        return builder.toString();
    }

    public String getCallingCodes(List<String> callingCodes) {
        StringBuilder builder = new StringBuilder();

        for (String details : callingCodes) {
            builder.append(details);
            builder.append("," + "\n");
        }

        return builder.toString().replace(",", "");
    }

}

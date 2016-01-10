package com.github.rodrigohenriques.picnic.ui.custom;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NetherlandsCurrencyFormatter implements CurrencyFormatter {

    @Inject
    public NetherlandsCurrencyFormatter() {}

    public String format(long amount) {
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(new Locale("NL", "nl"));
        decimalFormat.applyPattern("¤ #,##0.00;¤ -#");
        return decimalFormat.format(amount / 100.0);
    }
}

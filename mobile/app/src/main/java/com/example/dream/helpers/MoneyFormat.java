package com.example.dream.helpers;


import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class MoneyFormat {
    public static String format(Double price){
        NumberFormat format = NumberFormat.getCurrencyInstance( new Locale("pt", "BR") );
        format.setMaximumFractionDigits(2);
        format.setCurrency(Currency.getInstance("BRL"));
        return format.format(price);
    }
}



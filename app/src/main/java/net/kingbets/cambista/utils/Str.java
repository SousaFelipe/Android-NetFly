package net.kingbets.cambista.utils;


import android.support.annotation.NonNull;

import java.text.Normalizer;
import java.text.NumberFormat;
import java.util.Locale;


public abstract class Str {



    public static String mask(@NonNull String text, @NonNull String mask) {

        String masked = "";
        int k = 0;

        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) == '#') {
                if (k < text.length()) masked = masked.concat( String.valueOf(text.charAt(k++)) );
            }
            else {
                if (mask.length() >= text.length()) masked = masked.concat( String.valueOf(mask.charAt(i)) );
            }
        }

        return masked;
    }



    public static String getCurrency(double value) {
        Locale locale = new Locale("pt", "BR");
        NumberFormat currency = NumberFormat.getCurrencyInstance(locale);
        return currency.format(value);
    }



    public static double currencyToDouble(String currency) {
        currency = currency.replaceAll("[R$,.]", "");
        return Double.parseDouble( currency );
    }



    public static String nomeResumido(@NonNull String completeName) {

        String[] array = completeName.split(" ");
        String inlineName = array[0] + " ";

        inlineName = inlineName.concat(
                array[1].length() > 3 ? array[1] : array[2]
        );

        return inlineName;
    }


    @NonNull
    public static String removeAcentos(@NonNull String acentuado) {
        return Normalizer.normalize(acentuado, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}

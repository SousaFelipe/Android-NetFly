package net.kingbets.cambista.utils;


import java.text.Normalizer;
import java.text.NumberFormat;
import java.util.Locale;


public abstract class Str {



    public static String getCurrency(double value) {
        Locale locale = new Locale("pt", "BR");
        NumberFormat currency = NumberFormat.getCurrencyInstance(locale);
        return currency.format(value);
    }



    public static String getResumedCambistaName(String completeName) {

        String[] array = completeName.split(" ");
        String inlineName = array[0] + " ";

        inlineName = inlineName.concat(
                array[1].length() > 3 ? array[1] : array[2]
        );

        return inlineName;
    }



    public static String getStringFromArray(int[] array) {

        String inline = "";

        for (int i = 0; i < array.length; i++) {
            inline = inline.concat(
                    (i < array.length - 1) ? String.valueOf(array[i]).concat(";") : String.valueOf(array[i])
            );
        }

        return inline;
    }



    public static String removeAcentos(String acentuado) {
        return Normalizer.normalize(acentuado, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }



    public static int[] getArrayFromString(String string) {

        String[] arrayStr = string.split(";");
        int[] arrayInt = new int[arrayStr.length];

        for (int i = 0; i < arrayInt.length; i++) {
            arrayInt[i] = Integer.parseInt(arrayStr[i]);
        }

        return arrayInt;
    }



    public static String inverse(String text) {

        int len = text.length();

        char[] chars = new char[len];
        char[] tempc = new char[len];

        for (int i = 0; i < len; i++) {
            tempc[i] = text.charAt(i);
        }

        for (int i = 0; i < len; i++) {
            chars[i] = tempc[len - 1 - i];
        }

        return new String(chars);
    }
}

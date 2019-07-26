package net.kingbets.cambista.utils;


import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public abstract class DateTime {



    public static Date getDateFromString(String date) {

        try {

            DateFormat formater = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            return formater.parse(date);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Date getDateFromString(String date, String format) {

        try {

            DateFormat formater = new SimpleDateFormat(format, Locale.getDefault());
            return formater.parse(date);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



    public static Calendar calendar() {
        return calendar(new Date());
    }

    public static Calendar calendar(Date date) {
        Calendar calendar = Calendar.getInstance(new Locale("en", "UK"));
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        return calendar;
    }



    public static String getInlineDate(Date date) {
        return getInlineDate(date, "yyyy-MM-dd");
    }

    public static String getInlineDate(Date date, String format) {

        try {

            DateFormat formater = new SimpleDateFormat(format, Locale.getDefault());
            return formater.format(date);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }




    public static String compact(String hora) {
        String[] horas = hora.split(":");
        return horas[0] + ":" + horas[1];
    }



    public static String inverse(String date) {

        String[] oldDate = date.split("-");
        int length = oldDate.length;
        String[] newDate = new String[length];

        for (int i = 0; i < oldDate.length; i++) {
            length--;
            newDate[i] = oldDate[length];
        }

        return TextUtils.join("-", newDate);
    }



    public static String compile(int day, int month, int year, char c) {

        String strD = String.valueOf(day);
        String strM = String.valueOf(month);

        String strDay = (strD.length() > 1) ? strD : "0" + strD;
        String strMonth = (strM.length() > 1) ? strM : "0" + strM;
        String strYear = String.valueOf(year);

        return strDay + c + strMonth + c + strYear;
    }



    public static boolean pastTime(String past, String time) {

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

        try {

            Date pastTime = formatter.parse(past);
            Date timePast = formatter.parse(time);

            return (pastTime.getTime() > timePast.getTime());
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }
}

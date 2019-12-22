package net.kingbets.cambista.utils;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

public abstract class Config {


    public static final String PERMISSION_DENIED = "PERMISSION_DENIED";
    public static final String AUTH_HEADER = "Authorization";


    public static final double LIMITE_VALOR     = 1000;
    public static final double LIMITE_COTACAO   = 1000;
    public static final double LIMITE_PREMIO    = 20000;



    public static boolean somePermissionDenied(Context context) {
        return (
                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED
        );
    }



    public static String getDeviceInfo(Context context) {

        if (somePermissionDenied(context))
            return PERMISSION_DENIED;

        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return Build.SERIAL;
        }
        else {
            return Build.getSerial();
        }
    }
}

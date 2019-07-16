package net.kingbets.cambista.permissions;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;


public abstract class Net {



    public static boolean networkState(@NonNull Context context) {
        return (
                ContextCompat.checkSelfPermission(
                        context, Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED
        );
    }



    public static boolean wifiState(@NonNull Context context) {
        return (
                ContextCompat.checkSelfPermission(
                        context, Manifest.permission.ACCESS_WIFI_STATE) == PackageManager.PERMISSION_GRANTED
        );
    }



    public static boolean hasConnecton(@NonNull Context context) {

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return (
                manager.getActiveNetworkInfo() != null
                        && manager.getActiveNetworkInfo().isAvailable()
                        && manager.getActiveNetworkInfo().isConnected()
        );
    }
}

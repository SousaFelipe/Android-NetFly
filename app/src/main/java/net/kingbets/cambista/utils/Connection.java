package net.kingbets.cambista.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

import net.kingbets.cambista.alerts.NetAlert;


public abstract class Connection {



    public static void check(@NonNull Context context) {

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean check = (
                manager.getActiveNetworkInfo() != null
                && manager.getActiveNetworkInfo().isAvailable()
                && manager.getActiveNetworkInfo().isConnected()
        );

        if (!check) {
            NetAlert.show(context);
        }
    }
}

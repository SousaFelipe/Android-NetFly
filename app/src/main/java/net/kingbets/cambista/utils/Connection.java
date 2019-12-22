package net.kingbets.cambista.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.kingbets.cambista.alerts.NetAlert;
import net.kingbets.cambista.model.Cambista;
import net.kingbets.cambista.model.contracts.CambistaContract;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.annotations.EverythingIsNonNull;


public abstract class Connection {



    public static OkHttpClient getClientWithAuthHeader(Context context) {

        final Cambista cambista = new CambistaContract(context).first();

        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override @NonNull
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder builder = original.newBuilder().method(original.method(), original.body());
                builder.header(Config.AUTH_HEADER,  cambista.token);
                return chain.proceed(builder.build());
            }


        }).build();
    }



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

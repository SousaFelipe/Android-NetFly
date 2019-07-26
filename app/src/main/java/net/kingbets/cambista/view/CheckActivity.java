package net.kingbets.cambista.view;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.contracts.CambistaContract;
import net.kingbets.cambista.model.contracts.PerfilContract;
import net.kingbets.cambista.model.local.Cambista;
import net.kingbets.cambista.model.responses.PerfilResponse;
import net.kingbets.cambista.model.responses.StatusResponse;
import net.kingbets.cambista.utils.URL;

import java.io.IOException;
import java.text.NumberFormat;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class CheckActivity extends BaseActivity {



    private Callback callbackStatus = new Callback() {
        @Override public void onFailure(@NonNull Call call, @NonNull IOException e) {
            alertByCode(CheckActivity.this, 404);
            setLoaderVisibility(false);
        }

        @Override public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            proccessResponseStatus(response);
        }
    };

    private Callback callbackPerfil = new Callback() {
        @Override public void onFailure(@NonNull Call call, @NonNull IOException e) {
            alertByCode(CheckActivity.this, 404);
            setLoaderVisibility(false);
        }

        @Override public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            proccessResponsePerfil(response);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        setLoader(R.id.content_loader);
    }


    @Override
    protected void onResume() {
        super.onResume();
        URL.build(this);
        requestStatus();
    }




    private void requestStatus() {

        setLoaderVisibility(true);

        runOnUiThread(new Runnable() {
            @Override public void run() {
                ( (TextView) findViewById(R.id.txv_status_label) ).setText(R.string.validando_status);
            }
        });

        Cambista local = new CambistaContract(this).first();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url( URL.CAMBISTAS + "status/" + local.getWebToken())
                .build();

        client.newCall(request).enqueue(callbackStatus);
    }

    private void requestPerfil() {

        setLoaderVisibility(true);

        runOnUiThread(new Runnable() {
            @Override public void run() {
                ( (TextView) findViewById(R.id.txv_status_label) ).setText(R.string.verifica_perfil);
            }
        });

        Cambista local = new CambistaContract(this).first();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url( URL.CAMBISTAS + "perfil/" + local.getWebToken())
                .build();

        client.newCall(request).enqueue(callbackPerfil);
    }




    private void proccessResponseStatus(Response response) throws IOException {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                proccessStatus(StatusResponse.receive(response.body().string()));
            }
            else {
                setLoaderVisibility(false);
                alert(this, R.string.alert_http_response_empty);
            }
        }
        else {
            setLoaderVisibility(false);
            alertByCode(this, response.code());
        }
    }

    private void proccessResponsePerfil(Response response) throws IOException {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                proccessPerfil(PerfilResponse.receive(response.body().string()));
            }
            else {
                setLoaderVisibility(false);
                alert(this, R.string.alert_http_response_empty);
            }
        }
        else {
            setLoaderVisibility(false);
            alertByCode(this, response.code());
        }
    }



    private void proccessStatus(StatusResponse response) {
        if (response.code == 200) {

            if (response.body.bloqueado()) {
                displayInfo(R.id.content_bloqueado);
            }
            else if (response.body.deposito > 0) {

                final StatusResponse fnResponse = response;

                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        ((TextView)findViewById(R.id.txv_deposito_valor)).setText(
                                NumberFormat.getCurrencyInstance().format( fnResponse.body.deposito )
                        );
                    }
                });

                displayInfo(R.id.content_deposito);
            }
            else {
                requestPerfil();
            }

        }
        else {
            alertByCode(this, response.code);
            finishAndRemoveTask();
        }
    }

    private void proccessPerfil(PerfilResponse response) {
        if (response.code == 200) {

            if (response.body != null) {
                PerfilContract contract = new PerfilContract(CheckActivity.this);
                contract.insert(response.body);
                main();
            }
            else {
                alert(this, R.string.alert_http_response_empty);
                finishAndRemoveTask();
            }

        }
        else {
            alertByCode(this, response.code);
            finishAndRemoveTask();
        }
    }




    private void main() {
        startActivity(new Intent(CheckActivity.this, MainActivity.class));
        finish();
    }



    private void displayInfo(int resource) {

        final int fnRes = resource;

        runOnUiThread(new Runnable() {
            @Override public void run() {
                findViewById(fnRes).setVisibility(View.VISIBLE);
            }
        });
    }
}

package net.kingbets.cambista.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.contracts.CambistaContract;
import net.kingbets.cambista.model.local.Cambista;
import net.kingbets.cambista.model.remote.responses.PartidaResponse;
import net.kingbets.cambista.utils.URL;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class PartidasFragment extends BaseFragment {



    public static PartidasFragment newInstance(@NonNull Context context) {
        PartidasFragment fragment = new PartidasFragment();
        fragment.setContext(context);
        return fragment;
    }



    private Callback callback = new Callback() {
        @Override public void onFailure(@NonNull Call call, @NonNull IOException e) {

        }

        @Override public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            proccessResponse(response);
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_partidas, container, false);
        this.view = view;
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        requestPartidas();
    }



    private void requestPartidas() {
        //setLoaderVisibility(true);

        Cambista local = new CambistaContract(context).first();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL.PARTIDAS + "listar/" + local.getWebToken())
                .build();

        client.newCall(request).enqueue(callback);
    }


    private void proccessResponse(Response response) throws IOException {

        if (response.isSuccessful()) {
            if (response.body() != null) {
                onSuccessResponse( PartidaResponse.receive(response.body().string()) );
            }
            //else {

            //}
        }
        //else {

        //}
    }



    private void onSuccessResponse(PartidaResponse response) {

        //if (response != null && response.body.size() > 0) {

        //}
    }
}

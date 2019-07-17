package net.kingbets.cambista.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.contracts.CambistaContract;
import net.kingbets.cambista.model.local.Cambista;
import net.kingbets.cambista.model.remote.responses.CampeonatoResponse;
import net.kingbets.cambista.utils.URL;
import net.kingbets.cambista.view.adapters.CampeonatoAdapter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class CampeonatosFragmnet extends BaseFragment {



    public static CampeonatosFragmnet newInstance(Context context) {
        CampeonatosFragmnet fragmnet = new CampeonatosFragmnet();
        fragmnet.setContext(context);
        fragmnet.setLoader(R.id.content_loader);
        return fragmnet;
    }



    private Callback callback = new Callback() {
        @Override public void onFailure(@NonNull Call call, @NonNull IOException e) {
            alertByCode(404);
            setLoaderVisibility(false);
        }

        @Override public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            proccessResponse(response);
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_campeonatos, container, false);
        setView(view);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        requestCampeonatos();
    }



    private void requestCampeonatos() {
        setLoaderVisibility(true);

        Cambista local = new CambistaContract(context).first();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL.CAMPEONATOS + "listar/" + local.getWebToken())
                .build();

        client.newCall(request).enqueue(callback);
    }



    private void proccessResponse(Response response) throws IOException {

        if (response.isSuccessful()) {
            if (response.body() != null) {
                onSuccessResponse( CampeonatoResponse.receive(response.body().string()) );
            }
            else {
                alert(R.string.alert_http_response_empty);
            }
        }
        else {
            alertByCode(response.code());
        }

        setLoaderVisibility(false);
    }



    public void onSuccessResponse(CampeonatoResponse response) {

        if (response.body != null && response.body.size() > 0) {

            if (getActivity() != null) {

                final CampeonatoAdapter adapter = new CampeonatoAdapter(context, response.body);
                final RecyclerView recycler = view.findViewById(R.id.recycler_campeonatos);

                getActivity().runOnUiThread(new Runnable() {
                    @Override public void run() {
                        recycler.setLayoutManager(new LinearLayoutManager( context ));
                        recycler.setAdapter(adapter);
                    }
                });
            }
        }
        else {
            alert("Nenhum campeonato encontrado!");
        }
    }
}

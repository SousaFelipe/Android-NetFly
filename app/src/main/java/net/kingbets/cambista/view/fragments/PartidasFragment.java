package net.kingbets.cambista.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.contracts.CambistaContract;
import net.kingbets.cambista.model.local.Cambista;
import net.kingbets.cambista.model.remote.responses.CampeonatoPartidasResponse;
import net.kingbets.cambista.model.remote.responses.PartidaResponse;
import net.kingbets.cambista.utils.URL;
import net.kingbets.cambista.view.adapters.CampeonatoAdapter;
import net.kingbets.cambista.view.adapters.CampeonatoPartidaAdapter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class PartidasFragment extends BaseFragment {

    private static final String HOJE        = "hoje";
    private static final String AMANHA      = "amanha";
    private static final String PROXIMOS    = "proximos";



    private String PERIODO = HOJE;



    private CardView hoje;
    private CardView amanha;
    private CardView proximos;

    private RecyclerView recycler;
    private CampeonatoPartidaAdapter adapter;



    public static PartidasFragment newInstance(@NonNull Context context) {
        PartidasFragment fragment = new PartidasFragment();
        fragment.setContext(context);
        fragment.setLoader(R.id.content_loader);
        fragment.setInfo(R.id.content_info_empty);
        return fragment;
    }



    private Callback callback = new Callback() {
        @Override public void onFailure(@NonNull Call call, @NonNull IOException e) {
            Log.e(getClass().getSimpleName(), "onFailure: ", e);
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
    public void onStart() {
        super.onStart();

        inflateListView();
        inflateWidgets();

        selecionarWidget(hoje);
    }


    @Override
    public void onResume() {
        super.onResume();
        requestPartidas();
    }



    private void inflateWidgets() {

        hoje = view.findViewById(R.id.widget_hoje);
        hoje.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                PERIODO = HOJE;
                selecionarWidget(hoje);
                requestPartidas();
            }
        });

        amanha = view.findViewById(R.id.widget_amanha);
        amanha.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                PERIODO = AMANHA;
                selecionarWidget(amanha);
                requestPartidas();
            }
        });

        proximos = view.findViewById(R.id.widget_proximos);
        proximos.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                PERIODO = PROXIMOS;
                selecionarWidget(proximos);
                requestPartidas();
            }
        });
    }



    private void inflateListView() {
        adapter = new CampeonatoPartidaAdapter(context);
        recycler = view.findViewById(R.id.recycler_partidas);
    }



    private void requestPartidas() {

        setLoaderVisibility(true);
        setInfoVisibility(false);

        Cambista local = new CambistaContract(context).first();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL.PARTIDAS + "listar/" + local.getWebToken() + "/" + PERIODO)
                .build();

        client.newCall(request).enqueue(callback);
    }



    private void proccessResponse(Response response) throws IOException {

        if (response.isSuccessful()) {
            if (response.body() != null) {
                onSuccessResponse(CampeonatoPartidasResponse.receive( response.body().string() ));
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



    private void onSuccessResponse(CampeonatoPartidasResponse response) {

        if (response.body.size() > 0) {

            adapter.setDataList(response.body);

            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override public void run() {
                        LinearLayoutManager layoutManager = new LinearLayoutManager( context );
                        recycler.setLayoutManager( layoutManager );
                        recycler.setAdapter(adapter);
                        layoutManager.scrollToPositionWithOffset(0, 0);
                    }
                });
            }
        }
        else if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override public void run() {
                    adapter.clear();
                }
            });
            setInfoVisibility(true);
        }
    }
}

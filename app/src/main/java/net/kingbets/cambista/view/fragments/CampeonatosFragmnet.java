package net.kingbets.cambista.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.responses.CampeonatoResponse;
import net.kingbets.cambista.utils.Connection;
import net.kingbets.cambista.utils.URL;
import net.kingbets.cambista.view.adapters.CampeonatoAdapter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class CampeonatosFragmnet extends BaseFragment {



    private static final String NACIONAL         = "S";
    private static final String INTERNACIONAL    = "N";

    private String NACIONALIDADE = NACIONAL;

    private CardView nacionais;
    private CardView internacionais;

    private RecyclerView recycler;
    private CampeonatoAdapter adapter;



    public static CampeonatosFragmnet newInstance(Context context) {
        CampeonatosFragmnet fragment = new CampeonatosFragmnet();
        fragment.setContext(context);
        fragment.setLoader(R.id.content_loader);
        fragment.setInfo(R.id.content_info_empty);
        return fragment;
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
    public void onStart() {
        super.onStart();
        inflateListView();
        inflateWidgets();
        selecionarWidget(nacionais);
    }


    @Override
    public void onResume() {
        super.onResume();
        request();
    }



    private void inflateWidgets() {

        nacionais = view.findViewById(R.id.widget_nacionais);
        nacionais.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                NACIONALIDADE = NACIONAL;
                request();
                selecionarWidget(nacionais);
            }
        });

        internacionais = view.findViewById(R.id.widget_internacionais);
        internacionais.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                NACIONALIDADE = INTERNACIONAL;
                request();
                selecionarWidget(internacionais);
            }
        });
    }



    private void inflateListView() {
        adapter = new CampeonatoAdapter(context);
        recycler = view.findViewById(R.id.recycler_campeonatos);
    }


    @Override
    public void request() {
        setLoaderVisibility(true);
        setInfoVisibility(false);

        OkHttpClient client = Connection.getClientWithAuthHeader(getContext());

        Request request = new Request.Builder()
                .url(URL.CAMPEONATOS + "listar/" + NACIONALIDADE)
                .build();

        client.newCall(request).enqueue(callback);
    }



    private void proccessResponse(Response response) throws IOException {

        if (response.isSuccessful()) {
            if (response.body() != null) {

                final String fnBodyString = response.body().string();

                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override public void run() {
                            onSuccessResponse(CampeonatoResponse.receive(fnBodyString));
                        }
                    });
                }
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
        if (response.body.size() > 0) {
            adapter.setDataList(response.body);
            recycler.setLayoutManager(new LinearLayoutManager( context ));
            recycler.setAdapter(adapter);
        }
        else if (getActivity() != null) {
            adapter.clear();
            setInfoVisibility(true);
        }
    }
}

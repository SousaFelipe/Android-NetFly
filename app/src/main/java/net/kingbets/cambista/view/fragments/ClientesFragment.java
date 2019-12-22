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
import net.kingbets.cambista.http.responses.ClienteResponse;
import net.kingbets.cambista.utils.Connection;
import net.kingbets.cambista.utils.URL;
import net.kingbets.cambista.view.adapters.ClienteAdapter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ClientesFragment extends BaseFragment {



    private static final String REQUEST_BLOQUEADOS = "bloqueasdos";
    private static final String REQUEST_DESBLOQUEADOS = "desbloqueados";



    private String requestParam = REQUEST_DESBLOQUEADOS;

    private CardView desbloqueados;
    private CardView bloqueados;

    private RecyclerView recycler;
    private ClienteAdapter adapter;



    public static ClientesFragment newInstance(Context context) {
        ClientesFragment fragment = new ClientesFragment();
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
        View view = inflater.inflate(R.layout.fragment_clientes, container, false);
        setView(view);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        inflateWidgets();
        inflateListView();
        selecionarWidget(desbloqueados);
        request();
    }



    private void inflateWidgets() {

        desbloqueados = view.findViewById(R.id.widget_desbloqueados);
        desbloqueados.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                requestParam = REQUEST_DESBLOQUEADOS;
                selecionarWidget(desbloqueados);
                request();
            }
        });

        bloqueados = view.findViewById(R.id.widget_bloqueados);
        bloqueados.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                requestParam = REQUEST_BLOQUEADOS;
                selecionarWidget(bloqueados);
                request();
            }
        });
    }



    private void inflateListView() {
        adapter = new ClienteAdapter(context);
        adapter.setParent(this);
        adapter.setFragmentManager(getFragmentManager());
        recycler = view.findViewById(R.id.recycler_clientes);
    }


    @Override
    public void request() {
        setLoaderVisibility(true);
        setInfoVisibility(false);

        OkHttpClient client = Connection.getClientWithAuthHeader(getContext());

        Request request = new Request.Builder()
                .url(URL.CLIENTES + "listar/" + requestParam)
                .build();

        client.newCall(request).enqueue(callback);
    }



    private void proccessResponse(@NonNull Response response) throws IOException {

        if (response.isSuccessful()) {
            if (response.body() != null) {

                final String fnBodyString = response.body().string();

                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override public void run() {
                            onSuccessResponse(ClienteResponse.receive(fnBodyString));
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



    public void onSuccessResponse(@NonNull ClienteResponse response) {
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

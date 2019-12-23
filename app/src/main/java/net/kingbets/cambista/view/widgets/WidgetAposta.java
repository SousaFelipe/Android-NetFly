package net.kingbets.cambista.view.widgets;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.responses.ApostaResponse;
import net.kingbets.cambista.utils.Config;
import net.kingbets.cambista.utils.Connection;
import net.kingbets.cambista.utils.DateTime;
import net.kingbets.cambista.utils.URL;
import net.kingbets.cambista.view.dialogs.DialogCriaCupom;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WidgetAposta extends Widget {



    private DialogCriaCupom parent;
    private View view;

    private TextView txvFutebol;
    private TextView txvCampeonato;
    private TextView txvEquipes;
    private TextView txvStatus;
    private ImageView btnDeleteAposta;
    private View contentLoader;



    private Callback callbackRemove = new Callback() {
        @Override public void onFailure(@NonNull Call call, @NonNull IOException e) {
            Log.e(getClass().getSimpleName(), "onFailure: ", e);
        }

        @Override public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            proccessRemoveResponse(response);
        }
    };



    public WidgetAposta(Context context, Bet bet) {
        this.view = LayoutInflater.from(context).inflate(R.layout.item_cupom_aposta, null, false);

        setBet(bet);
        setContext(context);

        setTxvTitulo(view.findViewById(R.id.txv_cupom_titulo_aposta));
        setTxvOdd(view.findViewById(R.id.txv_cupom_aposta_cotacao));

        txvFutebol = view.findViewById(R.id.txv_cupom_futebol);
        txvCampeonato = view.findViewById(R.id.txv_cupom_campeonato);
        txvEquipes = view.findViewById(R.id.txv_cupom_equipes);
        txvStatus = view.findViewById(R.id.txv_cupom_aposta_status);

        btnDeleteAposta = view.findViewById(R.id.btn_delete_aposta);
        contentLoader = view.findViewById(R.id.content_loader);
    }



    public WidgetAposta build() {

        displayFutebol();
        displayCampeonato();
        displayEquipes();
        displayStatus();

        displayTitulo();
        displayOdd();

        btnDeleteAposta.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                removeBet();
            }
        });

        return this;
    }



    public View getView() {
        return view;
    }



    private void removeBet() {
        contentLoader.setVisibility(View.VISIBLE);
        btnDeleteAposta.setVisibility(View.GONE);

        OkHttpClient client = Connection.getClientWithAuthHeader(context);
        FormBody.Builder formBuilder = new FormBody.Builder();

        formBuilder.add("device", Config.getDeviceInfo(context));
        formBuilder.add("partida", String.valueOf(bet.partida.id));

        Request request = new Request.Builder()
                .method("DELETE", formBuilder.build())
                .url(URL.APOSTA + "remover")
                .build();

        client.newCall(request).enqueue(callbackRemove);
    }

    private void proccessRemoveResponse(@NonNull Response response) throws IOException {
        if (response.isSuccessful()) {
            if (response.body() != null) {

                final String fnBodyString = response.body().string();

                if (parent.getActivity() != null) {
                    parent.getActivity().runOnUiThread(new Runnable() {
                        @Override public void run() {
                            onSuccessRemoveResponse(ApostaResponse.receive(fnBodyString));
                        }
                    });
                }
            }
        }
    }

    private void onSuccessRemoveResponse(@NonNull ApostaResponse.Status status) {

        if (status.equals(ApostaResponse.Status.OK)) {
            parent.displayBets();
            parent.refresh();
        }
        else {
            showResponse(parent.getActivity(), status);
        }

        contentLoader.setVisibility(View.GONE);
        btnDeleteAposta.setVisibility(View.VISIBLE);
    }



    public void setParent(DialogCriaCupom parent) {
        this.parent = parent;
    }



    private void displayFutebol() {

        String data = DateTime.getInlineDate( DateTime.getDateFromString(bet.partida.data), "dd/MM/yyyy" );
        String hora = DateTime.compact(bet.partida.inicio);

        String futebol = "Futebol - " + data + "  às  " + hora;
        txvFutebol.setText(futebol);
    }

    private void displayCampeonato() {
        txvCampeonato.setText(bet.partida.campeonato);
    }

    private void displayEquipes() {
        String equipes = bet.partida.casa + " x " + bet.partida.fora;
        txvEquipes.setText(equipes);
    }

    private void displayStatus() {
        txvStatus.setText(bet.status);
    }



    public void hideStatus() {
        getView().findViewById(R.id.layout_status).setVisibility(View.GONE);
    }
}

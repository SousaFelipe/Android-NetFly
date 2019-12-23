package net.kingbets.cambista.view.widgets;


import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.apostas.BetStack;
import net.kingbets.cambista.http.responses.ApostaResponse;
import net.kingbets.cambista.utils.Config;
import net.kingbets.cambista.utils.Connection;
import net.kingbets.cambista.utils.URL;
import net.kingbets.cambista.view.fragments.BaseFragment;
import net.kingbets.cambista.view.fragments.PartidasFragment;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WidgetOdd extends Widget implements View.OnClickListener {



    enum State {
        CLICKABLE, ON_REQUEST
    }


    private boolean selected = false;
    private State state = State.CLICKABLE;

    protected LinearLayout layout;
    protected PartidasFragment parent;

    private int selectedResource;
    private int requestResource;



    private Callback callbackInsert = new Callback() {
        @Override public void onFailure(@NonNull Call call, @NonNull IOException e) {
            Log.e(getClass().getSimpleName(), "onFailure: ", e);
        }

        @Override public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            proccessInsertResponse(response);
        }
    };

    private Callback callbackRemove = new Callback() {
        @Override public void onFailure(@NonNull Call call, @NonNull IOException e) {
            Log.e(getClass().getSimpleName(), "onFailure: ", e);
        }

        @Override public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            proccessRemoveResponse(response);
        }
    };



    public WidgetOdd(View container) {

        setContext(container.getContext());

        this.layout = (LinearLayout) container;
        this.layout.setOnClickListener(this);

        setTxvTitulo(this.layout.getChildAt(0));
        setTxvOdd(this.layout.getChildAt(1));

        this.selectedResource = -1;
        this.requestResource = -1;
    }

    public WidgetOdd(Bet bet, View container) {
        this(container);
        this.bet = bet;
    }

    public WidgetOdd(Bet bet, View container, BaseFragment parent) {
        this(container);
        this.bet = bet;
        this.parent = (PartidasFragment) parent;
    }



    public void setParent(PartidasFragment parent) {
        this.parent = parent;
    }

    public void setSelectedResource(int selectedResource) {
        this.selectedResource = selectedResource;
    }

    public void setRequestResource(int requestResource) {
        this.requestResource = requestResource;
    }



    public boolean refresh() {

        boolean isSameOdd = BetStack.instance().isSameOdd(this.bet);

        if (isSameOdd) {
            select();
        }
        else {
            init();
        }

        txvOdd.setText(getTextOdd());

        return isSameOdd;
    }



    private void insertBet() {
        request();

        OkHttpClient client = Connection.getClientWithAuthHeader(context);

        Request request = new Request.Builder()
                .method("POST", bet.getRequestBody(context))
                .url(URL.APOSTA + "inserir")
                .build();

        client.newCall(request).enqueue(callbackInsert);
    }

    private void proccessInsertResponse(@NonNull Response response) throws IOException {

        if (response.isSuccessful()) {
            if (response.body() != null) {

                final String fnBodyString = response.body().string();

                if (parent.getActivity() != null) {
                    parent.getActivity().runOnUiThread(new Runnable() {
                        @Override public void run() {
                            onSuccessInsertResponse(ApostaResponse.receive(fnBodyString));
                        }
                    });
                }
            }
        }
        else {
            Log.e("INSERT", "proccessInsertResponse: " + response.code());
        }

        state = State.CLICKABLE;
    }

    private void onSuccessInsertResponse(@NonNull ApostaResponse.Status status) {

        if (status.equals(ApostaResponse.Status.OK)) {
            select();
        }
        else {
            showResponse(parent.getActivity(), status);
            init();
        }

        refresh();
    }



    private void removeBet() {
        request();

        OkHttpClient client = Connection.getClientWithAuthHeader(context);
        FormBody.Builder formBuilder = new FormBody.Builder();

        formBuilder.add("device", Config.getDeviceInfo(context));
        formBuilder.add("partida", String.valueOf(this.bet.partida.id));

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

        state = State.CLICKABLE;
    }

    private void onSuccessRemoveResponse(@NonNull ApostaResponse.Status status) {

        if (status.equals(ApostaResponse.Status.OK)) {
            init();
        }
        else {
            showResponse(parent.getActivity(), status);
            select();
        }

        refresh();
    }



    private void init() {

        final Resources res = layout.getContext().getResources();

        if (parent.getActivity() != null) {
            parent.getActivity().runOnUiThread(new Runnable() {
                @Override public void run() {
                    layout.setBackgroundColor(Color.TRANSPARENT);
                    txvTitulo.setTextColor( res.getColor(R.color.textColorSecondary) );
                    txvOdd.setTextColor( res.getColor(R.color.textColorPrimary) );
                }
            });
        }

        selected = false;
    }

    private void request() {
        state = State.ON_REQUEST;

        final Resources res = layout.getContext().getResources();

        if (parent.getActivity() != null) {
            parent.getActivity().runOnUiThread(new Runnable() {
                @Override public void run() {

                    if (requestResource != -1) {
                        layout.setBackgroundResource(requestResource);
                    }
                    else {
                        layout.setBackgroundColor(res.getColor(R.color.textColorTertiary));
                    }

                    txvTitulo.setTextColor( res.getColor(R.color.textColorPrimary) );
                    txvOdd.setTextColor( res.getColor(R.color.textColorPrimaryInverse) );
                }
            });
        }
    }

    private void select() {

        final Resources res = layout.getContext().getResources();

        if (parent.getActivity() != null) {
            parent.getActivity().runOnUiThread(new Runnable() {
                @Override public void run() {

                    if (selectedResource != -1) {
                        layout.setBackgroundResource(selectedResource);
                    }
                    else {
                        layout.setBackgroundColor(res.getColor(R.color.colorAccent));
                    }

                    txvTitulo.setTextColor( res.getColor(R.color.textColorPrimary) );
                    txvOdd.setTextColor( res.getColor(R.color.textColorPrimaryInverse) );
                }
            });
        }

        selected = true;
    }


    @Override
    public void onClick(View v) {
        if (state.equals(State.CLICKABLE)) {
            if (selected) {
                removeBet();
            }
            else {
                insertBet();
            }
        }
    }
}

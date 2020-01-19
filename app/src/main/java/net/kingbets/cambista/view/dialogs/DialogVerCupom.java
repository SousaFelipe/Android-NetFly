package net.kingbets.cambista.view.dialogs;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.model.contracts.CambistaContract;
import net.kingbets.cambista.model.Cambista;
import net.kingbets.cambista.http.responses.CupomResponse;
import net.kingbets.cambista.utils.Connection;
import net.kingbets.cambista.utils.DateTime;
import net.kingbets.cambista.utils.Str;
import net.kingbets.cambista.utils.URL;
import net.kingbets.cambista.view.ImpressaoActivity;
import net.kingbets.cambista.view.fragments.PartidasFragment;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class DialogVerCupom extends BaseDialog {
    public static final String TAG = "DIALOG_VER_CUPOM";



    private String codigo;

    private ImageView imvClose;
    private TextView txvCompartilhar;
    private TextView txvImprimir;

    private TextView txvCodigo;
    private TextView txvDataHora;
    private TextView txvCambista;
    private TextView txvContato;
    private TextView txvApostador;

    private TextView txvQuantJogos;
    private TextView txvCotacao;
    private TextView txvTotalApostado;
    private TextView txvPossivelRetorno;

    private Intent mainIntent;



    private Callback callback = new Callback() {
        @Override public void onFailure(@NonNull Call call, @NonNull IOException e) {
            Log.e(TAG, "onFailure: " + e.getMessage());
        }

        @Override public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            proccessCupom(response);
        }
    };



    public static void display(Context context, FragmentManager manager, String codigo) {

        DialogVerCupom dialog = new DialogVerCupom();
        dialog.setCodigo(codigo);
        dialog.setIntent(context);

        if (manager != null) {
            dialog.show(manager, TAG);
        }
    }



    public static void display(PartidasFragment parent, String codigo) {

        DialogVerCupom dialog = new DialogVerCupom();
        dialog.setParent(parent);
        dialog.setCodigo(codigo);
        dialog.setIntent(parent.getContext());

        if (parent.getFragmentManager() != null) {
            dialog.show(parent.getFragmentManager(), TAG);
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_ver_cupom, container, false);

        setRootView(view);
        setLoader(R.id.content_loader_main);

        imvClose = view.findViewById(R.id.imv_close);
        txvCompartilhar = view.findViewById(R.id.txv_compartilhar);
        txvImprimir = view.findViewById(R.id.txv_imprimir);

        txvCodigo = view.findViewById(R.id.txv_cupom_codigo);
        txvDataHora = view.findViewById(R.id.txv_cupom_data_hora);
        txvCambista = view.findViewById(R.id.txv_cupom_cambista);
        txvContato = view.findViewById(R.id.txv_cupom_contato);
        txvApostador = view.findViewById(R.id.txv_cupom_apostador);

        layoutContentApostas = view.findViewById(R.id.layout_cupom_content_apostas);

        txvQuantJogos = view.findViewById(R.id.txv_cupom_quant_jogos);
        txvCotacao = view.findViewById(R.id.txv_cupom_cotacao);
        txvTotalApostado = view.findViewById(R.id.txv_cupom_total_apostado);
        txvPossivelRetorno = view.findViewById(R.id.txv_cupom_possivel_retorno);

        start();

        return view;
    }


    @Override
    public void onDismiss(DialogInterface dialog) {


        if (parent != null) {
            parent.request();
        }

        super.onDismiss(dialog);
    }



    public void setParent(PartidasFragment parent) {
        this.parent = parent;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setIntent(Context context) {
        this.mainIntent = new Intent(context, ImpressaoActivity.class);
        this.mainIntent.putExtra("BILHETE", codigo);
    }



    private void start() {
        Dialog dialog = getDialog();

        if (dialog != null) {

            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            if (dialog.getWindow() != null) {
                dialog.getWindow().setLayout(width, height);
                dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide);
            }
        }

        inflateControls();
        buscarCupom();
    }



    private void inflateControls() {

        imvClose.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                dismiss();
            }
        });

        txvCompartilhar.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                compartilhar();
            }
        });

        txvImprimir.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(mainIntent);
            }
        });
    }



    private void compartilhar() {
        if (getContext() != null) {

            String message = "https://kingbets.net/bilhetes/consultar/" + codigo;

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, message);
            getContext().startActivity(intent);
        }
    }



    private void buscarCupom() {
        setLoaderVisibility(true);

        OkHttpClient client = Connection.getClientWithAuthHeader(getContext());

        Request request = new Request.Builder()
                .url( URL.CUPONS + "buscar/" + codigo)
                .build();

        client.newCall(request).enqueue(callback);
    }

    private void proccessCupom(@NonNull Response response) throws IOException {
        if (response.isSuccessful()) {
            if (response.body() != null) {

                final String fnBodyString = response.body().string();

                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override public void run() {
                            mostrarCupom(CupomResponse.receiveOne(fnBodyString));
                        }
                    });
                }
            }
            else {
                alert("Erro ao buscar cupom.", new View.OnClickListener() {
                    @Override public void onClick(View v) { buscarCupom(); }
                });
            }
        }
        else {
            alert("Erro ao buscar cupom.", new View.OnClickListener() {
                @Override public void onClick(View v) { buscarCupom(); }
            });
        }
    }

    private void mostrarCupom(CupomResponse response) {
        Cambista cambista = new CambistaContract(getContext()).first();

        String data = DateTime.getInlineDate(DateTime.getDateFromString(response.body.data), "dd/MM/yyyy");
        String hora = DateTime.compact(response.body.hora);
        String dataHora = data + " às " + hora;

        txvCodigo.setText(response.body.codigo);
        txvDataHora.setText(dataHora);
        txvCambista.setText(Str.nomeResumido(cambista.nome));
        txvContato.setText(Str.mask(cambista.contato, "(##) # ####-####"));
        txvApostador.setText(response.body.apostador);

        mostrarApostas(response.body.bets);

        txvQuantJogos.setText(String.valueOf(response.body.quantApostas));
        txvCotacao.setText(Str.getCurrency(response.body.cotacao));
        txvTotalApostado.setText(Str.getCurrency(response.body.valorApostado));
        txvPossivelRetorno.setText(Str.getCurrency(response.body.possivelRetorno));
    }

    private void mostrarApostas(List<Bet> bets) {

        for (Bet bet : bets) {

            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_cupom_aposta, null, false);
            view.findViewById(R.id.btn_delete_aposta).setVisibility(View.GONE);

            TextView txvFutebol = view.findViewById(R.id.txv_cupom_futebol);
            TextView txvCampeonato = view.findViewById(R.id.txv_cupom_campeonato);
            TextView txvEquipes = view.findViewById(R.id.txv_cupom_equipes);
            TextView txvTitulo = view.findViewById(R.id.txv_cupom_titulo_aposta);
            TextView txvCotacao = view.findViewById(R.id.txv_cupom_aposta_cotacao);
            TextView txvStatus = view.findViewById(R.id.txv_cupom_aposta_status);

            String data = DateTime.getInlineDate(DateTime.getDateFromString(bet.partida.data), "dd/MM/yyyy");
            String inicio = DateTime.compact(bet.partida.inicio);
            String futebol = "Futebol - " + data + " às " + inicio;
            String equipes = bet.partida.casa + " x " + bet.partida.fora;

            txvFutebol.setText( futebol );
            txvCampeonato.setText( bet.partida.campeonato );
            txvEquipes.setText( equipes );
            txvTitulo.setText( bet.titulo );
            txvCotacao.setText( String.format(Locale.getDefault(), "%.2f", bet.cotacao) );
            txvStatus.setText( bet.getStatusText() );

            layoutContentApostas.addView(view);
        }

        setLoaderVisibility(false);
    }
}

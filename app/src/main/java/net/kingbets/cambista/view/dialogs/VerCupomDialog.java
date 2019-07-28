package net.kingbets.cambista.view.dialogs;


import android.app.Dialog;
import android.content.DialogInterface;
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
import net.kingbets.cambista.model.contracts.CambistaContract;
import net.kingbets.cambista.model.local.Cambista;
import net.kingbets.cambista.model.local.apostas.Single;
import net.kingbets.cambista.model.remote.apostas.Aposta;
import net.kingbets.cambista.model.responses.CupomResponse;
import net.kingbets.cambista.utils.DateTime;
import net.kingbets.cambista.utils.Str;
import net.kingbets.cambista.utils.URL;
import net.kingbets.cambista.view.fragments.PartidasFragment;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class VerCupomDialog extends BaseDialog {
    public static final String TAG = "DIALOG_VER_CUPOM";



    private String currentCupom;

    private ImageView imvClose;
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



    private Callback callback = new Callback() {
        @Override public void onFailure(@NonNull Call call, @NonNull IOException e) {
            Log.e(TAG, "onFailure: " + e.getMessage());
        }

        @Override public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            proccessCupom(response);
        }
    };



    public static void display(FragmentManager manager, String codigo) {

        VerCupomDialog dialog = new VerCupomDialog();
        dialog.setCurrentCupom(codigo);

        if (manager != null) {
            dialog.show(manager, TAG);
        }
    }



    public static void display(PartidasFragment parent, String codigo) {

        VerCupomDialog dialog = new VerCupomDialog();
        dialog.setParent(parent);
        dialog.setCurrentCupom(codigo);

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

        setLoader(R.id.content_loader);

        imvClose = view.findViewById(R.id.imv_close);
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

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        if (getContext() != null) {
            URL.build(getContext());
        }

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


    @Override
    public void onDismiss(DialogInterface dialog) {
        Single.instance().clear();

        if (parent != null) {
            parent.request();
        }

        super.onDismiss(dialog);
    }



    public void setParent(PartidasFragment parent) {
        this.parent = parent;
    }



    public void setCurrentCupom(String currentCupom) {
        this.currentCupom = currentCupom;
    }



    private void inflateControls() {

        imvClose.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                dismiss();
            }
        });

        txvImprimir.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

            }
        });
    }



    private void buscarCupom() {

        setLoaderVisibility(true);

        Cambista cambista = new CambistaContract(getContext()).first();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url( URL.CUPONS + "buscar/" + cambista.getWebToken() + "/" + currentCupom)
                .build();

        client.newCall(request).enqueue(callback);
    }



    private void proccessCupom(Response response) throws IOException {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                mostrarCupom( CupomResponse.receive(response.body().string()) );
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



    private void  mostrarCupom(CupomResponse response) {
        setLoaderVisibility(false);

        final CupomResponse finalResponse = response;

        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override public void run() {

                    Cambista cambista = new CambistaContract(getContext()).first();

                    String data = DateTime.getInlineDate(DateTime.getDateFromString(finalResponse.body.data), "dd/MM/yyyy");
                    String hora = DateTime.compact(finalResponse.body.hora);
                    String dataHora = data + " às " + hora;

                    txvCodigo.setText( finalResponse.body.codigo );
                    txvDataHora.setText( dataHora );
                    txvCambista.setText( Str.nomeResumido(cambista.nome) );
                    txvContato.setText( Str.mask(cambista.contato, "(##) # ####-####") );
                    txvApostador.setText( finalResponse.body.apostador );

                    mostrarApostas(finalResponse.body.apostas);

                    txvQuantJogos.setText( String.valueOf(finalResponse.body.quantApostas) );
                    txvCotacao.setText( Str.getCurrency(finalResponse.body.cotacao) );
                    txvTotalApostado.setText( Str.getCurrency(finalResponse.body.valorApostado ) );
                    txvPossivelRetorno.setText( Str.getCurrency(finalResponse.body.possivelRetorno) );
                }
            });
        }
    }



    private void mostrarApostas(List<Aposta> apostas) {
        for (Aposta aposta : apostas) {

            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_cupom_aposta, null, false);
            view.findViewById(R.id.btn_delete_aposta).setVisibility(View.GONE);

            TextView txvFutebol = view.findViewById(R.id.txv_cupom_futebol);
            TextView txvCampeonato = view.findViewById(R.id.txv_cupom_campeonato);
            TextView txvEquipes = view.findViewById(R.id.txv_cupom_equipes);
            TextView txvTitulo = view.findViewById(R.id.txv_cupom_titulo_aposta);
            TextView txvCotacao = view.findViewById(R.id.txv_cupom_aposta_cotacao);
            TextView txvStatus = view.findViewById(R.id.txv_cupom_aposta_status);

            String data = DateTime.getInlineDate(DateTime.getDateFromString(aposta.partida.data), "dd/MM/yyyy");
            String inicio = DateTime.compact(aposta.partida.inicio);
            String futebol = "Futebol - " + data + " às " + inicio;
            String equipes = aposta.partida.casa + " x " + aposta.partida.fora;

            txvFutebol.setText( futebol );
            txvCampeonato.setText( aposta.partida.campeonato );
            txvEquipes.setText( equipes );
            txvTitulo.setText( aposta.titulo );
            txvCotacao.setText( String.format(Locale.getDefault(), "%.2f", aposta.cotacao) );
            txvStatus.setText( getStatus(aposta.status) );

            layoutContentApostas.addView(view);
        }
    }



    private String getStatus(String status) {

        if (status.equals("G")) {
            return "Ganhou";
        }
        else if (status.equals("P")) {
            return "Perdeu";
        }

        return "Aberto";
    }
}

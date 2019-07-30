package net.kingbets.cambista.view.dialogs;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.contracts.CambistaContract;
import net.kingbets.cambista.model.contracts.PerfilContract;
import net.kingbets.cambista.model.local.Cambista;
import net.kingbets.cambista.model.local.Perfil;
import net.kingbets.cambista.model.local.apostas.Single;
import net.kingbets.cambista.utils.MoneyTextWatcher;
import net.kingbets.cambista.utils.Str;
import net.kingbets.cambista.utils.URL;
import net.kingbets.cambista.view.fragments.PartidasFragment;
import net.kingbets.cambista.view.widgets.Widget;
import net.kingbets.cambista.view.widgets.WidgetCupom;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class CriaCupomDialog extends BaseDialog {
    public static final String TAG = "DIALOG_CRIA_CUPOM";



    private ImageView imvClose;
    private TextView txvCancelar;

    private TextView txvQuantJogos;
    private TextView txvCotacao;
    private TextView txvTotalApostado;
    private TextView txvPossivelRetorno;

    private EditText inputValor;
    private EditText inputApostador;
    private CardView btnSalvarCupom;



    private Callback callbackCupom = new Callback() {
        @Override public void onFailure(@NonNull Call call, @NonNull IOException e) {
            Log.e(TAG, "onFailure: " + e.getMessage());
        }

        @Override public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            proccessCupom(response);
        }
    };

    private Callback callbackApostas = new Callback() {
        @Override public void onFailure(@NonNull Call call, @NonNull IOException e) {
            Log.e(TAG, "onFailure: " + e.getMessage());
        }

        @Override public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            proccessApostas(response);
        }
    };



    public static void display(PartidasFragment parent) {

        CriaCupomDialog dialog = new CriaCupomDialog();
        dialog.setParent(parent);

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
        View view = inflater.inflate(R.layout.dialog_cria_cupom, container, false);

        setLoader(R.id.content_loader);

        imvClose = view.findViewById(R.id.imv_close);
        txvCancelar = view.findViewById(R.id.txv_cancelar);
        layoutContentApostas = view.findViewById(R.id.layout_cupom_content_apostas);

        txvQuantJogos = view.findViewById(R.id.txv_cupom_quant_jogos);
        txvCotacao = view.findViewById(R.id.txv_cupom_cotacao);
        txvTotalApostado = view.findViewById(R.id.txv_cupom_total_apostado);
        txvPossivelRetorno = view.findViewById(R.id.txv_cupom_possivel_retorno);

        inputApostador = view.findViewById(R.id.input_apostador);
        inputValor = view.findViewById(R.id.input_valor);
        btnSalvarCupom = view.findViewById(R.id.btn_salvar_cupom);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();

        if (dialog != null) {

            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            if (dialog.getWindow() != null) {
                dialog.getWindow().setLayout(width, height);
                dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide);
            }
        }

        inflateTopControls();
        inflateBottomControls();

        mostrarApostas();
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        parent.request();
        super.onDismiss(dialog);
    }



    public void setParent(PartidasFragment parent) {
        this.parent = parent;
    }



    private void inflateTopControls() {

        imvClose.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                dismiss();
            }
        });

        txvCancelar.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Single.instance().clear();
                dismiss();
            }
        });
    }

    private void inflateBottomControls() {

        Single single = Single.instance();

        inputValor.setText( NumberFormat.getCurrencyInstance().format( single.valorApostado ) );
        inputValor.addTextChangedListener(new MoneyTextWatcher(this, inputValor));

        inputApostador.setText( single.apostador != null ? single.apostador : "" );
        inputApostador.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override public void afterTextChanged(Editable s) {
                Single.instance().apostador = s != null ? s.toString() : "";
            }
        });

        btnSalvarCupom.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { salvarCupom(); }
        });
    }



    private void salvarCupom() {
        setLoaderVisibility(true);

        if (inputApostador.getText() == null || inputApostador.getText().toString().length() <= 0) {
            alert("Você esqueceu o nome do apostador.", null);
            setLoaderVisibility(false);
        }
        else if (inputValor.getText() == null || Str.currencyToDouble( inputValor.getText().toString() ) < 2.0) {
            alert("A valor mínimo da aposta é R$ 2,00.", null);
            setLoaderVisibility(false);
        }
        else {
            OkHttpClient client = new OkHttpClient();
            RequestBody formBody = Single.instance().getPostData(getContext());

            Request request = new Request.Builder()
                    .method("POST", formBody)
                    .url( URL.CUPONS + "salvar")
                    .build();

            Call call = client.newCall(request);
            call.enqueue(callbackCupom);
        }
    }

    private void salvarApostas(String codigo) {
        if (codigo != null && getContext() != null) {

            setLoaderVisibility(true);

            Cambista cambista = new CambistaContract(getContext()).first();
            Single single = Single.instance();
            single.getApostasJSON();

            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("token",   cambista.token)
                    .add("cupom",   codigo)
                    .add("apostas", single.getApostasJSON().toString())
                    .build();

            Request request = new Request.Builder()
                    .method("POST", formBody)
                    .url( URL.APOSTA + "salvar")
                    .build();

            Call call = client.newCall(request);
            call.enqueue(callbackApostas);
        }
        else {
            alert("Erro ao salvar as apostas do cupom.", new View.OnClickListener() {
                @Override public void onClick(View v) { salvarCupom(); }
            });
            setLoaderVisibility(false);
        }
    }



    private void proccessCupom(Response response) throws IOException {
        if (response.isSuccessful()) {

            if (response.body() != null) {
                salvarApostas(response.body().string());
            }
            else {
                alert("Erro ao salvar o cupom.", new View.OnClickListener() {
                    @Override public void onClick(View v) { salvarCupom(); }
                });
            }

        }
        else {
            alert("Erro ao salvar o cupom.", new View.OnClickListener() {
                @Override public void onClick(View v) { salvarCupom(); }
            });
        }

        setLoaderVisibility(false);
    }

    private void proccessApostas(Response response) throws IOException {
        if (response.isSuccessful()) {
            if (response.body() != null) {

                String codigo = response.body().string();

                if (codigo.equals("error")) {
                    alert("Erro [500] ao salvar as apostas.", new View.OnClickListener() {
                        @Override public void onClick(View v) { salvarCupom(); }
                    });
                }
                else {
                    sair(codigo);
                }
            }
            else {
               alert("Erro [204] ao salvar as apostas.", new View.OnClickListener() {
                   @Override public void onClick(View v) { salvarCupom(); }
               });
            }
        }
        else {
            alert(response.message(), new View.OnClickListener() {
                @Override public void onClick(View v) { salvarCupom(); }
            });
        }
    }



    public void mostrarApostas() {

        if (layoutContentApostas.getChildCount() > 0) {
            layoutContentApostas.removeAllViews();
        }

        for (Widget w: Single.instance().widgets) {

            WidgetCupom widget = new WidgetCupom(getContext(), w);
            widget.hideStatus();
            widget.setParent(this);

            layoutContentApostas.addView( widget.build().getRootView() );
        }

        PerfilContract contract = new PerfilContract(getContext());

        if (layoutContentApostas.getChildCount() < contract.first().limiteApostas) {
            Single.instance().update( contract.first().limiteApostas );
            dismiss();
        }

        refresh();
    }



    public void refresh() {

        Single single = Single.instance();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        txvQuantJogos.setText(String.valueOf(single.widgets.size()));
        txvCotacao.setText( String.format(Locale.getDefault(), "%.2f", single.cotacao) );

        txvTotalApostado.setText( formatter.format(single.valorApostado)  );
        txvPossivelRetorno.setText( formatter.format(single.possivelRetorno)  );
    }



    public void sair(String codigo) {

        final String fnCodigo = codigo;

        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override public void run() {

                    Single single = Single.instance();
                    Perfil perfil = new PerfilContract(getContext()).first();

                    single.clear();
                    single.update( perfil.limiteApostas );

                    ((PartidasFragment)parent).mostrarCupom(fnCodigo);

                    dismiss();

                }
            });
        }
    }
}

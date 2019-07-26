package net.kingbets.cambista.view.dialogs;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.contracts.PerfilContract;
import net.kingbets.cambista.model.local.Perfil;
import net.kingbets.cambista.model.remote.apostas.Single;
import net.kingbets.cambista.utils.MoneyTextWatcher;
import net.kingbets.cambista.view.fragments.PartidasFragment;
import net.kingbets.cambista.view.widgets.Widget;
import net.kingbets.cambista.view.widgets.WidgetCupom;

import java.text.NumberFormat;
import java.util.Locale;


public class CupomDialog extends DialogFragment {

    public static final String TAG = "DIALOG_CUPOM";



    public static final int SALVAR = 1;
    public static final int IMPRIMIR = 2;


    private PartidasFragment parent;
    private int action = 0;

    private ImageView imvClose;
    private TextView txvCancelar;
    private TextView txvImprimir;

    private LinearLayout layoutContentApostas;

    private TextView txvQuantJogos;
    private TextView txvCotacao;
    private TextView txvTotalApostado;
    private TextView txvPossivelRetorno;

    private EditText inputValor;
    private EditText inputApostador;
    private CardView btnSalvarCupom;



    public static void display(PartidasFragment parent) {
        display(parent, SALVAR);
    }



    public static void display(PartidasFragment parent, int action) {

        CupomDialog dialog = new CupomDialog();
        dialog.setAction(action);
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
        View view = inflater.inflate(R.layout.dialog_cupom, container, false);

        imvClose = view.findViewById(R.id.imv_close);
        txvCancelar = view.findViewById(R.id.txv_cancelar);
        txvImprimir = view.findViewById(R.id.txv_imprimir);
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
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        switch (action) {

            case SALVAR:
                txvCancelar.setVisibility(View.VISIBLE);
                if (getView() != null) getView().findViewById(R.id.layout_info).setVisibility(View.GONE);
                break;

            case IMPRIMIR:
                txvImprimir.setVisibility(View.GONE);
                if (getView() != null) getView().findViewById(R.id.layout_info).setVisibility(View.VISIBLE);
                break;
        }
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
        parent.requestPartidas();
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

            }
        });

        txvImprimir.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

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
            @Override public void onClick(View v) {

            }
        });
    }



    public void mostrarApostas() {

        if (layoutContentApostas.getChildCount() > 0) {
            layoutContentApostas.removeAllViews();
        }

        for (Widget w: Single.instance().widgets) {

            WidgetCupom widget = new WidgetCupom(getContext(), w);
            widget.setParent(this);

            if (action == SALVAR) {
                widget.hideStatus();
            }

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



    public void setAction(int action) {
        this.action = action;
    }
}

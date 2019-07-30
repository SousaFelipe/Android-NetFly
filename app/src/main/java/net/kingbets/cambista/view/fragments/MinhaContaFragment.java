package net.kingbets.cambista.view.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.contracts.CambistaContract;
import net.kingbets.cambista.model.contracts.PerfilContract;
import net.kingbets.cambista.model.local.Cambista;
import net.kingbets.cambista.model.local.Perfil;
import net.kingbets.cambista.utils.Str;
import net.kingbets.cambista.view.CuponsActivity;
import net.kingbets.cambista.view.FinanceiroActivity;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class MinhaContaFragment extends BaseFragment {



    private Cambista cambista;
    private Perfil perfil;

    private TextView txvNomeCambista;
    private TextView txvNomeGerente;
    private TextView txvEmail;
    private TextView txvContato;
    private TextView txvLimite;

    private CardView cardCupons;
    private CardView cardCaixa;

    private TextView txvPontos;
    private FrameLayout barraContent;
    private FrameLayout barra;



    public static MinhaContaFragment newInstance(@NonNull Context context) {
        MinhaContaFragment fragment = new MinhaContaFragment();
        fragment.setContext(context);
        fragment.setLoader(R.id.content_loader);
        fragment.setInfo(R.id.content_info_empty);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_minha_conta, container, false);
        this.view = view;
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txvNomeCambista = view.findViewById(R.id.txv_nome_cambista);
        txvNomeGerente = view.findViewById(R.id.txv_nome_gerente);
        txvEmail = view.findViewById(R.id.txv_email);
        txvContato = view.findViewById(R.id.txv_telefone);
        txvLimite = view.findViewById(R.id.txv_limite);

        cardCupons = view.findViewById(R.id.card_cupons);
        cardCaixa = view.findViewById(R.id.card_caixa);

        txvPontos = view.findViewById(R.id.txv_xp);
        barraContent = view.findViewById(R.id.view_xp_content);
        barra = view.findViewById(R.id.view_xp);
    }


    @Override
    public void onStart() {
        super.onStart();

        cambista = new CambistaContract(getContext()).first();
        perfil = new PerfilContract(getContext()).first();

        txvNomeCambista.setText( cambista.nome.toUpperCase() );

        if (perfil.gerente != null && perfil.gerente.length() > 0) {
            txvNomeGerente.setText(perfil.gerente.toUpperCase());
        }
        else {
            txvNomeGerente.setVisibility(View.GONE);
        }

        txvEmail.setText( cambista.email );
        txvContato.setText( Str.mask(cambista.contato, "(##) # ####-####") );
        txvLimite.setText( Str.getCurrency(perfil.limite) );
        txvPontos.setText( String.format(Locale.getDefault(), "%.2f", perfil.xp) );

        mostrarXP();
        addControlsActions();
    }



    private void mostrarXP() {

        barraContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override public void onGlobalLayout() {

                barraContent.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int     width       = barraContent.getMeasuredWidth();
                double  percent     = ((perfil.xp * 100) / perfil.xpMaximo);
                int     newWidth    = (int) ((percent * width) / 100);

                barra.setLayoutParams(new FrameLayout.LayoutParams(newWidth, 16));
            }
        });
    }



    private void addControlsActions() {

        cardCupons.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().startActivity(new Intent(getContext(), CuponsActivity.class));
                }
            }
        });

        cardCaixa.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().startActivity(new Intent(getContext(), FinanceiroActivity.class));
                }
            }
        });
    }


    @Override
    public void request() {
        Log.i(getClass().getSimpleName(), "request: Empty!");
    }
}

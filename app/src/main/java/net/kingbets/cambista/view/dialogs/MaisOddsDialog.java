package net.kingbets.cambista.view.dialogs;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.remote.futebol.Partida;
import net.kingbets.cambista.view.fragments.PartidasFragment;
import net.kingbets.cambista.view.odds.AmbasMarcamView;
import net.kingbets.cambista.view.odds.DuplaChanceView;
import net.kingbets.cambista.view.odds.EmpateAnulaApostaView;
import net.kingbets.cambista.view.odds.PlacarView;
import net.kingbets.cambista.view.odds.pt.AmbasMarcamPView;
import net.kingbets.cambista.view.odds.pt.DuplaChancePView;
import net.kingbets.cambista.view.odds.pt.GolsMaisMenosPView;
import net.kingbets.cambista.view.odds.pt.ParOuImparPView;
import net.kingbets.cambista.view.odds.pt.ResultadoPView;
import net.kingbets.cambista.view.odds.st.AmbasMarcamSView;
import net.kingbets.cambista.view.odds.st.DuplaChanceSView;
import net.kingbets.cambista.view.odds.st.GolsMaisMenosSView;
import net.kingbets.cambista.view.odds.GolsMaisMenosView;
import net.kingbets.cambista.view.odds.IntervaloFinalView;
import net.kingbets.cambista.view.odds.ParOuImparView;
import net.kingbets.cambista.view.odds.ResultadoView;
import net.kingbets.cambista.view.odds.VencedorAmbasMarcamView;
import net.kingbets.cambista.view.odds.st.ParOuImparSView;
import net.kingbets.cambista.view.odds.st.ResultadoSView;


public class MaisOddsDialog extends DialogFragment {
    public static final String TAG = "DIALOG_MAIS_ODDS";



    private Toolbar toolbar;
    private Partida partida;
    private LinearLayout contentOdds;

    private PartidasFragment parent;

    private CardView principais;
    private CardView primeiroSegundotempo;
    private CardView lastWidget;



    public static void display(PartidasFragment parent, Partida partida) {

        MaisOddsDialog dialog = new MaisOddsDialog();
        dialog.setParent(parent);
        dialog.setPartida(partida);

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
        View view = inflater.inflate(R.layout.dialog_mais_odds, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        contentOdds = view.findViewById(R.id.content_odds);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                dismiss();
            }
        });

        inflateWidgets();
        selecionarWidget(principais);
        mostrarPrincipaisOdds();
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
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        parent.requestPartidas();
        super.onDismiss(dialog);
    }



    public void setParent(PartidasFragment parent) {
        this.parent = parent;
    }



    public void setPartida(Partida partida) {
        this.partida = partida;
    }



    private void inflateWidgets() {
        View view = getView();

        if (view != null) {

            principais = view.findViewById(R.id.widget_principais);
            principais.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    selecionarWidget(principais);
                    mostrarPrincipaisOdds();
                }
            });

            primeiroSegundotempo = view.findViewById(R.id.widget_primeiro_segundo_tempo);
            primeiroSegundotempo.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    selecionarWidget(primeiroSegundotempo);
                    mostrarPrimeiroSegundoTempo();
                }
            });
        }
    }



    private void selecionarWidget(CardView widget) {

        if (lastWidget != null) {
            lastWidget.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            ((TextView)lastWidget.getChildAt(0)).setTextColor(Color.parseColor("#263238"));
        }

        widget.setCardBackgroundColor(Color.parseColor("#FF5252"));
        ((TextView)widget.getChildAt(0)).setTextColor(Color.parseColor("#FFFFFF"));

        lastWidget = widget;
    }



    private void mostrarPrincipaisOdds() {
        Context context = getContext();

        if (context != null) {

            contentOdds.removeAllViews();
            partida.display(getView());

            if (partida.resultado != null) {
                ResultadoView resultadoFinal = new ResultadoView(context, partida.resultado).create().build();
                contentOdds.addView(resultadoFinal.getRootView());
            }

            if (partida.duplaChance != null) {
                DuplaChanceView duplaChance = new DuplaChanceView(context, partida.duplaChance).create().build();
                contentOdds.addView(duplaChance.getRootView());
            }

            if (partida.ambasMarcam != null) {
                AmbasMarcamView ambasMarcam = new AmbasMarcamView(context, partida.ambasMarcam).create().build();
                contentOdds.addView(ambasMarcam.getRootView());
            }

            if (partida.vencedorAmbasMarcam != null) {
                VencedorAmbasMarcamView vencedorAmbasMarcam = new VencedorAmbasMarcamView(context, partida.vencedorAmbasMarcam).create().build();
                contentOdds.addView(vencedorAmbasMarcam.getRootView());
            }

            if (partida.intervaloFinal != null) {
                IntervaloFinalView intervaloFinal = new IntervaloFinalView(context, partida.intervaloFinal).create().build();
                contentOdds.addView(intervaloFinal.getRootView());
            }

            if (partida.golsMaisMenos != null) {
                GolsMaisMenosView golsMaisMenos = new GolsMaisMenosView(context, partida.golsMaisMenos).create().build();
                contentOdds.addView(golsMaisMenos.getRootView());
            }

            if (partida.placaresCasa != null && partida.placaresEmpate != null && partida.placaresFora != null) {
                PlacarView placar = new PlacarView(context, partida.placaresCasa, partida.placaresEmpate, partida.placaresFora).create();
                contentOdds.addView(placar.getRootView());
            }

            if (partida.parOuImpar != null) {
                ParOuImparView parOuImpar = new ParOuImparView(context, partida.parOuImpar).create().build();
                contentOdds.addView(parOuImpar.getRootView());
            }

            if (partida.empateAnulaAposta != null) {
                EmpateAnulaApostaView empateAnulaAposta = new EmpateAnulaApostaView(context, partida.empateAnulaAposta).create().build();
                contentOdds.addView(empateAnulaAposta.getRootView());
            }
        }
    }



    private void mostrarPrimeiroSegundoTempo() {

        Context context = getContext();

        if (context != null) {

            contentOdds.removeAllViews();
            partida.display(getView());


            if (partida.resultadoP != null) {
                ResultadoPView resultadoFinal = new ResultadoPView(context, partida.resultadoP).create().build();
                contentOdds.addView(resultadoFinal.getRootView());
            }
            if (partida.resultadoS != null) {
                ResultadoSView resultadoFinal = new ResultadoSView(context, partida.resultadoS).create().build();
                contentOdds.addView(resultadoFinal.getRootView());
            }


            if (partida.duplaChanceP != null) {
                DuplaChancePView duplaChance = new DuplaChancePView(context, partida.duplaChanceP).create().build();
                contentOdds.addView(duplaChance.getRootView());
            }
            if (partida.duplaChanceS != null) {
                DuplaChanceSView duplaChance = new DuplaChanceSView(context, partida.duplaChanceS).create().build();
                contentOdds.addView(duplaChance.getRootView());
            }


            if (partida.ambasMarcamP != null) {
                AmbasMarcamPView ambasMarcam = new AmbasMarcamPView(context, partida.ambasMarcamP).create().build();
                contentOdds.addView(ambasMarcam.getRootView());
            }
            if (partida.ambasMarcamS != null) {
                AmbasMarcamSView ambasMarcam = new AmbasMarcamSView(context, partida.ambasMarcamS).create().build();
                contentOdds.addView(ambasMarcam.getRootView());
            }


            if (partida.golsMaisMenosP != null) {
                GolsMaisMenosPView golsMaisMenos = new GolsMaisMenosPView(context, partida.golsMaisMenosP).create().build();
                contentOdds.addView(golsMaisMenos.getRootView());
            }
            if (partida.golsMaisMenosS != null) {
                GolsMaisMenosSView golsMaisMenos = new GolsMaisMenosSView(context, partida.golsMaisMenosS).create().build();
                contentOdds.addView(golsMaisMenos.getRootView());
            }


            if (partida.parOuImparP != null) {
                ParOuImparPView parOuImpar = new ParOuImparPView(context, partida.parOuImparP).create().build();
                contentOdds.addView(parOuImpar.getRootView());
            }
            if (partida.parOuImparS != null) {
                ParOuImparSView parOuImpar = new ParOuImparSView(context, partida.parOuImparS).create().build();
                contentOdds.addView(parOuImpar.getRootView());
            }
        }
    }
}

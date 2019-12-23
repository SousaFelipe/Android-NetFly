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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.futebol.Partida;
import net.kingbets.cambista.http.responses.OddsResponse;
import net.kingbets.cambista.utils.Config;
import net.kingbets.cambista.utils.Connection;
import net.kingbets.cambista.utils.URL;
import net.kingbets.cambista.view.fragments.PartidasFragment;
import net.kingbets.cambista.view.odds.AmbasMarcamView;
import net.kingbets.cambista.view.odds.DuplaChanceView;
import net.kingbets.cambista.view.odds.EmpateAnulaApostaView;
import net.kingbets.cambista.view.odds.FaixaDeGolsView;
import net.kingbets.cambista.view.odds.GanhaSemLevarGolsCasaView;
import net.kingbets.cambista.view.odds.GanhaSemLevarGolsForaView;
import net.kingbets.cambista.view.odds.GolsMaisMenosView;
import net.kingbets.cambista.view.odds.IntervaloFinalView;
import net.kingbets.cambista.view.odds.ParOuImparView;
import net.kingbets.cambista.view.odds.PlacarView;
import net.kingbets.cambista.view.odds.ResultadoView;
import net.kingbets.cambista.view.odds.TempoComMaisGolsView;
import net.kingbets.cambista.view.odds.VencedorAmbasMarcamView;
import net.kingbets.cambista.view.odds.pt.AmbasMarcamPView;
import net.kingbets.cambista.view.odds.pt.DuplaChancePView;
import net.kingbets.cambista.view.odds.pt.GolsMaisMenosPView;
import net.kingbets.cambista.view.odds.pt.ParOuImparPView;
import net.kingbets.cambista.view.odds.pt.ResultadoPView;
import net.kingbets.cambista.view.odds.st.AmbasMarcamSView;
import net.kingbets.cambista.view.odds.st.DuplaChanceSView;
import net.kingbets.cambista.view.odds.st.GolsMaisMenosSView;
import net.kingbets.cambista.view.odds.st.ParOuImparSView;
import net.kingbets.cambista.view.odds.st.ResultadoSView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class DialogMaisOdds extends DialogFragment {

    public static final String TAG = "DIALOG_MAIS_ODDS";
    public static final String ODDS_PRINCIPAIS = "principais";
    public static final String ODDS_DOIS_TEMPOS = "dois_tempos";



    public String ODDS = ODDS_PRINCIPAIS;



    private Toolbar toolbar;
    private Partida partida;
    private LinearLayout contentOdds;
    private FrameLayout contentLoader;
    private TextView txvNomeTimeCasa;
    private TextView txvNomeTimeFora;

    private PartidasFragment parent;

    private CardView principais;
    private CardView primeiroSegundotempo;
    private CardView lastWidget;



    private Callback callback = new Callback() {
        @Override public void onFailure(@NonNull Call call, @NonNull IOException e) {
            Log.e(getClass().getSimpleName(), "onFailure: ", e);
        }

        @Override public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            proccessResponse(response);
        }
    };



    public static void display(PartidasFragment parent, Partida partida) {

        DialogMaisOdds dialog = new DialogMaisOdds();
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
        contentLoader = view.findViewById(R.id.content_loader);
        txvNomeTimeCasa = view.findViewById(R.id.txv_nome_time_casa);
        txvNomeTimeFora = view.findViewById(R.id.txv_nome_time_fora);

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

        txvNomeTimeCasa.setText(partida.casa);
        txvNomeTimeFora.setText(partida.fora);

        inflateWidgets();
        selecionarWidget(principais);
        request();
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
        parent.request();
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
                    ODDS = ODDS_PRINCIPAIS;
                    request();
                }
            });

            primeiroSegundotempo = view.findViewById(R.id.widget_primeiro_segundo_tempo);
            primeiroSegundotempo.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    selecionarWidget(primeiroSegundotempo);
                    ODDS = ODDS_DOIS_TEMPOS;
                    request();
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



    private void request() {
        contentLoader.setVisibility(View.VISIBLE);

        OkHttpClient client = Connection.getClientWithAuthHeader(getContext());

        Request request = new Request.Builder()
                .url(URL.PARTIDAS + "buscar/" + Config.getDeviceInfo(getContext()) + "/" + partida.id + "/" + ODDS)
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
                            if (ODDS.equals(ODDS_DOIS_TEMPOS)) {
                                mostrarOddsDoisTempos(OddsResponse.DoisTempos.receive(partida, fnBodyString));
                            }
                            else {
                                mostrarOddsPrincipais(OddsResponse.Principais.receive(partida, fnBodyString));
                            }
                        }
                    });
                }
            }
        }
        else {
            Log.e(TAG, "proccessResponse: " + response.message());
        }
    }



    private void mostrarOddsPrincipais(OddsResponse.Principais principais) {
        contentLoader.setVisibility(View.GONE);
        Context context = getContext();

        if (context != null) {

            contentOdds.removeAllViews();
            //partida.display(getView());

            if (principais.resultado != null) {
                ResultadoView resultadoFinal = new ResultadoView(context, principais.resultado).create(parent).build();
                contentOdds.addView(resultadoFinal.getRootView());
            }

            if (principais.duplaChance != null) {
                DuplaChanceView duplaChance = new DuplaChanceView(context, principais.duplaChance).create(parent).build();
                contentOdds.addView(duplaChance.getRootView());
            }

            if (principais.ambasMarcam != null) {
                AmbasMarcamView ambasMarcam = new AmbasMarcamView(context, principais.ambasMarcam).create(parent).build();
                contentOdds.addView(ambasMarcam.getRootView());
            }

            if (principais.vencedorAmbasMarcam != null) {
                VencedorAmbasMarcamView vencedorAmbasMarcam = new VencedorAmbasMarcamView(context, principais.vencedorAmbasMarcam).create(parent).build();
                contentOdds.addView(vencedorAmbasMarcam.getRootView());
            }

            if (principais.intervaloFinal != null) {
                IntervaloFinalView intervaloFinal = new IntervaloFinalView(context, principais.intervaloFinal).create(parent).build();
                contentOdds.addView(intervaloFinal.getRootView());
            }

            if (principais.placaresCasa != null && principais.placaresEmpate != null && principais.placaresFora != null) {
                PlacarView placar = new PlacarView(context, principais.placaresCasa, principais.placaresEmpate, principais.placaresFora).create(parent).build();
                contentOdds.addView(placar.getRootView());
            }

            if (principais.golsMaisMenos != null) {
                GolsMaisMenosView golsMaisMenos = new GolsMaisMenosView(context, principais.golsMaisMenos).create(parent).build();
                contentOdds.addView(golsMaisMenos.getRootView());
            }

            if (principais.tempoComMaisGols != null) {
                TempoComMaisGolsView tempoComMaisGols = new TempoComMaisGolsView(context, principais.tempoComMaisGols).create(parent).build();
                contentOdds.addView(tempoComMaisGols.getRootView());
            }

            if (principais.faixaDeGols != null) {
                FaixaDeGolsView faixaDeGols = new FaixaDeGolsView(context, principais.faixaDeGols).create(parent).build();
                contentOdds.addView(faixaDeGols.getRootView());
            }

            if (principais.casaVenceSemLevarGols != null) {
                GanhaSemLevarGolsCasaView ganhaSemLevarGols = new GanhaSemLevarGolsCasaView(context, principais.casaVenceSemLevarGols).create(parent).build();
                contentOdds.addView(ganhaSemLevarGols.getRootView());
            }

            if (principais.foraVenceSemLevarGols != null) {
                GanhaSemLevarGolsForaView ganhaSemLevarGols = new GanhaSemLevarGolsForaView(context, principais.foraVenceSemLevarGols).create(parent).build();
                contentOdds.addView(ganhaSemLevarGols.getRootView());
            }

            if (principais.parOuImpar != null) {
                ParOuImparView parOuImpar = new ParOuImparView(context, principais.parOuImpar).create(parent).build();
                contentOdds.addView(parOuImpar.getRootView());
            }

            if (principais.empateAnulaAposta != null) {
                EmpateAnulaApostaView empateAnulaAposta = new EmpateAnulaApostaView(context, principais.empateAnulaAposta).create(parent).build();
                contentOdds.addView(empateAnulaAposta.getRootView());
            }
        }
    }

    private void mostrarOddsDoisTempos(OddsResponse.DoisTempos doisTempos) {
        contentLoader.setVisibility(View.GONE);
        Context context = getContext();

        if (context != null) {

            contentOdds.removeAllViews();
            //partida.display(getView());


            if (doisTempos.resultadoP != null) {
                ResultadoPView resultadoFinal = new ResultadoPView(context, doisTempos.resultadoP).create(parent).build();
                contentOdds.addView(resultadoFinal.getRootView());
            }
            if (doisTempos.resultadoS != null) {
                ResultadoSView resultadoFinal = new ResultadoSView(context, doisTempos.resultadoS).create(parent).build();
                contentOdds.addView(resultadoFinal.getRootView());
            }


            if (doisTempos.duplaChanceP != null) {
                DuplaChancePView duplaChance = new DuplaChancePView(context, doisTempos.duplaChanceP).create(parent).build();
                contentOdds.addView(duplaChance.getRootView());
            }
            if (doisTempos.duplaChanceS != null) {
                DuplaChanceSView duplaChance = new DuplaChanceSView(context, doisTempos.duplaChanceS).create(parent).build();
                contentOdds.addView(duplaChance.getRootView());
            }


            if (doisTempos.ambasMarcamP != null) {
                AmbasMarcamPView ambasMarcam = new AmbasMarcamPView(context, doisTempos.ambasMarcamP).create(parent).build();
                contentOdds.addView(ambasMarcam.getRootView());
            }
            if (doisTempos.ambasMarcamS != null) {
                AmbasMarcamSView ambasMarcam = new AmbasMarcamSView(context, doisTempos.ambasMarcamS).create(parent).build();
                contentOdds.addView(ambasMarcam.getRootView());
            }


            if (doisTempos.golsMaisMenosP != null) {
                GolsMaisMenosPView golsMaisMenos = new GolsMaisMenosPView(context, doisTempos.golsMaisMenosP).create(parent).build();
                contentOdds.addView(golsMaisMenos.getRootView());
            }
            if (doisTempos.golsMaisMenosS != null) {
                GolsMaisMenosSView golsMaisMenos = new GolsMaisMenosSView(context, doisTempos.golsMaisMenosS).create(parent).build();
                contentOdds.addView(golsMaisMenos.getRootView());
            }


            if (doisTempos.parOuImparP != null) {
                ParOuImparPView parOuImpar = new ParOuImparPView(context, doisTempos.parOuImparP).create(parent).build();
                contentOdds.addView(parOuImpar.getRootView());
            }
            if (doisTempos.parOuImparS != null) {
                ParOuImparSView parOuImpar = new ParOuImparSView(context, doisTempos.parOuImparS).create(parent).build();
                contentOdds.addView(parOuImpar.getRootView());
            }
        }
    }
}

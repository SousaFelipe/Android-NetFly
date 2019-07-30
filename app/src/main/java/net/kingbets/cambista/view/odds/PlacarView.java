package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.local.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.principais.Placar;
import net.kingbets.cambista.view.widgets.WidgetPlacar;

import java.util.ArrayList;
import java.util.List;


public class PlacarView extends BaseOddsView {


    private List<Placar>        placaresCasa;
    private List<WidgetPlacar>  wgtPlacaresCasa;
    private List<View>          viewWgtCasa;

    private List<Placar>        placaresEmpate;
    private List<WidgetPlacar>  wgtPlacaresEmpate;
    private List<View>          viewWgtEmpate;

    private List<Placar>        placaresFora;
    private List<WidgetPlacar>  wgtPlacaresFora;
    private List<View>          viewWgtFora;



    public PlacarView(Context context, List<Placar> placaresCasa, List<Placar> placaresEmpate, List<Placar> placaresFora) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_placar_exatato, null, false));

        setContext(context);
        setAposta(new Aposta(Placar.TIPO).partida(placaresCasa.get(0).partida));

        this.placaresCasa = placaresCasa;
        this.placaresEmpate = placaresEmpate;
        this.placaresFora = placaresFora;
    }


    @Override
    public PlacarView create() {

        createViewsCasa();
        wgtPlacaresCasa = new ArrayList<>();
        int maxCasa = (placaresCasa.size() < 10) ? placaresCasa.size() : 10;

        for (int i = 0; i < maxCasa; i++) {
            WidgetPlacar widget = new WidgetPlacar( viewWgtCasa.get( i ) );
            wgtPlacaresCasa.add(widget);
        }

        createViewsEmpate();
        wgtPlacaresEmpate = new ArrayList<>();
        int maxEmpate = (placaresEmpate.size() < 6) ? placaresEmpate.size() : 6;

        for (int i = 0; i < maxEmpate; i++) {
            WidgetPlacar widget = new WidgetPlacar( viewWgtEmpate.get( i ) );
            wgtPlacaresEmpate.add(widget);
        }

        createViewsFora();
        wgtPlacaresFora = new ArrayList<>();
        int maxFora = (placaresFora.size() < 10) ? placaresFora.size() : 10;

        for (int i = 0; i < maxFora; i++) {
            WidgetPlacar widget = new WidgetPlacar( viewWgtFora.get( i ) );
            wgtPlacaresFora.add(widget);
        }

        return this;
    }



    private void createViewsCasa() {
        viewWgtCasa = new ArrayList<>();
        viewWgtCasa.add(getRootView().findViewById(R.id.layout_casa_ganha_row_0));
        viewWgtCasa.add(getRootView().findViewById(R.id.layout_casa_ganha_row_1));
        viewWgtCasa.add(getRootView().findViewById(R.id.layout_casa_ganha_row_2));
        viewWgtCasa.add(getRootView().findViewById(R.id.layout_casa_ganha_row_3));
        viewWgtCasa.add(getRootView().findViewById(R.id.layout_casa_ganha_row_4));
        viewWgtCasa.add(getRootView().findViewById(R.id.layout_casa_ganha_row_5));
        viewWgtCasa.add(getRootView().findViewById(R.id.layout_casa_ganha_row_6));
        viewWgtCasa.add(getRootView().findViewById(R.id.layout_casa_ganha_row_7));
        viewWgtCasa.add(getRootView().findViewById(R.id.layout_casa_ganha_row_8));
        viewWgtCasa.add(getRootView().findViewById(R.id.layout_casa_ganha_row_9));
    }

    private void createViewsEmpate() {
        viewWgtEmpate = new ArrayList<>();
        viewWgtEmpate.add(getRootView().findViewById(R.id.layout_empate_row_0));
        viewWgtEmpate.add(getRootView().findViewById(R.id.layout_empate_row_1));
        viewWgtEmpate.add(getRootView().findViewById(R.id.layout_empate_row_2));
        viewWgtEmpate.add(getRootView().findViewById(R.id.layout_empate_row_3));
        viewWgtEmpate.add(getRootView().findViewById(R.id.layout_empate_row_4));
        viewWgtEmpate.add(getRootView().findViewById(R.id.layout_empate_row_5));
    }

    private void createViewsFora() {
        viewWgtFora = new ArrayList<>();
        viewWgtFora.add(getRootView().findViewById(R.id.layout_fora_ganha_row_0));
        viewWgtFora.add(getRootView().findViewById(R.id.layout_fora_ganha_row_1));
        viewWgtFora.add(getRootView().findViewById(R.id.layout_fora_ganha_row_2));
        viewWgtFora.add(getRootView().findViewById(R.id.layout_fora_ganha_row_3));
        viewWgtFora.add(getRootView().findViewById(R.id.layout_fora_ganha_row_4));
        viewWgtFora.add(getRootView().findViewById(R.id.layout_fora_ganha_row_5));
        viewWgtFora.add(getRootView().findViewById(R.id.layout_fora_ganha_row_6));
        viewWgtFora.add(getRootView().findViewById(R.id.layout_fora_ganha_row_7));
        viewWgtFora.add(getRootView().findViewById(R.id.layout_fora_ganha_row_8));
        viewWgtFora.add(getRootView().findViewById(R.id.layout_fora_ganha_row_9));
    }


    @Override
    public PlacarView build() {
        buildCasaWidgets();
        buildEmpateWidgets();
        buildForaWidgets();
        return this;
    }



    private void buildCasaWidgets() {

        int max = (placaresCasa.size() < 10) ? placaresCasa.size() : 10;

        for (int i = 0; i < max; i++) {

            Placar placar   = placaresCasa.get(i);
            Aposta aposta   = new Aposta(Placar.TIPO).partida( placar.partida );
            aposta.setTitulo(placar);
            aposta.cotacao  = placar.odds;

            WidgetPlacar widget = wgtPlacaresCasa.get( i );
            widget.setAposta(aposta);
            widget.setTitulo(placar);
        }
    }

    private void buildEmpateWidgets() {

        int max = (placaresEmpate.size() < 6) ? placaresEmpate.size() : 6;

        for (int i = 0; i < max; i++) {

            Placar placar = placaresEmpate.get(i);
            Aposta aposta   = new Aposta(Placar.TIPO).partida( placar.partida );
            aposta.setTitulo(placar);
            aposta.cotacao  = placar.odds;

            WidgetPlacar widget = wgtPlacaresEmpate.get( i );
            widget.setAposta(aposta);
            widget.setTitulo(placar);
        }
    }

    private void buildForaWidgets() {

        int max = (placaresFora.size() < 10) ? placaresFora.size() : 10;

        for (int i = 0; i < max; i++) {

            Placar placar = placaresFora.get(i);
            Aposta aposta = new Aposta(Placar.TIPO).partida( placar.partida );
            aposta.setTitulo(placar);
            aposta.cotacao  = placar.odds;

            WidgetPlacar widget = wgtPlacaresFora.get( i );
            widget.setAposta(aposta);
            widget.setTitulo(placar);
        }
    }
}

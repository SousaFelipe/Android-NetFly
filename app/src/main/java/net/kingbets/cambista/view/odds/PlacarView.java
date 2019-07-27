package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.LayoutInflater;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.local.apostas.Aposta;
import net.kingbets.cambista.model.remote.odds.principais.Placar;
import net.kingbets.cambista.view.widgets.WidgetPlacar;

import java.util.List;


public class PlacarView extends BaseOddsView {



    private List<Placar> placaresCasa;
    private List<Placar> placaresEmpate;
    private List<Placar> placaresFora;

    private WidgetPlacar wgtCasaGanha_0;
    private WidgetPlacar wgtCasaGanha_1;
    private WidgetPlacar wgtCasaGanha_2;
    private WidgetPlacar wgtCasaGanha_3;
    private WidgetPlacar wgtCasaGanha_4;
    private WidgetPlacar wgtCasaGanha_5;
    private WidgetPlacar wgtCasaGanha_6;
    private WidgetPlacar wgtCasaGanha_7;
    private WidgetPlacar wgtCasaGanha_8;
    private WidgetPlacar wgtCasaGanha_9;

    private WidgetPlacar wgtEmpate_0;
    private WidgetPlacar wgtEmpate_1;
    private WidgetPlacar wgtEmpate_2;
    private WidgetPlacar wgtEmpate_3;
    private WidgetPlacar wgtEmpate_4;
    private WidgetPlacar wgtEmpate_5;

    private WidgetPlacar wgtForaGanha_0;
    private WidgetPlacar wgtForaGanha_1;
    private WidgetPlacar wgtForaGanha_2;
    private WidgetPlacar wgtForaGanha_3;
    private WidgetPlacar wgtForaGanha_4;
    private WidgetPlacar wgtForaGanha_5;
    private WidgetPlacar wgtForaGanha_6;
    private WidgetPlacar wgtForaGanha_7;
    private WidgetPlacar wgtForaGanha_8;
    private WidgetPlacar wgtForaGanha_9;



    public PlacarView(Context context, List<Placar> placaresCasa, List<Placar> placaresEmpate, List<Placar> placaresFora) {
        super(LayoutInflater.from(context).inflate(R.layout.odds_placar_exatato, null, false));

        setContext(context);
        setAposta(new Aposta(Placar.TIPO).partida( placaresCasa.get(0).partida ));

        this.placaresCasa = placaresCasa;
        this.placaresEmpate = placaresEmpate;
        this.placaresFora = placaresFora;
    }



    @Override
    public PlacarView create() {

        createPlacaresCasa();
        createPlacaresEmpate();
        createPlacaresFora();

        return this;
    }


    @Override
    public PlacarView build() {
        return this;
    }



    private void createPlacaresCasa() {

        if (placaresCasa != null) {

            if (placaresCasa.size() >= 1) {
                Placar placar = placaresCasa.get(0);
                String sentenca = ("C:" + placar.casa + ";" + placar.fora);
                wgtCasaGanha_0 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_casa_ganha_row_0), placar.odds);
                wgtCasaGanha_0.setTitulo(placar);
            }

            if (placaresCasa.size() >= 2) {
                Placar placar = placaresCasa.get(1);
                String sentenca = ("C:" + placar.casa + ";" + placar.fora);
                wgtCasaGanha_1 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_casa_ganha_row_1), placar.odds);
                wgtCasaGanha_1.setTitulo(placar);
            }

            if (placaresCasa.size() >= 3) {
                Placar placar = placaresCasa.get(2);
                String sentenca = ("C:" + placar.casa + ";" + placar.fora);
                wgtCasaGanha_2 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_casa_ganha_row_2), placar.odds);
                wgtCasaGanha_2.setTitulo(placar);
            }

            if (placaresCasa.size() >= 4) {
                Placar placar = placaresCasa.get(3);
                String sentenca = ("C:" + placar.casa + ";" + placar.fora);
                wgtCasaGanha_3 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_casa_ganha_row_3), placar.odds);
                wgtCasaGanha_3.setTitulo(placar);
            }

            if (placaresCasa.size() >= 5) {
                Placar placar = placaresCasa.get(4);
                String sentenca = ("C:" + placar.casa + ";" + placar.fora);
                wgtCasaGanha_4 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_casa_ganha_row_4), placar.odds);
                wgtCasaGanha_4.setTitulo(placar);
            }

            if (placaresCasa.size() >= 6) {
                Placar placar = placaresCasa.get(5);
                String sentenca = ("C:" + placar.casa + ";" + placar.fora);
                wgtCasaGanha_5 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_casa_ganha_row_5), placar.odds);
                wgtCasaGanha_5.setTitulo(placar);
            }

            if (placaresCasa.size() >= 7) {
                Placar placar = placaresCasa.get(6);
                String sentenca = ("C:" + placar.casa + ";" + placar.fora);
                wgtCasaGanha_6 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_casa_ganha_row_6), placar.odds);
                wgtCasaGanha_6.setTitulo(placar);
            }

            if (placaresCasa.size() >= 8) {
                Placar placar = placaresCasa.get(7);
                String sentenca = ("C:" + placar.casa + ";" + placar.fora);
                wgtCasaGanha_7 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_casa_ganha_row_7), placar.odds);
                wgtCasaGanha_7.setTitulo(placar);
            }

            if (placaresCasa.size() >= 9) {
                Placar placar = placaresCasa.get(8);
                String sentenca = ("C:" + placar.casa + ";" + placar.fora);
                wgtCasaGanha_8 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_casa_ganha_row_8), placar.odds);
                wgtCasaGanha_8.setTitulo(placar);
            }

            if (placaresCasa.size() >= 10) {
                Placar placar = placaresCasa.get(9);
                String sentenca = ("C:" + placar.casa + ";" + placar.fora);
                wgtCasaGanha_9 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_casa_ganha_row_9), placar.odds);
                wgtCasaGanha_9.setTitulo(placar);
            }
        }
    }


    private void createPlacaresEmpate() {

        if (placaresEmpate != null) {

            if (placaresEmpate.size() >= 1) {
                Placar placar = placaresEmpate.get(0);
                String sentenca = ("E:" + placar.casa + ";" + placar.fora);
                wgtEmpate_0 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_empate_row_0), placar.odds);
                wgtEmpate_0.setTitulo(placar);
            }

            if (placaresEmpate.size() >= 2) {
                Placar placar = placaresEmpate.get(1);
                String sentenca = ("E:" + placar.casa + ";" + placar.fora);
                wgtEmpate_1 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_empate_row_1), placar.odds);
                wgtEmpate_1.setTitulo(placar);
            }

            if (placaresEmpate.size() >= 3) {
                Placar placar = placaresEmpate.get(2);
                String sentenca = ("E:" + placar.casa + ";" + placar.fora);
                wgtEmpate_2 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_empate_row_2), placar.odds);
                wgtEmpate_2.setTitulo(placar);
            }

            if (placaresEmpate.size() >= 4) {
                Placar placar = placaresEmpate.get(3);
                String sentenca = ("E:" + placar.casa + ";" + placar.fora);
                wgtEmpate_3 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_empate_row_3), placar.odds);
                wgtEmpate_3.setTitulo(placar);
            }

            if (placaresEmpate.size() >= 5) {
                Placar placar = placaresEmpate.get(4);
                String sentenca = ("E:" + placar.casa + ";" + placar.fora);
                wgtEmpate_4 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_empate_row_4), placar.odds);
                wgtEmpate_4.setTitulo(placar);
            }

            if (placaresEmpate.size() >= 6) {
                Placar placar = placaresEmpate.get(5);
                String sentenca = ("E:" + placar.casa + ";" + placar.fora);
                wgtEmpate_5 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_empate_row_5), placar.odds);
                wgtEmpate_5.setTitulo(placar);
            }
        }
    }


    private void createPlacaresFora() {

        if (placaresFora.size() >= 1) {
            Placar placar = placaresFora.get(0);
            String sentenca = ("F:" + placar.casa + ";" + placar.fora);
            wgtForaGanha_0 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_fora_ganha_row_0), placar.odds);
            wgtForaGanha_0.setTitulo(placar);
        }

        if (placaresFora.size() >= 2) {
            Placar placar = placaresFora.get(1);
            String sentenca = ("F:" + placar.casa + ";" + placar.fora);
            wgtForaGanha_1 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_fora_ganha_row_1), placar.odds);
            wgtForaGanha_1.setTitulo(placar);
        }

        if (placaresFora.size() >= 3) {
            Placar placar = placaresFora.get(2);
            String sentenca = ("F:" + placar.casa + ";" + placar.fora);
            wgtForaGanha_2 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_fora_ganha_row_2), placar.odds);
            wgtForaGanha_2.setTitulo(placar);
        }

        if (placaresFora.size() >= 4) {
            Placar placar = placaresFora.get(3);
            String sentenca = ("F:" + placar.casa + ";" + placar.fora);
            wgtForaGanha_3 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_fora_ganha_row_3), placar.odds);
            wgtForaGanha_3.setTitulo(placar);
        }

        if (placaresFora.size() >= 5) {
            Placar placar = placaresFora.get(4);
            String sentenca = ("F:" + placar.casa + ";" + placar.fora);
            wgtForaGanha_4 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_fora_ganha_row_4), placar.odds);
            wgtForaGanha_4.setTitulo(placar);
        }

        if (placaresFora.size() >= 6) {
            Placar placar = placaresFora.get(5);
            String sentenca = ("F:" + placar.casa + ";" + placar.fora);
            wgtForaGanha_5 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_fora_ganha_row_5), placar.odds);
            wgtForaGanha_5.setTitulo(placar);
        }

        if (placaresFora.size() >= 7) {
            Placar placar = placaresFora.get(6);
            String sentenca = ("F:" + placar.casa + ";" + placar.fora);
            wgtForaGanha_6 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_fora_ganha_row_6), placar.odds);
            wgtForaGanha_6.setTitulo(placar);
        }

        if (placaresFora.size() >= 8) {
            Placar placar = placaresFora.get(7);
            String sentenca = ("F:" + placar.casa + ";" + placar.fora);
            wgtForaGanha_7 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_fora_ganha_row_7), placar.odds);
            wgtForaGanha_7.setTitulo(placar);
        }

        if (placaresFora.size() >= 9) {
            Placar placar = placaresFora.get(8);
            String sentenca = ("F:" + placar.casa + ";" + placar.fora);
            wgtForaGanha_8 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_fora_ganha_row_8), placar.odds);
            wgtForaGanha_8.setTitulo(placar);
        }

        if (placaresFora.size() >= 10) {
            Placar placar = placaresFora.get(9);
            String sentenca = ("F:" + placar.casa + ";" + placar.fora);
            wgtForaGanha_9 = new WidgetPlacar(getAposta(sentenca), getRootView().findViewById(R.id.layout_fora_ganha_row_9), placar.odds);
            wgtForaGanha_9.setTitulo(placar);
        }
    }
}

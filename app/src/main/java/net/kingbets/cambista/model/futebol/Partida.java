package net.kingbets.cambista.model.futebol;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.odds.primeiras.AmbasMarcamP;
import net.kingbets.cambista.http.models.odds.primeiras.DuplaChanceP;
import net.kingbets.cambista.http.models.odds.primeiras.GolsMaisMenosP;
import net.kingbets.cambista.http.models.odds.primeiras.ParImparP;
import net.kingbets.cambista.http.models.odds.primeiras.ResultadoP;
import net.kingbets.cambista.http.models.odds.principais.AmbasMarcam;
import net.kingbets.cambista.http.models.odds.principais.DuplaChance;
import net.kingbets.cambista.http.models.odds.principais.EmpateAnulaAposta;
import net.kingbets.cambista.http.models.odds.principais.FaixaDeGols;
import net.kingbets.cambista.http.models.odds.principais.GolsMaisMenos;
import net.kingbets.cambista.http.models.odds.principais.IntervaloFinal;
import net.kingbets.cambista.http.models.odds.principais.ParImpar;
import net.kingbets.cambista.http.models.odds.principais.Placar;
import net.kingbets.cambista.http.models.odds.principais.Resultado;
import net.kingbets.cambista.http.models.odds.principais.TempoComMaisGols;
import net.kingbets.cambista.http.models.odds.principais.VenceSemLevarGols;
import net.kingbets.cambista.http.models.odds.principais.VencedorAmbasMarcam;
import net.kingbets.cambista.http.models.odds.segundas.AmbasMarcamS;
import net.kingbets.cambista.http.models.odds.segundas.DuplaChanceS;
import net.kingbets.cambista.http.models.odds.segundas.GolsMaisMenosS;
import net.kingbets.cambista.http.models.odds.segundas.ParImparS;
import net.kingbets.cambista.http.models.odds.segundas.ResultadoS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Partida extends net.kingbets.cambista.http.models.futebol.Partida {



    public Resultado resultado;
    public DuplaChance duplaChance;
    public AmbasMarcam ambasMarcam;
    public VencedorAmbasMarcam vencedorAmbasMarcam;
    public IntervaloFinal intervaloFinal;
    public ParImpar parOuImpar;
    public EmpateAnulaAposta empateAnulaAposta;
    public GolsMaisMenos golsMaisMenos;
    public FaixaDeGols faixaDeGols;
    public TempoComMaisGols tempoComMaisGols;
    public VenceSemLevarGols casaVenceSemLevarGols;
    public VenceSemLevarGols foraVenceSemLevarGols;

    public List<Placar> placaresCasa;
    public List<Placar> placaresEmpate;
    public List<Placar> placaresFora;

    public ResultadoP resultadoP;
    public DuplaChanceP duplaChanceP;
    public AmbasMarcamP ambasMarcamP;
    public GolsMaisMenosP golsMaisMenosP;
    public ParImparP parOuImparP;

    public ResultadoS resultadoS;
    public DuplaChanceS duplaChanceS;
    public AmbasMarcamS ambasMarcamS;
    public GolsMaisMenosS golsMaisMenosS;
    public ParImparS parOuImparS;



    public void setODDS(JSONObject odds) throws JSONException {

        JSONObject principais = odds.getJSONObject("principais");
        JSONObject primeiroTempo = odds.getJSONObject("primeiro_tempo");
        JSONObject segundoTempo = odds.getJSONObject("segundo_tempo");

        setPrincipais(principais);
        setPrimeiroTempo(primeiroTempo);
        setSegundoTempo(segundoTempo);
    }



    public void display(View view) {

        TextView txvNomeTimeCasa = view.findViewById(R.id.txv_nome_time_casa);
        TextView txvNomeTimeFora = view.findViewById(R.id.txv_nome_time_fora);

        txvNomeTimeCasa.setText( casa );
        txvNomeTimeFora.setText( fora );
    }



    private void setPrincipais(@NonNull JSONObject principais) throws JSONException {

        if ( ! principais.isNull("principal")) {
            resultado = new Resultado(principais.getJSONObject("principal"));
            resultado.partida = this;
        }

        if ( ! principais.isNull("dupla_chance")) {
            duplaChance = new DuplaChance(principais.getJSONObject("dupla_chance"));
            duplaChance.partida = this;
        }

        if ( ! principais.isNull("ambas_marcam")) {
            ambasMarcam = new AmbasMarcam(principais.getJSONObject("ambas_marcam"));
            ambasMarcam.partida = this;
        }

        if ( ! principais.isNull("vencedor_ambas_marcam")) {
            vencedorAmbasMarcam = new VencedorAmbasMarcam(principais.getJSONObject("vencedor_ambas_marcam"));
            vencedorAmbasMarcam.partida = this;
        }

        if ( ! principais.isNull("intervalo_final")) {
            intervaloFinal = new IntervaloFinal(principais.getJSONObject("intervalo_final"));
            intervaloFinal.partida = this;
        }

        if ( ! principais.isNull("gols_par_impar")) {
            parOuImpar = new ParImpar(principais.getJSONObject("gols_par_impar"));
            parOuImpar.partida = this;
        }

        if ( ! principais.isNull("empate_anula_aposta")) {
            empateAnulaAposta = new EmpateAnulaAposta(principais.getJSONObject("empate_anula_aposta"));
            empateAnulaAposta.partida = this;
        }

        if ( ! principais.isNull("placares")) {
            placaresCasa   = getPlacars( principais.getJSONObject("placares").getJSONArray("CS") );
            placaresEmpate = getPlacars( principais.getJSONObject("placares").getJSONArray("EP") );
            placaresFora   = getPlacars( principais.getJSONObject("placares").getJSONArray("FR") );
        }

        if ( ! principais.isNull("gols_parcial")) {
            golsMaisMenos = new GolsMaisMenos(principais.getJSONObject("gols_parcial"));
            golsMaisMenos.partida = this;
        }

        if ( ! principais.isNull("tempo_com_mais_gols")) {
            tempoComMaisGols = new TempoComMaisGols(principais.getJSONObject("tempo_com_mais_gols"));
            tempoComMaisGols.partida = this;
        }

        if ( ! principais.isNull("faixa_de_gols")) {
            faixaDeGols = new FaixaDeGols(principais.getJSONObject("faixa_de_gols"));
            faixaDeGols.partida = this;
        }

        if ( ! principais.isNull("vence_sem_levar_gols")) {

            if ( ! principais.getJSONObject("vence_sem_levar_gols").isNull("casa")) {
                casaVenceSemLevarGols = new VenceSemLevarGols( principais.getJSONObject("vence_sem_levar_gols").getJSONObject("casa") );
                casaVenceSemLevarGols.tipo = VenceSemLevarGols.CASA;
                casaVenceSemLevarGols.partida = this;
            }

            if ( ! principais.getJSONObject("vence_sem_levar_gols").isNull("fora")) {
                foraVenceSemLevarGols = new VenceSemLevarGols( principais.getJSONObject("vence_sem_levar_gols").getJSONObject("fora") );
                foraVenceSemLevarGols.tipo = VenceSemLevarGols.FORA;
                foraVenceSemLevarGols.partida = this;
            }
        }
    }



    private void setPrimeiroTempo(@NonNull JSONObject primeiroTempo) throws JSONException {

        if ( ! primeiroTempo.isNull("pt_principal")) {
            resultadoP = new ResultadoP(primeiroTempo.getJSONObject("pt_principal"));
            resultadoP.partida = this;
        }

        if ( ! primeiroTempo.isNull("pt_dupla_chance")) {
            duplaChanceP = new DuplaChanceP(primeiroTempo.getJSONObject("pt_dupla_chance"));
            duplaChanceP.partida = this;
        }

        if ( ! primeiroTempo.isNull("pt_ambas_marcam")) {
            ambasMarcamP = new AmbasMarcamP(primeiroTempo.getJSONObject("pt_ambas_marcam"));
            ambasMarcamP.partida = this;
        }

        if ( ! primeiroTempo.isNull("pt_gols_par_impar")) {
            parOuImparP = new ParImparP(primeiroTempo.getJSONObject("pt_gols_par_impar"));
            parOuImparP.partida = this;
        }

        if ( ! primeiroTempo.isNull("pt_gols_parcial")) {
            golsMaisMenosP = new GolsMaisMenosP(primeiroTempo.getJSONObject("pt_gols_parcial"));
            golsMaisMenosP.partida = this;
        }
    }



    private void setSegundoTempo(@NonNull JSONObject segundoTempo) throws JSONException {

        if ( ! segundoTempo.isNull("st_principal")) {
            resultadoS = new ResultadoS(segundoTempo.getJSONObject("st_principal"));
            resultadoS.partida = this;
        }

        if ( ! segundoTempo.isNull("st_dupla_chance")) {
            duplaChanceS = new DuplaChanceS(segundoTempo.getJSONObject("st_dupla_chance"));
            duplaChanceS.partida = this;
        }

        if ( ! segundoTempo.isNull("st_ambas_marcam")) {
            ambasMarcamS = new AmbasMarcamS(segundoTempo.getJSONObject("st_ambas_marcam"));
            ambasMarcamS.partida = this;
        }

        if ( ! segundoTempo.isNull("st_gols_par_impar")) {
            parOuImparS = new ParImparS(segundoTempo.getJSONObject("st_gols_par_impar"));
            parOuImparS.partida = this;
        }

        if ( ! segundoTempo.isNull("st_gols_parcial")) {
            golsMaisMenosS = new GolsMaisMenosS(segundoTempo.getJSONObject("st_gols_parcial"));
            golsMaisMenosS.partida = this;
        }
    }


    @Nullable
    private List<Placar> getPlacars (@NonNull JSONArray JSONPlacares) throws JSONException {
        List<Placar> placares = new ArrayList<>();

        for (int i = 0; i < JSONPlacares.length(); i++) {

            JSONObject JSONPlacar = JSONPlacares.getJSONObject( i );

            Placar placar = new Placar();
            placar.partida = this;
            placar.tipo = JSONPlacar.getString("tipo");
            placar.casa = JSONPlacar.getInt("casa");
            placar.fora = JSONPlacar.getInt("fora");
            placar.odds = JSONPlacar.getDouble("odds");

            placares.add(placar);
        }

        return (placares.size() > 0) ? placares : null;
    }


    @Override
    public boolean equals(@Nullable Object obj) {

        if (obj != null) {
            Partida out = (Partida) obj;
            return id == out.id && casa.equals(out.casa) && fora.equals(out.fora) && inicio.equals(out.inicio);
        }

        return false;
    }
}

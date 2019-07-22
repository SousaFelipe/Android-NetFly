package net.kingbets.cambista.model.remote.futebol;


import android.view.View;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.BaseModel;
import net.kingbets.cambista.model.remote.odds.primeiras.AmbasMarcamP;
import net.kingbets.cambista.model.remote.odds.primeiras.DuplaChanceP;
import net.kingbets.cambista.model.remote.odds.primeiras.GolsMaisMenosP;
import net.kingbets.cambista.model.remote.odds.primeiras.ParImparP;
import net.kingbets.cambista.model.remote.odds.primeiras.ResultadoP;
import net.kingbets.cambista.model.remote.odds.principais.AmbasMarcam;
import net.kingbets.cambista.model.remote.odds.principais.DuplaChance;
import net.kingbets.cambista.model.remote.odds.principais.EmpateAnulaAposta;
import net.kingbets.cambista.model.remote.odds.principais.GolsMaisMenos;
import net.kingbets.cambista.model.remote.odds.principais.IntervaloFinal;
import net.kingbets.cambista.model.remote.odds.principais.ParImpar;
import net.kingbets.cambista.model.remote.odds.principais.Resultado;
import net.kingbets.cambista.model.remote.odds.principais.VencedorAmbasMarcam;
import net.kingbets.cambista.model.remote.odds.segundas.AmbasMarcamS;
import net.kingbets.cambista.model.remote.odds.segundas.DuplaChanceS;
import net.kingbets.cambista.model.remote.odds.segundas.GolsMaisMenosS;
import net.kingbets.cambista.model.remote.odds.segundas.ParImparS;
import net.kingbets.cambista.model.remote.odds.segundas.ResultadoS;

import org.json.JSONException;
import org.json.JSONObject;


public class Partida extends BaseModel {



    public String casa;
    public String fora;
    public String inicio;



    public Resultado resultado;
    public DuplaChance duplaChance;
    public AmbasMarcam ambasMarcam;
    public VencedorAmbasMarcam vencedorAmbasMarcam;
    public IntervaloFinal intervaloFinal;
    public GolsMaisMenos golsMaisMenos;
    public ParImpar parOuImpar;
    public EmpateAnulaAposta empateAnulaAposta;

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



    private void setPrincipais(JSONObject principais) throws JSONException {

        if ( ! principais.isNull("principal")) {
            resultado = new Resultado(principais.getJSONObject("principal"));
            resultado.partida = this.id;
        }

        if ( ! principais.isNull("dupla_chance")) {
            duplaChance = new DuplaChance(principais.getJSONObject("dupla_chance"));
            duplaChance.partida = this.id;
        }

        if ( ! principais.isNull("ambas_marcam")) {
            ambasMarcam = new AmbasMarcam(principais.getJSONObject("ambas_marcam"));
            ambasMarcam.partida = this.id;
        }

        if ( ! principais.isNull("vencedor_ambas_marcam")) {
            vencedorAmbasMarcam = new VencedorAmbasMarcam(principais.getJSONObject("vencedor_ambas_marcam"));
            vencedorAmbasMarcam.partida = this.id;
        }

        if ( ! principais.isNull("intervalo_final")) {
            intervaloFinal = new IntervaloFinal(principais.getJSONObject("intervalo_final"));
            intervaloFinal.partida = this.id;
        }

        if ( ! principais.isNull("gols_par_impar")) {
            parOuImpar = new ParImpar(principais.getJSONObject("gols_par_impar"));
            parOuImpar.partida = this.id;
        }

        if ( ! principais.isNull("gols_parcial")) {
            golsMaisMenos = new GolsMaisMenos(principais.getJSONObject("gols_parcial"));
            golsMaisMenos.partida = this.id;
        }

        if ( ! principais.isNull("empate_anula_aposta")) {
            empateAnulaAposta = new EmpateAnulaAposta(principais.getJSONObject("empate_anula_aposta"));
            empateAnulaAposta.partida = this.id;
        }
    }



    private void setPrimeiroTempo(JSONObject primeiroTempo) throws JSONException {

        if ( ! primeiroTempo.isNull("pt_principal")) {
            resultadoP = new ResultadoP(primeiroTempo.getJSONObject("pt_principal"));
            resultadoP.partida = this.id;
        }

        if ( ! primeiroTempo.isNull("pt_dupla_chance")) {
            duplaChanceP = new DuplaChanceP(primeiroTempo.getJSONObject("pt_dupla_chance"));
            duplaChanceP.partida = this.id;
        }

        if ( ! primeiroTempo.isNull("pt_ambas_marcam")) {
            ambasMarcamP = new AmbasMarcamP(primeiroTempo.getJSONObject("pt_ambas_marcam"));
            ambasMarcamP.partida = this.id;
        }

        if ( ! primeiroTempo.isNull("pt_gols_par_impar")) {
            parOuImparP = new ParImparP(primeiroTempo.getJSONObject("pt_gols_par_impar"));
            parOuImparP.partida = this.id;
        }

        if ( ! primeiroTempo.isNull("pt_gols_parcial")) {
            golsMaisMenosP = new GolsMaisMenosP(primeiroTempo.getJSONObject("pt_gols_parcial"));
            golsMaisMenosP.partida = this.id;
        }
    }



    private void setSegundoTempo(JSONObject segundoTempo) throws JSONException {

        if ( ! segundoTempo.isNull("st_principal")) {
            resultadoS = new ResultadoS(segundoTempo.getJSONObject("st_principal"));
            resultadoS.partida = this.id;
        }

        if ( ! segundoTempo.isNull("st_dupla_chance")) {
            duplaChanceS = new DuplaChanceS(segundoTempo.getJSONObject("st_dupla_chance"));
            duplaChanceS.partida = this.id;
        }

        if ( ! segundoTempo.isNull("st_ambas_marcam")) {
            ambasMarcamS = new AmbasMarcamS(segundoTempo.getJSONObject("st_ambas_marcam"));
            ambasMarcamS.partida = this.id;
        }

        if ( ! segundoTempo.isNull("st_gols_par_impar")) {
            parOuImparS = new ParImparS(segundoTempo.getJSONObject("st_gols_par_impar"));
            parOuImparS.partida = this.id;
        }

        if ( ! segundoTempo.isNull("st_gols_parcial")) {
            golsMaisMenosS = new GolsMaisMenosS(segundoTempo.getJSONObject("st_gols_parcial"));
            golsMaisMenosS.partida = this.id;
        }
    }
}

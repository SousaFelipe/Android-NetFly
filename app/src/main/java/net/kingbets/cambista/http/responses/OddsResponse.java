package net.kingbets.cambista.http.responses;


import android.support.annotation.NonNull;

import net.kingbets.cambista.http.models.apostas.BetStack;
import net.kingbets.cambista.http.models.futebol.Partida;
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


public class OddsResponse {



    private OddsResponse () { }



    public static class Principais extends BaseResponse {


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


        public static Principais receive(Partida partida, @NonNull String bodyString) {
            Principais response = new OddsResponse.Principais();

            try {

                JSONObject json = new JSONObject(bodyString);
                JSONObject body = json.getJSONObject("body");
                response.code = json.getInt("code");

                if (response.code == 200) {
                    response.load(partida, body.getJSONObject("match_odds"));
                    BetStack.instance().load(body.getJSONArray("currentbets"), body.getInt("minimun"));
                }
            }
            catch (JSONException e) {
                response.code = 512;
                e.printStackTrace();
            }

            return response;
        }


        private void load(Partida partida, JSONObject matchOdds) throws JSONException {

            if ( ! matchOdds.isNull("principal")) {
                resultado = new Resultado(matchOdds.getJSONObject("principal"));
                resultado.partida = partida;
            }

            if ( ! matchOdds.isNull("dupla_chance")) {
                duplaChance = new DuplaChance(matchOdds.getJSONObject("dupla_chance"));
                duplaChance.partida = partida;
            }

            if ( ! matchOdds.isNull("ambas_marcam")) {
                ambasMarcam = new AmbasMarcam(matchOdds.getJSONObject("ambas_marcam"));
                ambasMarcam.partida = partida;
            }

            if ( ! matchOdds.isNull("vencedor_ambas_marcam")) {
                vencedorAmbasMarcam = new VencedorAmbasMarcam(matchOdds.getJSONObject("vencedor_ambas_marcam"));
                vencedorAmbasMarcam.partida = partida;
            }

            if ( ! matchOdds.isNull("intervalo_final")) {
                intervaloFinal = new IntervaloFinal(matchOdds.getJSONObject("intervalo_final"));
                intervaloFinal.partida = partida;
            }

            if ( ! matchOdds.isNull("gols_par_impar")) {
                parOuImpar = new ParImpar(matchOdds.getJSONObject("gols_par_impar"));
                parOuImpar.partida = partida;
            }

            if ( ! matchOdds.isNull("empate_anula_aposta")) {
                empateAnulaAposta = new EmpateAnulaAposta(matchOdds.getJSONObject("empate_anula_aposta"));
                empateAnulaAposta.partida = partida;
            }

            if ( ! matchOdds.isNull("placares")) {
                placaresCasa   = getPlacars(partida, matchOdds.getJSONObject("placares").getJSONArray("CS"));
                placaresEmpate = getPlacars(partida, matchOdds.getJSONObject("placares").getJSONArray("EP"));
                placaresFora   = getPlacars(partida, matchOdds.getJSONObject("placares").getJSONArray("FR"));
            }

            if ( ! matchOdds.isNull("gols_parcial")) {
                golsMaisMenos = new GolsMaisMenos(matchOdds.getJSONObject("gols_parcial"));
                golsMaisMenos.partida = partida;
            }

            if ( ! matchOdds.isNull("tempo_com_mais_gols")) {
                tempoComMaisGols = new TempoComMaisGols(matchOdds.getJSONObject("tempo_com_mais_gols"));
                tempoComMaisGols.partida = partida;
            }

            if ( ! matchOdds.isNull("faixa_de_gols")) {
                faixaDeGols = new FaixaDeGols(matchOdds.getJSONObject("faixa_de_gols"));
                faixaDeGols.partida = partida;
            }

            if ( ! matchOdds.isNull("vence_sem_levar_gols")) {

                if ( ! matchOdds.getJSONObject("vence_sem_levar_gols").isNull("casa")) {
                    casaVenceSemLevarGols = new VenceSemLevarGols( matchOdds.getJSONObject("vence_sem_levar_gols").getJSONObject("casa") );
                    casaVenceSemLevarGols.tipo = VenceSemLevarGols.CASA;
                    casaVenceSemLevarGols.partida = partida;
                }

                if ( ! matchOdds.getJSONObject("vence_sem_levar_gols").isNull("fora")) {
                    foraVenceSemLevarGols = new VenceSemLevarGols( matchOdds.getJSONObject("vence_sem_levar_gols").getJSONObject("fora") );
                    foraVenceSemLevarGols.tipo = VenceSemLevarGols.FORA;
                    foraVenceSemLevarGols.partida = partida;
                }
            }
        }


        private List<Placar> getPlacars (Partida partida, @NonNull JSONArray placaresJSON) throws JSONException {
            List<Placar> placares = new ArrayList<>();

            for (int i = 0; i < placaresJSON.length(); i++) {

                JSONObject placarJSON = placaresJSON.getJSONObject( i );

                Placar placar = new Placar();
                placar.id = placarJSON.getLong("id");
                placar.partida = partida;
                placar.tipo = placarJSON.getString("tipo");
                placar.casa = placarJSON.getInt("casa");
                placar.fora = placarJSON.getInt("fora");
                placar.odds = placarJSON.getDouble("odds");

                placares.add(placar);
            }

            return (placares.size() > 0) ? placares : null;
        }
    }



    public static class DoisTempos extends BaseResponse {


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


        public static DoisTempos receive(Partida partida, @NonNull String bodyString) {
            DoisTempos response = new DoisTempos();

            try {

                JSONObject json = new JSONObject(bodyString);
                JSONObject body = json.getJSONObject("body");
                response.code = json.getInt("code");

                if (response.code == 200) {

                    JSONObject matchOdds = body.getJSONObject("match_odds");

                    response.loadFH(partida, matchOdds.getJSONObject("primeiro_tempo"));
                    response.loadSH(partida, matchOdds.getJSONObject("segundo_tempo"));

                    BetStack.instance().load(body.getJSONArray("currentbets"), body.getInt("minimun"));
                }
            }
            catch (JSONException e) {
                response.code = 512;
                e.printStackTrace();
            }

            return response;
        }


        private void loadFH(Partida partida, JSONObject matchOdds) throws JSONException {

            if ( ! matchOdds.isNull("pt_principal")) {
                resultadoP = new ResultadoP(matchOdds.getJSONObject("pt_principal"));
                resultadoP.partida = partida;
            }

            if ( ! matchOdds.isNull("pt_dupla_chance")) {
                duplaChanceP = new DuplaChanceP(matchOdds.getJSONObject("pt_dupla_chance"));
                duplaChanceP.partida = partida;
            }

            if ( ! matchOdds.isNull("pt_ambas_marcam")) {
                ambasMarcamP = new AmbasMarcamP(matchOdds.getJSONObject("pt_ambas_marcam"));
                ambasMarcamP.partida = partida;
            }

            if ( ! matchOdds.isNull("pt_gols_par_impar")) {
                parOuImparP = new ParImparP(matchOdds.getJSONObject("pt_gols_par_impar"));
                parOuImparP.partida = partida;
            }

            if ( ! matchOdds.isNull("pt_gols_parcial")) {
                golsMaisMenosP = new GolsMaisMenosP(matchOdds.getJSONObject("pt_gols_parcial"));
                golsMaisMenosP.partida = partida;
            }
        }


        private void loadSH(Partida partida, JSONObject matchOdds) throws JSONException {

            if ( ! matchOdds.isNull("st_principal")) {
                resultadoS = new ResultadoS(matchOdds.getJSONObject("st_principal"));
                resultadoS.partida = partida;
            }

            if ( ! matchOdds.isNull("st_dupla_chance")) {
                duplaChanceS = new DuplaChanceS(matchOdds.getJSONObject("st_dupla_chance"));
                duplaChanceS.partida = partida;
            }

            if ( ! matchOdds.isNull("st_ambas_marcam")) {
                ambasMarcamS = new AmbasMarcamS(matchOdds.getJSONObject("st_ambas_marcam"));
                ambasMarcamS.partida = partida;
            }

            if ( ! matchOdds.isNull("st_gols_par_impar")) {
                parOuImparS = new ParImparS(matchOdds.getJSONObject("st_gols_par_impar"));
                parOuImparS.partida = partida;
            }

            if ( ! matchOdds.isNull("st_gols_parcial")) {
                golsMaisMenosS = new GolsMaisMenosS(matchOdds.getJSONObject("st_gols_parcial"));
                golsMaisMenosS.partida = partida;
            }
        }
    }
}

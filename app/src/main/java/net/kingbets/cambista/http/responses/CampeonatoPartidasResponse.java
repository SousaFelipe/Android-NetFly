package net.kingbets.cambista.http.responses;


import android.support.annotation.NonNull;

import net.kingbets.cambista.http.models.apostas.BetStack;
import net.kingbets.cambista.http.models.futebol.CampeonatoPartidas;
import net.kingbets.cambista.http.models.futebol.Partida;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CampeonatoPartidasResponse extends BaseResponse {



    public List<CampeonatoPartidas> body;



    private CampeonatoPartidasResponse() {
        this.code = 0;
        this.body = new ArrayList<>();
    }



    public static CampeonatoPartidasResponse receive(String bodyString) {
        CampeonatoPartidasResponse response = new CampeonatoPartidasResponse();

        try {

            JSONObject json = new JSONObject(bodyString);
            response.code = json.getInt("code");

            if (response.code == 200) {

                JSONObject body = json.getJSONObject("body");
                JSONArray campeonatos = body.getJSONArray("campeonatos");

                for (int c = 0; c < campeonatos.length(); c++) {
                    CampeonatoPartidas campeonatoPartidas = getCampeonatoPartidas(campeonatos.getJSONObject( c ));
                    response.body.add(campeonatoPartidas);
                }

                BetStack.instance().load(body.getJSONArray("currentbets"), body.getInt("minimun"));
            }
        }
        catch (JSONException e) {
            response.code = 512;
        }

        return response;
    }



    private static CampeonatoPartidas getCampeonatoPartidas(@NonNull JSONObject objetct) throws JSONException {

        CampeonatoPartidas campeonatoPartidas = new CampeonatoPartidas();
        campeonatoPartidas.id       = objetct.getInt("id");
        campeonatoPartidas.bandeira = objetct.getString("bandeira");
        campeonatoPartidas.titulo   = objetct.getString("titulo");
        campeonatoPartidas.partidas = getPartidas(objetct);

        return campeonatoPartidas;
    }



    private static List<Partida> getPartidas(@NonNull JSONObject campeonatoJSON) throws JSONException {

        List<Partida> partidas = new ArrayList<>();
        JSONArray partidasJSON = campeonatoJSON.getJSONArray("partidas");

        for (int p = 0; p < partidasJSON.length(); p++) {

            Partida partida = new Partida();
            partida.id          = partidasJSON.getJSONObject( p ).getLong("id");
            partida.campeonato  = campeonatoJSON.getString("titulo");
            partida.casa        = partidasJSON.getJSONObject( p ).getString("casa");
            partida.fora        = partidasJSON.getJSONObject( p ).getString("fora");
            partida.data        = partidasJSON.getJSONObject( p ).getString("data");
            partida.inicio      = partidasJSON.getJSONObject( p ).getString("inicio");
            partida.setODDS( partidasJSON.getJSONObject( p ).getJSONObject("odds") );

            partidas.add(partida);
        }

        return partidas;
    }
}

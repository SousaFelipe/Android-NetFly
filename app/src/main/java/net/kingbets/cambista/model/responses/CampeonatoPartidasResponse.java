package net.kingbets.cambista.model.responses;


import net.kingbets.cambista.model.BaseResponse;
import net.kingbets.cambista.model.remote.futebol.CampeonatoPartidas;
import net.kingbets.cambista.model.remote.futebol.Partida;

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
                JSONArray body = json.getJSONArray("body");

                for (int c = 0; c < body.length(); c++) {
                    CampeonatoPartidas campeonatoPartidas = getCampeonatoPartidas(body.getJSONObject( c ));
                    response.body.add(campeonatoPartidas);
                }
            }
        }
        catch (JSONException e) {
            response.code = 512;
        }

        return response;
    }



    private static CampeonatoPartidas getCampeonatoPartidas(JSONObject objetct) throws JSONException {

        CampeonatoPartidas campeonatoPartidas = new CampeonatoPartidas();
        campeonatoPartidas.id       = objetct.getJSONObject("campeonato").getInt("id");
        campeonatoPartidas.bandeira = objetct.getJSONObject("campeonato").getString("bandeira");
        campeonatoPartidas.titulo   = objetct.getJSONObject("campeonato").getString("titulo");
        campeonatoPartidas.partidas = getPartidas(objetct.getJSONObject("campeonato"));

        return campeonatoPartidas;
    }



    private static List<Partida> getPartidas(JSONObject campeonatoJSON) throws JSONException {

        List<Partida> partidas = new ArrayList<>();
        JSONArray JSONPartidas = campeonatoJSON.getJSONArray("partidas");

        for (int p = 0; p < JSONPartidas.length(); p++) {

            Partida partida = new Partida();
            partida.id          = JSONPartidas.getJSONObject( p ).getLong("id");
            partida.campeonato  = campeonatoJSON.getString("titulo");
            partida.casa        = JSONPartidas.getJSONObject( p ).getString("casa");
            partida.fora        = JSONPartidas.getJSONObject( p ).getString("fora");
            partida.data        = JSONPartidas.getJSONObject( p ).getString("data");
            partida.inicio      = JSONPartidas.getJSONObject( p ).getString("inicio");
            partida.setODDS( JSONPartidas.getJSONObject( p ).getJSONObject("odds") );

            partidas.add(partida);
        }

        return partidas;
    }
}

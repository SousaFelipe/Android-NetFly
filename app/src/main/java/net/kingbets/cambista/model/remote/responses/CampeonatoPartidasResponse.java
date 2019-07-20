package net.kingbets.cambista.model.remote.responses;


import android.util.Log;

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
            JSONArray body = json.getJSONArray("body");
            response.code = json.getInt("code");

            if (response.code == 200) {

                for (int c = 0; c < body.length(); c++) {

                    CampeonatoPartidas campeonato = new CampeonatoPartidas();
                    campeonato.id = body.getJSONObject( c ).getJSONObject("campeonato").getInt("id");
                    campeonato.bandeira = body.getJSONObject( c ).getJSONObject("campeonato").getString("bandeira");
                    campeonato.titulo = body.getJSONObject( c ).getJSONObject("campeonato").getString("titulo");
                    campeonato.serie = body.getJSONObject( c ).getJSONObject("campeonato").getString("serie");
                    campeonato.partidas = new ArrayList<>();

                    JSONArray partidas = body.getJSONObject( c ).getJSONObject("campeonato").getJSONArray("partidas");

                    for (int p = 0; p < partidas.length(); p++) {

                        Partida partida = new Partida();
                        partida.casa = partidas.getJSONObject( p ).getString("casa");
                        partida.fora = partidas.getJSONObject( p ).getString("fora");
                        partida.inicio = partidas.getJSONObject( p ).getString("inicio");

                        partida.setODDS( partidas.getJSONObject( p ).getJSONObject("odds") );

                        campeonato.partidas.add(partida);
                    }

                    response.body.add(campeonato);
                }
            }
        }
        catch (JSONException e) {
            Log.e("CAMPEONATO_PARTIDAS", "receive: ", e);
            response.code = 512;
        }

        return response;
    }
}

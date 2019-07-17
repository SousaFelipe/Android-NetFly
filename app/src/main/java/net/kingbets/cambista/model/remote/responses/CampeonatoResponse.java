package net.kingbets.cambista.model.remote.responses;


import android.support.annotation.NonNull;

import net.kingbets.cambista.model.BaseResponse;
import net.kingbets.cambista.model.remote.futebol.Campeonato;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CampeonatoResponse extends BaseResponse {



    public List<Campeonato> body;



    private CampeonatoResponse() {
        this.code = 0;
        this.body = new ArrayList<>();
    }



    public static CampeonatoResponse receive(@NonNull String bodyString) {

        CampeonatoResponse response = new CampeonatoResponse();

        try {

            JSONObject json = new JSONObject(bodyString);
            JSONArray body = json.getJSONArray("body");
            response.code = json.getInt("code");

            if (response.code == 200) {

                for (int i = 0; i < body.length(); i++) {

                    Campeonato campeonato = new Campeonato();

                    campeonato.id = body.getJSONObject(i).getInt("id");
                    campeonato.bandeira = body.getJSONObject(i).getString("bandeira");
                    campeonato.titulo = body.getJSONObject(i).getString("titulo");
                    campeonato.serie = body.getJSONObject(i).getString("serie");
                    campeonato.partidas = body.getJSONObject(i).getInt("proximas_partidas");
                    campeonato.fim = body.getJSONObject(i).getString("fim");

                    response.body.add(campeonato);
                }
            }
        }
        catch (JSONException e) {
            response.code = 512;
        }

        return response;
    }
}

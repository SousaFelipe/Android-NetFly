package net.kingbets.cambista.http.responses;


import android.support.annotation.NonNull;

import net.kingbets.cambista.http.models.futebol.Campeonato;

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

                    campeonato.id = body.getJSONObject( i ).getInt("id");
                    campeonato.bandeira = body.getJSONObject( i ).getString("bandeira");
                    campeonato.titulo = body.getJSONObject( i ).getString("titulo");
                    campeonato.partidas = body.getJSONObject( i ).getInt("partidas_registradas");

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

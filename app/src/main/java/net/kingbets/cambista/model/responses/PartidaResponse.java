package net.kingbets.cambista.model.responses;


import android.support.annotation.NonNull;

import net.kingbets.cambista.model.BaseResponse;
import net.kingbets.cambista.model.remote.futebol.Partida;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PartidaResponse extends BaseResponse {



    public List<Partida> body;



    private PartidaResponse() {
        this.code = 0;
        this.body = new ArrayList<>();
    }



    public static PartidaResponse receive(@NonNull String bodyString) {

        PartidaResponse response = new PartidaResponse();

        try {

            JSONObject json = new JSONObject(bodyString);
            JSONArray body = json.getJSONArray("body");
            response.code = json.getInt("code");

            if (response.code == 200) {

            }
        }
        catch (JSONException e) {
            response.code = 512;
        }

        return response;
    }
}

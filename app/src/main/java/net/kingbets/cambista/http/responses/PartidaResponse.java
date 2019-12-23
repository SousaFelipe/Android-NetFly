package net.kingbets.cambista.http.responses;


import android.support.annotation.NonNull;

import net.kingbets.cambista.http.models.apostas.BetStack;
import net.kingbets.cambista.model.futebol.Partida;

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
            JSONObject body = json.getJSONObject("body");
            response.code = json.getInt("code");

            if (response.code == 200) {

                BetStack.instance().load(body.getJSONArray("currentbets"), body.getInt("minimun"));
            }
        }
        catch (JSONException e) {
            response.code = 512;
        }

        return response;
    }
}

package net.kingbets.cambista.model.remote.responses;


import android.support.annotation.NonNull;

import net.kingbets.cambista.model.local.Cambista;

import org.json.JSONException;
import org.json.JSONObject;


public class CambistaResponse {



    public int code;
    public Cambista body;



    private CambistaResponse() {
        this.code = 0;
        this.body = new Cambista();
    }


    public static CambistaResponse receive(@NonNull String bodyString) {

        CambistaResponse response = new CambistaResponse();

        try {

            JSONObject json = new JSONObject(bodyString);
            JSONObject body = json.getJSONObject("body");
            response.code = json.getInt("code");

            if (response.code == 200) {
                response.body.clean = 0;
                response.body.nome = body.getString("nome");
                response.body.email = body.getString("email");
                response.body.senha = body.getString("senha");
                response.body.token = body.getString("token");
            }
        }
        catch (JSONException e) {
            response.code = 512;
        }

        return response;
    }
}

package net.kingbets.cambista.model.responses;


import android.support.annotation.NonNull;

import net.kingbets.cambista.model.BaseResponse;
import net.kingbets.cambista.model.local.Cambista;

import org.json.JSONException;
import org.json.JSONObject;


public class CambistaResponse extends BaseResponse {



    public Cambista body;



    private CambistaResponse() {
        this.code = 0;
        this.body = new Cambista();
    }


    public static CambistaResponse receive(@NonNull String bodyString) {

        CambistaResponse response = new CambistaResponse();

        try {

            JSONObject json = new JSONObject(bodyString);
            response.code = json.getInt("code");

            if (response.code == 200) {
                JSONObject body = json.getJSONObject("body");

                response.body.clean     = 0;
                response.body.nome      = body.getString("nome");
                response.body.contato   = body.getString("contato");
                response.body.email     = body.getString("email");
                response.body.senha     = body.getString("senha");
                response.body.token     = body.getString("token");
            }
        }
        catch (JSONException e) {
            response.code = 512;
        }

        return response;
    }
}

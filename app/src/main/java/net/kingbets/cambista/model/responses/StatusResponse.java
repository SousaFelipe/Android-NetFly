package net.kingbets.cambista.model.responses;


import android.support.annotation.NonNull;
import android.util.Log;

import net.kingbets.cambista.model.BaseResponse;
import net.kingbets.cambista.model.remote.Sistema.Status;

import org.json.JSONException;
import org.json.JSONObject;


public class StatusResponse extends BaseResponse {



    public int code;
    public Status body;



    private StatusResponse() {
        this.code = 0;
        this.body = new Status();
    }


    public static StatusResponse receive(@NonNull String bodyString) {

        StatusResponse response = new StatusResponse();

        try {

            JSONObject json = new JSONObject(bodyString);
            response.code = json.getInt("code");

            if (response.code == 200) {
                JSONObject body = json.getJSONObject("body");

                response.body.bloqueio = body.getString("bloqueio");
                response.body.deposito = body.isNull("deposito") ? 0.0 : body.getDouble("deposito");
            }
        }
        catch (JSONException e) {
            response.code = 512;
            Log.e("STATUS", "receive: ", e);
        }

        return response;
    }
}

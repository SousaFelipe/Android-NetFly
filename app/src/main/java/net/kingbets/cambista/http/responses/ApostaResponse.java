package net.kingbets.cambista.http.responses;


import android.support.annotation.NonNull;

import net.kingbets.cambista.http.models.apostas.BetStack;

import org.json.JSONException;
import org.json.JSONObject;


public class ApostaResponse extends BaseResponse {



    public enum Status {
        OK, ERROR, APOSTA_N_EXISTE, PARTIDA_DUPLICADA, DESCONHECIDO
    }



    private ApostaResponse() { }



    public static Status receive(@NonNull String bodyString) {

        try {

            JSONObject json = new JSONObject(bodyString);
            JSONObject body = json.getJSONObject("body");

            if (json.getInt("code") == 200) {

                switch (body.getString("status")) {

                    case "OK":
                        BetStack.instance().load(body.getJSONArray("currentbets"), body.getInt("minimun"));
                        return Status.OK;

                    case "ERROR": return Status.ERROR;
                    case "APOSTA_N_EXISTE": return Status.APOSTA_N_EXISTE;
                    case "PARTIDA_DUPLICADA": return Status.PARTIDA_DUPLICADA;

                    default: return Status.DESCONHECIDO;
                }
            }
        }
        catch (JSONException e) {
            return Status.DESCONHECIDO;
        }

        return Status.DESCONHECIDO;
    }
}

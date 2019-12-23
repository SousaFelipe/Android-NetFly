package net.kingbets.cambista.http.responses;


import android.util.Log;

import net.kingbets.cambista.model.Perfil;

import org.json.JSONException;
import org.json.JSONObject;


public class PerfilResponse extends BaseResponse {



    public Perfil body;



    private PerfilResponse() {
        this.code = 0;
        this.body = new Perfil();
    }



    public static PerfilResponse receive(String bodyString) {
        PerfilResponse response = new PerfilResponse();

        try {

            JSONObject json = new JSONObject(bodyString);
            response.code = json.getInt("code");

            if (response.code == 200) {
                JSONObject body = json.getJSONObject("body");

                response.body.gerente = body.getString("gerente");
                response.body.nome = body.getString("nome");
                response.body.limite = body.getDouble("limite");
                response.body.limiteApostas = body.getInt("limite_apostas");
                response.body.xp = body.getDouble("pontuacao_cambio");
                response.body.xpMaximo = body.getDouble("pontuacao_cambio_maxima");
            }
        }
        catch (JSONException e) {
            response.code = 512;
            Log.e("PERFIL_RESPONSE", "receiveOne: ", e);
        }

        return response;
    }
}

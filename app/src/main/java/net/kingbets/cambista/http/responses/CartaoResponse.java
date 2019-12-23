package net.kingbets.cambista.http.responses;


import android.support.annotation.NonNull;

import net.kingbets.cambista.http.models.Sistema.Cartao;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CartaoResponse extends BaseResponse {



    public Body body;



    private CartaoResponse() {
        this.code = 0;
        this.body = new Body();
    }



    public static CartaoResponse receive(@NonNull String bodyString) {

        CartaoResponse response = new CartaoResponse();

        try {

            JSONObject json = new JSONObject(bodyString);
            JSONObject body = json.getJSONObject("body");
            response.code = json.getInt("code");

            if (response.code == 200) {

                if (!body.isNull("atual")) {
                    JSONObject jsonAtual = body.getJSONObject("atual");
                    response.body.atual = parseFromJSON(jsonAtual);
                }

                if (!body.isNull("outros")) {

                    JSONArray jsonAnteriores = body.getJSONArray("outros");

                    for (int i = 0; i < jsonAnteriores.length(); i++) {
                        response.body.anteriores.add( parseFromJSON(jsonAnteriores.getJSONObject(i)) );
                    }
                }
            }
        }
        catch (JSONException e) {
            response.code = 512;
        }

        return response;
    }



    private static Cartao parseFromJSON (@NotNull JSONObject jsonObject) throws JSONException {
        Cartao cartao = new Cartao();
        cartao.id = jsonObject.getLong("id");
        cartao.cliente = jsonObject.getLong("cliente");
        cartao.cambista = jsonObject.getLong("cambista");
        cartao.valorInicial = jsonObject.getDouble("valor_inicial");
        cartao.valorAtual = jsonObject.getDouble("valor_atual");
        cartao.validadoAte = jsonObject.getString("valido_ate");
        cartao.quantBilhetses = jsonObject.has("bilhetes") ? jsonObject.getInt("bilhetes") : 0;
        return cartao;
    }



    public class Body {


        public Cartao atual;
        public List<Cartao> anteriores;


        Body() {
            this.atual = null;
            this.anteriores = new ArrayList<>();
        }
    }
}

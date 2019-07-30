package net.kingbets.cambista.model.responses;


import net.kingbets.cambista.model.BaseResponse;
import net.kingbets.cambista.model.remote.Caixa;

import org.json.JSONException;
import org.json.JSONObject;


public class CaixaResponse extends BaseResponse {



    public Caixa body;



    private CaixaResponse() {
        this.code = 0;
        this.body = new Caixa();
    }



    public static CaixaResponse receive(String bodyString) {
        CaixaResponse response = new CaixaResponse();

        try {

            JSONObject json = new JSONObject(bodyString);
            response.code = json.getInt("code");

            if (response.code == 200) {
                JSONObject body = json.getJSONObject("body");

                response.body.quantApostas = body.getInt("apostas");
                response.body.entrada = body.getDouble("entrada");
                response.body.quant_premios_cambista = body.getInt("quant_premios_ca");
                response.body.quant_premios_kingbets = body.getInt("quant_premios_ki");
                response.body.premios_cambista = body.getDouble("premios_cambista");
                response.body.premios_kingbets = body.getDouble("premios_kingbets");
                response.body.comissao = body.getDouble("comissao");
                response.body.deposito = body.getDouble("deposito");
            }
        }
        catch (JSONException e) {
            response.code = 512;
        }

        return response;
    }
}

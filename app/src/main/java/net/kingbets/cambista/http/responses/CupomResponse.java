package net.kingbets.cambista.http.responses;


import android.util.Log;

import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.model.apostas.Cupom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CupomResponse extends BaseResponse {



    public Cupom body;



    private CupomResponse() {
        body = new Cupom();
    }



    public static String receiveCode(String bodyString) {
        String codigo = "";

        try {

            JSONObject json = new JSONObject(bodyString);

            if (json.getInt("code") == 200) {

                JSONObject body = json.getJSONObject("body");
                String status = body.getString("status");

                if (status.equals("OK")) {
                    codigo = body.getString("codigo");
                }
                else {
                    Log.e("RECEIVE_CODE", "receiveCode: " + status);
                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return codigo;
    }



    public static CupomResponse receiveOne(String bodyString) {
        CupomResponse response = new CupomResponse();

        try {

            JSONObject json = new JSONObject(bodyString);
            response.code = json.getInt("code");

            if (response.code == 200) {

                JSONObject body = json.getJSONObject("body");

                response.body = getCupom(body.getJSONObject("cupom"));
                response.body.bets = getBets(body.getJSONArray("apostas"));
            }
        }
        catch (JSONException e) {
            response.code = 512;
        }

        return response;
    }



    public static List<Cupom> receiveMany(String bodyString) {
        List<Cupom> cupons = new ArrayList<>();

        try {

            JSONObject json = new JSONObject(bodyString);
            int code = json.getInt("code");

            if (code == 200) {

                JSONArray body = json.getJSONArray("body");

                for (int i = 0; i < body.length(); i++) {
                    Cupom cupom = getCupom( body.getJSONObject( i ) );
                    cupons.add(cupom);
                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return cupons;
    }



    private static Cupom getCupom(JSONObject JSONCupom) throws JSONException {

        Cupom cupom = new Cupom();

        cupom.id = JSONCupom.getLong("id");
        cupom.codigo = JSONCupom.getString("codigo");
        cupom.cambista = JSONCupom.getLong("cambista");
        cupom.apostador = JSONCupom.getString("apostador");
        cupom.cotacao = JSONCupom.getDouble("cotacao");
        cupom.quantApostas = JSONCupom.getInt("quant_apostas");
        cupom.valorApostado = JSONCupom.getDouble("valor_apostado");
        cupom.possivelRetorno = JSONCupom.getDouble("possivel_retorno");
        cupom.status = JSONCupom.getString("status");
        cupom.data = JSONCupom.getString("data");
        cupom.hora = JSONCupom.getString("hora");

        return cupom;
    }



    private static List<Bet> getBets(JSONArray apostasJSON) throws JSONException {

        List<Bet> bets = new ArrayList<>();

        for (int i = 0; i < apostasJSON.length(); i++) {

            JSONObject betJSON = apostasJSON.getJSONObject(i);

            Bet bet = new Bet(betJSON);
            bet.setPartida(betJSON.getJSONObject("partida"));

            bets.add(bet);
        }

        return bets;
    }
}

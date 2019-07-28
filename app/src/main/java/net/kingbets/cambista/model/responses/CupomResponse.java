package net.kingbets.cambista.model.responses;


import net.kingbets.cambista.model.BaseResponse;
import net.kingbets.cambista.model.remote.apostas.Aposta;
import net.kingbets.cambista.model.remote.apostas.Cupom;
import net.kingbets.cambista.model.remote.futebol.Partida;

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



    public static CupomResponse receive(String bodyString) {
        CupomResponse response = new CupomResponse();

        try {

            JSONObject json = new JSONObject(bodyString);
            response.code = json.getInt("code");

            if (response.code == 200) {
                JSONObject body = json.getJSONObject("body");
                response.body = getCupom(body.getJSONObject("cupom"));
                response.body.apostas = getApostas( body.getJSONArray("apostas") );
            }
        }
        catch (JSONException e) {
            response.code = 512;
        }

        return response;
    }



    public static List<Cupom> receiveAll(String bodyString) {
        List<Cupom> cupons = new ArrayList<>();

        try {

            JSONObject json = new JSONObject(bodyString);
            int code = json.getInt("code");

            if (code == 200) {

                JSONArray body = json.getJSONArray("body");

                for (int i = 0; i < body.length(); i++) {
                    Cupom cupom = getCupom( body.getJSONObject( i ) );
                    cupom.apostas = getApostas( body.getJSONObject( i ).getJSONArray("apostas") );
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
        cupom.comissao = JSONCupom.getDouble("comissao");
        cupom.status = JSONCupom.getString("status");
        cupom.data = JSONCupom.getString("data");
        cupom.hora = JSONCupom.getString("hora");

        return cupom;
    }



    private static List<Aposta> getApostas(JSONArray JSONApostas) throws JSONException {
        List<Aposta> apostas = new ArrayList<>();

        for (int i = 0; i < JSONApostas.length(); i++) {

            Aposta aposta = new Aposta();
            aposta.id = JSONApostas.getJSONObject( i ).getLong("id");
            aposta.cupom = JSONApostas.getJSONObject( i ).getLong("cupom");
            aposta.partida = getPartida(JSONApostas.getJSONObject( i ).getJSONObject("partida"));
            aposta.titulo = JSONApostas.getJSONObject( i ).getString("titulo");
            aposta.tipo = JSONApostas.getJSONObject( i ).getString("tipo");
            aposta.sentenca = JSONApostas.getJSONObject( i ).getString("sentenca");
            aposta.cotacao = JSONApostas.getJSONObject( i ).getDouble("cotacao");
            aposta.status = JSONApostas.getJSONObject( i ).getString("status");

            apostas.add(aposta);
        }

        return apostas;
    }



    private static Partida getPartida(JSONObject JSONPartida) throws JSONException {
        Partida partida = new Partida();

        partida.campeonato = JSONPartida.getString("campeonato");
        partida.casa    = JSONPartida.getString("casa");
        partida.fora    = JSONPartida.getString("fora");
        partida.data    = JSONPartida.getString("data");
        partida.inicio  = JSONPartida.getString("inicio");

        return partida;
    }
}

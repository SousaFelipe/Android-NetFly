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
                JSONObject cupom = body.getJSONObject("cupom");

                response.body.id = cupom.getLong("id");
                response.body.codigo = cupom.getString("codigo");
                response.body.cambista = cupom.getLong("cambista");
                response.body.apostador = cupom.getString("apostador");
                response.body.cotacao = cupom.getDouble("cotacao");
                response.body.quantApostas = cupom.getInt("quant_apostas");
                response.body.valorApostado = cupom.getDouble("valor_apostado");
                response.body.possivelRetorno = cupom.getDouble("possivel_retorno");
                response.body.comissao = cupom.getDouble("comissao");
                response.body.status = cupom.getString("status");
                response.body.data = cupom.getString("data");
                response.body.hora = cupom.getString("hora");
                response.body.apostas = getApostas( body.getJSONArray("apostas") );
            }
        }
        catch (JSONException e) {
            response.code = 512;
        }

        return response;
    }



    private static List<Aposta> getApostas(JSONArray JSONApostas) throws JSONException {
        List<Aposta> apostas = new ArrayList<>();

        for (int i = 0; i < JSONApostas.length(); i++) {

            Aposta aposta = new Aposta();
            aposta.id = JSONApostas.getJSONObject(i).getLong("id");
            aposta.cupom = JSONApostas.getJSONObject(i).getLong("cupom");
            aposta.partida = getPartida(JSONApostas.getJSONObject(i).getJSONObject("partida"));
            aposta.titulo = JSONApostas.getJSONObject(i).getString("titulo");
            aposta.tipo = JSONApostas.getJSONObject(i).getString("tipo");
            aposta.sentenca = JSONApostas.getJSONObject(i).getString("sentenca");
            aposta.cotacao = JSONApostas.getJSONObject(i).getDouble("cotacao");
            aposta.status = JSONApostas.getJSONObject(i).getString("status");

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

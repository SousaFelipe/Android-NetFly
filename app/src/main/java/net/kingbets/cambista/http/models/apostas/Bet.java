package net.kingbets.cambista.http.models.apostas;


import android.content.Context;

import net.kingbets.cambista.http.models.futebol.Partida;
import net.kingbets.cambista.http.models.odds.principais.Placar;
import net.kingbets.cambista.utils.Config;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;


public class Bet {



    private static final String STATUS_AGUARDANDO   = "AGUARDANDO";
    private static final String STATUS_CANCELADA    = "CANCELADA";
    private static final String STATUS_ADIADA       = "ADIADA";
    private static final String STATUS_GANHOU       = "GANHOU";
    private static final String STATUS_PERDEU       = "PERDEU";



    public long     odd;
    public long     cupom;
    public Partida  partida;
    public String   titulo;
    public String   tipo;
    public String   sentenca;
    public double   cotacao;
    public String   status;



    public Bet(String tipo) {
        this.tipo = tipo;
    }

    public Bet(JSONObject jsonBet) {

        try {

            odd = jsonBet.has("odd") ? jsonBet.getLong("odd") : 0;
            cupom = jsonBet.has("cupom") ? jsonBet.getLong("cupom") : 0;

            titulo = jsonBet.getString("titulo");
            tipo = jsonBet.getString("tipo");
            sentenca = jsonBet.getString("sentenca");
            cotacao = jsonBet.getDouble("cotacao");

            setPartida(jsonBet.getJSONObject("partida"));
            setStatus(jsonBet.has("status") ? jsonBet.getString("status") : "A");

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public void setPartida(JSONObject partidaJSON) {

        this.partida = new Partida();

        try {
            this.partida.id = partidaJSON.getLong("id");
            this.partida.campeonato = partidaJSON.getString("campeonato");
            this.partida.casa = partidaJSON.getString("casa");
            this.partida.fora = partidaJSON.getString("fora");
            this.partida.data = partidaJSON.getString("data");
            this.partida.inicio = partidaJSON.getString("inicio");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setStatus(String status) {
        switch (status) {

            case "A":
                this.status = STATUS_AGUARDANDO;
                break;

            case "C":
                this.status = STATUS_CANCELADA;
                break;

            case "D":
                this.status = STATUS_ADIADA;
                break;

            case "G":
                this.status = STATUS_GANHOU;
                break;

            case "P":
                this.status = STATUS_PERDEU;
                break;

            default:
                this.status = "ERRO";
        }
    }



    public Bet odd(long odd) {
        this.odd = odd;
        return this;
    }

    public Bet partida(Partida partida) {
        this.partida = partida;
        return this;
    }


    public Bet titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public Bet titulo(Placar placar) {
        this.titulo = ("Placar - Casa: " + placar.casa + " x Fora: " + placar.fora);
        return this;
    }


    public Bet tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }



    public Bet sentenca(String sentenca) {
        this.sentenca = sentenca;
        return this;
    }

    public Bet sentenca(Placar placar) {
        this.sentenca = placar.casa + ";" + placar.fora;
        return this;
    }


    public Bet cotacao(double cotacao) {
        this.cotacao = cotacao;
        return this;
    }



    public RequestBody getRequestBody(Context context) {

        FormBody.Builder formBuilder = new FormBody.Builder();

        formBuilder.add("device", Config.getDeviceInfo(context));
        formBuilder.add("odd", String.valueOf(odd));
        formBuilder.add("partida", String.valueOf(partida.id));
        formBuilder.add("titulo", String.valueOf(titulo));
        formBuilder.add("tipo", String.valueOf(tipo));
        formBuilder.add("sentenca", String.valueOf(sentenca));
        formBuilder.add("cotacao", String.valueOf(cotacao));

        return formBuilder.build();
    }
}

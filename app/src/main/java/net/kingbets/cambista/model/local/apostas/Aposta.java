package net.kingbets.cambista.model.local.apostas;


import net.kingbets.cambista.model.BaseModel;
import net.kingbets.cambista.model.local.futebol.Partida;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;


public class Aposta extends BaseModel {


    public static final String AGUARDANDO   = "Aguardando";
    public static final String GANHOU       = "Ganhou";
    public static final String PERDEU       = "Perdeu";




    public String   cupom;
    public Partida  partida;
    public String   titulo;
    public String   tipo;
    public String   sentenca;
    public double   cotacao;
    public String   status;



    public Aposta(String tipo) {
        this.tipo = tipo;
        this.status = AGUARDANDO;
    }



    public Aposta partida(Partida partida) {
        this.partida  = partida;
        return this;
    }



    public Aposta sentenca(String sentenca) {
        this.sentenca = sentenca;
        return this;
    }



    JSONObject toJSON() {

        JSONObject json = new JSONObject();

        try {

            json.put("cupom",       cupom);
            json.put("partida",     partida.id);
            json.put("titulo",      titulo);
            json.put("tipo",        tipo);
            json.put("sentenca",    sentenca);
            json.put("cotacao",     String.format(Locale.getDefault(), "%.2f", cotacao));
            json.put("status",      status);

            return json;

        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}

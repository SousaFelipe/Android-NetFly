package net.kingbets.cambista.model.local.apostas;


import net.kingbets.cambista.model.BaseModel;
import net.kingbets.cambista.model.local.futebol.Partida;
import net.kingbets.cambista.model.remote.odds.principais.Placar;

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



    public Aposta titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }



    public Aposta sentenca(String sentenca) {
        this.sentenca = sentenca;
        return this;
    }



    public Aposta cotacao(double cotacao) {
        this.cotacao = cotacao;
        return this;
    }



    public void setTitulo(Placar placar) {
        this.titulo = "Placar - Casa: " + placar.casa + " x Fora: " + placar.fora;
    }



    public void setSentenca(String prefixo, Placar placar) {
        this.sentenca =  prefixo + ":" + placar.casa + ";" + placar.fora;
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


    @Override
    public boolean equals(Object obj) {

        if (obj != null) {

            Aposta outra = ((Aposta) obj);

            return (
                    (this.titulo    != null && this.titulo.equals( outra.titulo ))  &&
                    (this.tipo      != null && this.tipo.equals( outra.tipo ))      &&
                    (this.sentenca  != null && this.sentenca.equals( outra.sentenca ))
            );
        }

        return false;
    }
}

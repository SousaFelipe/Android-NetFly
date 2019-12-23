package net.kingbets.cambista.http.models.futebol;


import net.kingbets.cambista.http.models.odds.principais.Resultado;
import net.kingbets.cambista.model.BaseModel;

import org.json.JSONException;
import org.json.JSONObject;


public class Partida extends BaseModel {



    public String campeonato;
    public String casa;
    public String fora;
    public String data;
    public String inicio;



    public Resultado probabilidades;



    public void setODDS(JSONObject odds) throws JSONException {
        probabilidades = new Resultado(odds);
        probabilidades.setPartida(this);
    }
}

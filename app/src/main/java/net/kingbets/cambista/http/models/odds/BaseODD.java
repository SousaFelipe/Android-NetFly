package net.kingbets.cambista.http.models.odds;


import net.kingbets.cambista.http.models.futebol.Partida;

import org.json.JSONException;
import org.json.JSONObject;


public abstract class BaseODD {



    public long id;
    public Partida partida;



    protected BaseODD() { }



    protected BaseODD(JSONObject odds) throws JSONException {
        id = (odds != null) ? odds.getLong("id") : 0;
    }



    public void setPartida(Partida partida) {
        this.partida = partida;
    }
}

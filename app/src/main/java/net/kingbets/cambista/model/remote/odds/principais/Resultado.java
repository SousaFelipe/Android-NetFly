package net.kingbets.cambista.model.remote.odds.principais;


import net.kingbets.cambista.model.remote.odds.BaseODD;

import org.json.JSONException;
import org.json.JSONObject;


public class Resultado extends BaseODD {


    public static final String TIPO     = "tt:resultado_final";



    public double casa;
    public double empate;
    public double fora;



    public Resultado(JSONObject odds) throws JSONException {
        casa    = odds != null ? odds.getDouble("casa")     : 0.00;
        empate  = odds != null ? odds.getDouble("empate")   : 0.00;
        fora    = odds != null ? odds.getDouble("fora")     : 0.00;
    }
}

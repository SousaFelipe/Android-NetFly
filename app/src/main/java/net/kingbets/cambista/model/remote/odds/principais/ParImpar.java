package net.kingbets.cambista.model.remote.odds.principais;


import net.kingbets.cambista.model.remote.odds.BaseODD;

import org.json.JSONException;
import org.json.JSONObject;


public class ParImpar extends BaseODD {

    public static final String TIPO = "tt_par_ou_impar";



    public double par;
    public double impar;



    public ParImpar(JSONObject odds) throws JSONException {
        par = odds != null ? odds.getDouble("par") : 0.00;
        impar = odds != null ? odds.getDouble("impar") : 0.00;
    }
}

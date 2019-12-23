package net.kingbets.cambista.http.models.odds.principais;


import net.kingbets.cambista.http.models.odds.BaseODD;

import org.json.JSONException;
import org.json.JSONObject;


public class ParImpar extends BaseODD {

    public static final String TIPO = "tt:par_ou_impar";



    public double par;
    public double impar;



    public ParImpar(JSONObject odds) throws JSONException {
        super(odds);

        par = odds != null ? odds.getDouble("par") : 0.00;
        impar = odds != null ? odds.getDouble("impar") : 0.00;
    }
}

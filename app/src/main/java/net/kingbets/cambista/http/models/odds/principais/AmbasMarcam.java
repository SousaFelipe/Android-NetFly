package net.kingbets.cambista.http.models.odds.principais;


import net.kingbets.cambista.http.models.odds.BaseODD;

import org.json.JSONException;
import org.json.JSONObject;


public class AmbasMarcam extends BaseODD {

    public static final String TIPO = "tt:ambas_marcam";



    public double sim;
    public double nao;



    public AmbasMarcam(JSONObject odds) throws JSONException {
        super(odds);

        sim = odds != null ? odds.getDouble("sim") : 0.00;
        nao = odds != null ? odds.getDouble("nao") : 0.00;
    }
}

package net.kingbets.cambista.model.remote.odds.principais;


import net.kingbets.cambista.model.remote.odds.BaseODD;

import org.json.JSONException;
import org.json.JSONObject;


public class AmbasMarcam extends BaseODD {

    public static final String TIPO = "tt_ambas_marcam";



    public double sim;
    public double nao;



    public AmbasMarcam(JSONObject odds) throws JSONException {
        sim = odds != null ? odds.getDouble("sim") : 0.00;
        nao = odds != null ? odds.getDouble("nao") : 0.00;
    }
}

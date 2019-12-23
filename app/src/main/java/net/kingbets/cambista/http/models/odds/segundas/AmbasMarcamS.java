package net.kingbets.cambista.http.models.odds.segundas;


import net.kingbets.cambista.http.models.odds.principais.AmbasMarcam;

import org.json.JSONException;
import org.json.JSONObject;


public class AmbasMarcamS extends AmbasMarcam {

    public static final String TIPO = "st:ambas_marcam";



    public AmbasMarcamS(JSONObject odds) throws JSONException {
        super(odds);
    }
}

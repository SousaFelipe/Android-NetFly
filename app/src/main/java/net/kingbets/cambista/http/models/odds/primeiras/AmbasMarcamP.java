package net.kingbets.cambista.http.models.odds.primeiras;


import net.kingbets.cambista.http.models.odds.principais.AmbasMarcam;

import org.json.JSONException;
import org.json.JSONObject;


public class AmbasMarcamP extends AmbasMarcam {

    public static final String TIPO = "pt:ambas_marcam";



    public AmbasMarcamP(JSONObject odds) throws JSONException {
        super(odds);
    }
}

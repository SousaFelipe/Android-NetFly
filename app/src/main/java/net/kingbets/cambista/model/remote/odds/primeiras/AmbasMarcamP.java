package net.kingbets.cambista.model.remote.odds.primeiras;


import net.kingbets.cambista.model.remote.odds.principais.AmbasMarcam;

import org.json.JSONException;
import org.json.JSONObject;


public class AmbasMarcamP extends AmbasMarcam {

    public static final String TIPO = "pt_ambas_marcam";



    public AmbasMarcamP(JSONObject odds) throws JSONException {
        super(odds);
    }
}

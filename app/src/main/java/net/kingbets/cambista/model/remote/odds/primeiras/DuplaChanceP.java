package net.kingbets.cambista.model.remote.odds.primeiras;


import net.kingbets.cambista.model.remote.odds.principais.DuplaChance;

import org.json.JSONException;
import org.json.JSONObject;


public class DuplaChanceP extends DuplaChance {

    public static final String TIPO = "pt_dupla_chance";



    public DuplaChanceP(JSONObject odds) throws JSONException {
        super(odds);
    }
}

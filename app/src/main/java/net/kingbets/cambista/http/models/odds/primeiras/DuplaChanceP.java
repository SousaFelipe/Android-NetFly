package net.kingbets.cambista.http.models.odds.primeiras;


import net.kingbets.cambista.http.models.odds.principais.DuplaChance;

import org.json.JSONException;
import org.json.JSONObject;


public class DuplaChanceP extends DuplaChance {

    public static final String TIPO = "pt:dupla_chance";



    public DuplaChanceP(JSONObject odds) throws JSONException {
        super(odds);
    }
}

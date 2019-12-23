package net.kingbets.cambista.http.models.odds.segundas;


import net.kingbets.cambista.http.models.odds.principais.DuplaChance;

import org.json.JSONException;
import org.json.JSONObject;


public class DuplaChanceS extends DuplaChance {

    public static final String TIPO = "st:dupla_chance";



    public DuplaChanceS(JSONObject odds) throws JSONException {
        super(odds);
    }
}

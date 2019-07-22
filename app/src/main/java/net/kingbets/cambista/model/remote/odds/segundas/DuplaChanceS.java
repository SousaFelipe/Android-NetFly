package net.kingbets.cambista.model.remote.odds.segundas;


import net.kingbets.cambista.model.remote.odds.principais.DuplaChance;

import org.json.JSONException;
import org.json.JSONObject;


public class DuplaChanceS extends DuplaChance {

    public static final String TIPO = "st_dupla_chance";



    public DuplaChanceS(JSONObject odds) throws JSONException {
        super(odds);
    }
}

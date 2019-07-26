package net.kingbets.cambista.model.remote.odds.segundas;


import net.kingbets.cambista.model.remote.odds.primeiras.GolsMaisMenosP;

import org.json.JSONException;
import org.json.JSONObject;


public class GolsMaisMenosS extends GolsMaisMenosP {

    public static final String TIPO = "st:gols_mais_menos";



    public GolsMaisMenosS(JSONObject odds) throws JSONException {
        super(odds);
    }
}

package net.kingbets.cambista.http.models.odds.principais;


import net.kingbets.cambista.http.models.odds.primeiras.GolsMaisMenosP;

import org.json.JSONException;
import org.json.JSONObject;


public class GolsMaisMenos extends GolsMaisMenosP {

    public static final String TIPO = "tt:gols_mais_menos";



    public double mais_45;
    public double menos_45;

    public double mais_55;
    public double menos_55;



    public GolsMaisMenos(JSONObject odds) throws JSONException {
        super(odds);

        mais_45 = odds != null ? odds.getDouble("mais_45") : 0.00;
        menos_45 = odds != null ? odds.getDouble("menos_45") : 0.00;

        mais_55 = odds != null ? odds.getDouble("mais_55") : 0.00;
        menos_55 = odds != null ? odds.getDouble("menos_55") : 0.00;
    }
}

package net.kingbets.cambista.model.remote.odds.primeiras;


import net.kingbets.cambista.model.remote.odds.BaseODD;

import org.json.JSONException;
import org.json.JSONObject;


public class GolsMaisMenosP extends BaseODD {

    public static final String TIPO = "pt_gols_mais_menos";



    public double mais_05;
    public double menos_05;

    public double mais_15;
    public double menos_15;

    public double mais_25;
    public double menos_25;

    public double mais_35;
    public double menos_35;



    public GolsMaisMenosP(JSONObject odds) throws JSONException {
        mais_05 = odds != null ? odds.getDouble("mais_05") : 0.00;
        menos_05 = odds != null ? odds.getDouble("menos_05") : 0.00;
        mais_15 = odds != null ? odds.getDouble("mais_15") : 0.00;
        menos_15 = odds != null ? odds.getDouble("menos_15") : 0.00;
        mais_25 = odds != null ? odds.getDouble("mais_25") : 0.00;
        menos_25 = odds != null ? odds.getDouble("menos_25") : 0.00;
        mais_35 = odds != null ? odds.getDouble("mais_35") : 0.00;
        menos_35 = odds != null ? odds.getDouble("menos_35") : 0.00;
    }
}

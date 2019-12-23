package net.kingbets.cambista.http.models.odds.principais;


import net.kingbets.cambista.http.models.odds.BaseODD;

import org.json.JSONException;
import org.json.JSONObject;


public class FaixaDeGols extends BaseODD {



    public static final String TIPO     = "tt:faixa_de_gols";



    public double _01;
    public double _23;
    public double _45;
    public double _M6;



    public FaixaDeGols(JSONObject odds) throws JSONException {
        super(odds);

        _01 = odds != null ? odds.getDouble("_01") : 0.00;
        _23 = odds != null ? odds.getDouble("_23") : 0.00;
        _45 = odds != null ? odds.getDouble("_45") : 0.00;
        _M6 = odds != null ? odds.getDouble("_M6") : 0.00;
    }
}

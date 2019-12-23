package net.kingbets.cambista.http.models.odds.principais;


import net.kingbets.cambista.http.models.odds.BaseODD;

import org.json.JSONException;
import org.json.JSONObject;


public class TempoComMaisGols extends BaseODD {


    public static final String TIPO     = "tt:tempo_com_mais_gols";



    public double primeiro;
    public double segundo;
    public double iguais;



    public TempoComMaisGols(JSONObject odds) throws JSONException {
        super(odds);

        primeiro = odds != null ? odds.getDouble("primeiro")  : 0.00;
        segundo  = odds != null ? odds.getDouble("segundo")   : 0.00;
        iguais   = odds != null ? odds.getDouble("iguais")    : 0.00;
    }
}

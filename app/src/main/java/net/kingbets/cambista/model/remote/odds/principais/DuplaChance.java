package net.kingbets.cambista.model.remote.odds.principais;


import net.kingbets.cambista.model.remote.odds.BaseODD;

import org.json.JSONException;
import org.json.JSONObject;


public class DuplaChance extends BaseODD {

    public static final String TIPO = "tt:dupla_chance";



    public double casaEmpate;
    public double foraEmpate;
    public double casaFora;



    public DuplaChance(JSONObject odds) throws JSONException {
        casaEmpate  = odds != null ? odds.getDouble("casa_empate")  : 0.00;
        foraEmpate  = odds != null ? odds.getDouble("fora_empate")  : 0.00;
        casaFora    = odds != null ? odds.getDouble("casa_fora")    : 0.00;
    }
}

package net.kingbets.cambista.model.remote.odds.principais;


import net.kingbets.cambista.model.remote.odds.BaseODD;

import org.json.JSONException;
import org.json.JSONObject;


public class IntervaloFinal extends BaseODD {

    public static final String TIPO = "tt:intervalo_final";



    public double casaCasa;
    public double casaEmpate;
    public double casaFora;

    public double empateCasa;
    public double empateEmpate;
    public double empateFora;

    public double foraCasa;
    public double foraEmpate;
    public double foraFora;



    public IntervaloFinal(JSONObject odds) throws JSONException {

        casaCasa    = odds != null ? odds.getDouble("casa_casa")    : 0.00;
        casaEmpate  = odds != null ? odds.getDouble("casa_empate")  : 0.00;
        casaFora    = odds != null ? odds.getDouble("casa_fora")    : 0.00;

        empateCasa  = odds != null ? odds.getDouble("empate_casa")  : 0.00;
        empateEmpate= odds != null ? odds.getDouble("empate_empate"): 0.00;
        empateFora  = odds != null ? odds.getDouble("empate_fora")  : 0.00;

        foraCasa    = odds != null ? odds.getDouble("fora_casa")    : 0.00;
        foraEmpate  = odds != null ? odds.getDouble("fora_empate")  : 0.00;
        foraFora    = odds != null ? odds.getDouble("fora_fora")    : 0.00;
    }
}

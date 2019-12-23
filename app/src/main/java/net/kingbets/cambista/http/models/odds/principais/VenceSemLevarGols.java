package net.kingbets.cambista.http.models.odds.principais;


import net.kingbets.cambista.http.models.odds.BaseODD;

import org.json.JSONException;
import org.json.JSONObject;


public class VenceSemLevarGols extends BaseODD {



    public static final String TIPO = "tt:vence_sem_levar_gols";

    public static final String CASA = "C";
    public static final String FORA = "F";



    public String tipo;
    public double sim;
    public double nao;



    public VenceSemLevarGols(JSONObject odds) throws JSONException {
        super(odds);

        sim = odds != null ? odds.getDouble("sim") : 0.00;
        nao = odds != null ? odds.getDouble("nao") : 0.00;
    }
}

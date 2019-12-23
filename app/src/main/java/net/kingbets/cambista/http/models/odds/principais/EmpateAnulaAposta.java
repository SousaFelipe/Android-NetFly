package net.kingbets.cambista.http.models.odds.principais;


import net.kingbets.cambista.http.models.odds.BaseODD;

import org.json.JSONException;
import org.json.JSONObject;


public class EmpateAnulaAposta extends BaseODD {

    public static final String TIPO = "tt:empate_anula_aposta";



    public double casaOuAnula;
    public double foraOuAnula;



    public EmpateAnulaAposta(JSONObject odds) throws JSONException {
        super(odds);

        casaOuAnula = odds != null ? odds.getDouble("casa") : 0.00;
        foraOuAnula = odds != null ? odds.getDouble("fora") : 0.00;
    }
}

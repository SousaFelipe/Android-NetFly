package net.kingbets.cambista.http.models.odds.principais;


import net.kingbets.cambista.http.models.odds.BaseODD;

import org.json.JSONException;
import org.json.JSONObject;


public class VencedorAmbasMarcam extends BaseODD {

    public static final String TIPO = "tt:vencedor_ambas_marcam";



    public double casaSim;
    public double casaNao;

    public double foraSim;
    public double foraNao;

    public double empateSim;
    public double empateNao;



    public VencedorAmbasMarcam(JSONObject odds) throws JSONException {
        super(odds);

        casaSim = odds != null ? odds.getDouble("casa_sim")     : 0.00;
        casaNao = odds != null ? odds.getDouble("casa_nao")     : 0.00;
        foraSim = odds != null ? odds.getDouble("fora_sim")     : 0.00;
        foraNao = odds != null ? odds.getDouble("fora_nao")     : 0.00;
        empateSim = odds != null ? odds.getDouble("empate_sim") : 0.00;
        empateNao = odds != null ? odds.getDouble("empate_nao") : 0.00;
    }
}

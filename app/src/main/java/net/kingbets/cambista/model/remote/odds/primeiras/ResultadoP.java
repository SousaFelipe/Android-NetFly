package net.kingbets.cambista.model.remote.odds.primeiras;


import net.kingbets.cambista.model.remote.odds.principais.Resultado;

import org.json.JSONException;
import org.json.JSONObject;


public class ResultadoP extends Resultado {

    public static final String TIPO = "pt_resultado";



    public ResultadoP(JSONObject odds) throws JSONException {
        super(odds);
    }
}

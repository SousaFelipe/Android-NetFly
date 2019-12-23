package net.kingbets.cambista.http.models.odds.primeiras;


import net.kingbets.cambista.http.models.odds.principais.Resultado;

import org.json.JSONException;
import org.json.JSONObject;


public class ResultadoP extends Resultado {

    public static final String TIPO = "pt:resultado";



    public ResultadoP(JSONObject odds) throws JSONException {
        super(odds);
    }
}

package net.kingbets.cambista.http.models.odds.segundas;


import net.kingbets.cambista.http.models.odds.principais.Resultado;

import org.json.JSONException;
import org.json.JSONObject;


public class ResultadoS extends Resultado {

    public static final String TIPO = "st:resultado";



    public ResultadoS(JSONObject odds) throws JSONException {
        super(odds);
    }
}

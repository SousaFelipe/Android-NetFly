package net.kingbets.cambista.model.remote.odds.segundas;


import net.kingbets.cambista.model.remote.odds.principais.Resultado;

import org.json.JSONException;
import org.json.JSONObject;


public class ResultadoS extends Resultado {

    public static final String TIPO = "st_resultado";



    public ResultadoS(JSONObject odds) throws JSONException {
        super(odds);
    }
}

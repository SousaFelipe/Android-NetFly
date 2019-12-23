package net.kingbets.cambista.http.models.odds.segundas;

import net.kingbets.cambista.http.models.odds.principais.ParImpar;

import org.json.JSONException;
import org.json.JSONObject;


public class ParImparS extends ParImpar {

    public static final String TIPO = "st:par_ou_impar";



    public ParImparS(JSONObject odds) throws JSONException {
        super(odds);
    }
}

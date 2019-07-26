package net.kingbets.cambista.model.remote.odds.segundas;

import net.kingbets.cambista.model.remote.odds.principais.ParImpar;

import org.json.JSONException;
import org.json.JSONObject;


public class ParImparS extends ParImpar {

    public static final String TIPO = "st:par_ou_impar";



    public ParImparS(JSONObject odds) throws JSONException {
        super(odds);
    }
}

package net.kingbets.cambista.http.models.odds.primeiras;

import net.kingbets.cambista.http.models.odds.principais.ParImpar;

import org.json.JSONException;
import org.json.JSONObject;


public class ParImparP extends ParImpar {

    public static final String TIPO = "pt:par_ou_impar";



    public ParImparP(JSONObject odds) throws JSONException {
        super(odds);
    }
}

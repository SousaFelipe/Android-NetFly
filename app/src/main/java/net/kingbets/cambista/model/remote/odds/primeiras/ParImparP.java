package net.kingbets.cambista.model.remote.odds.primeiras;

import net.kingbets.cambista.model.remote.odds.principais.ParImpar;

import org.json.JSONException;
import org.json.JSONObject;


public class ParImparP extends ParImpar {

    public static final String TIPO = "pt_par_ou_impar";



    public ParImparP(JSONObject odds) throws JSONException {
        super(odds);
    }
}

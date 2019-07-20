package net.kingbets.cambista.model.remote.futebol;


import net.kingbets.cambista.model.BaseModel;
import net.kingbets.cambista.model.remote.futebol.odds.principais.AmbasMarcam;
import net.kingbets.cambista.model.remote.futebol.odds.principais.Resultado;

import org.json.JSONException;
import org.json.JSONObject;


public class Partida extends BaseModel {



    public String casa;
    public String fora;
    public String inicio;



    public AmbasMarcam ambasMarcam;
    public Resultado resultado;



    public void setODDS(JSONObject odds) throws JSONException {
        JSONObject principais = odds.getJSONObject("principais");
        setPrincipais(principais);
    }



    private void setPrincipais(JSONObject principais) throws JSONException {

        if ( ! principais.isNull("ambas_marcam") ) {
            ambasMarcam = new AmbasMarcam(principais.getJSONObject("ambas_marcam"));
        }

        if ( ! principais.isNull("principal") ) {
            resultado = new Resultado(principais.getJSONObject("principal"));
        }
    }
}

package net.kingbets.cambista.model.local.apostas;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;

import net.kingbets.cambista.model.contracts.CambistaContract;
import net.kingbets.cambista.model.local.Cambista;
import net.kingbets.cambista.view.widgets.Widget;

import org.json.JSONArray;

import okhttp3.FormBody;
import okhttp3.RequestBody;


public class Single extends Cupom {



    private static Single instance = null;



    private CardView displayButton;
    private int lastSize = 0;



    private Single() {
        super();
    }



    public static synchronized Single instance() {
        if (instance == null) {
            instance = new Single();
        }
        return instance;
    }



    public void setDisplayButton(CardView displayButton) {
        this.displayButton = displayButton;
    }



    public void update(int min) {

        if (widgets.size() >= min && lastSize <= (min - 1)) {
            displayButton.setVisibility(View.VISIBLE);
        }
        else if (widgets.size() < min && lastSize >= min) {
            displayButton.setVisibility(View.GONE);
        }

        lastSize = widgets.size();
    }



    public RequestBody getPostData(Context context) {

        Cambista cambista = new CambistaContract(context).first();
        FormBody.Builder formBuilder = new FormBody.Builder();

        formBuilder.add("token", cambista.token);
        formBuilder.add("apostador", apostador);
        formBuilder.add("cotacao", String.valueOf(cotacao));
        formBuilder.add("quant_apostas", String.valueOf(quantApostas));
        formBuilder.add("valor_apostado", String.valueOf(valorApostado));
        formBuilder.add("possivel_retorno", String.valueOf(possivelRetorno));

        return formBuilder.build();
    }



    public JSONArray getApostasJSON() {
        JSONArray array = new JSONArray();

        for (Widget w : widgets) {
            array.put( w.getAposta().toJSON() );
        }

        return  array;
    }
}

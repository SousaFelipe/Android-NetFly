package net.kingbets.cambista.model.apostas;


import android.content.Context;

import net.kingbets.cambista.http.models.apostas.Bet;
import net.kingbets.cambista.http.models.apostas.BetStack;
import net.kingbets.cambista.model.BaseModel;
import net.kingbets.cambista.utils.Config;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;


public class Cupom extends BaseModel {



    private static final String AGUARDANDO   = "A";



    public String codigo;
    public long   cambista;
    public String apostador;
    public double cotacao;
    public int    quantApostas;
    public double valorApostado;
    public double possivelRetorno;
    public String status;
    public String data;
    public String hora;

    public List<Bet> bets;



    public Cupom() {
        bets = new ArrayList<>();
    }



    public void setValorApostado(double valorApostado) {
        this.valorApostado = (valorApostado > Config.LIMITE_VALOR) ? Config.LIMITE_VALOR : valorApostado;
    }



    public void updateCotacao() {

        List<Bet> stack = BetStack.instance().getStack();
        int stackSize = stack.size();

        quantApostas = stackSize;

        if (stackSize > 0) {

            double cotacao = 1;

            for (Bet b: stack)
                cotacao *= b.cotacao;

            this.cotacao = (cotacao > Config.LIMITE_COTACAO) ? Config.LIMITE_COTACAO : cotacao;
        }
        else {
            clear();
        }
    }



    public void updatePossivelRetorno() {
        this.possivelRetorno = (valorApostado * this.cotacao);
        this.possivelRetorno = (this.possivelRetorno > Config.LIMITE_PREMIO) ? Config.LIMITE_PREMIO : this.possivelRetorno;
    }



    public void clear() {
        cambista = 0;
        apostador = null;
        cotacao = 0.0;
        valorApostado = 0.0;
        possivelRetorno = 0.0;
        status = AGUARDANDO;
        data = null;
        hora = null;
    }



    public RequestBody getRequestBody(Context context) {

        FormBody.Builder formBuilder = new FormBody.Builder();

        formBuilder.add("device", Config.getDeviceInfo(context));
        formBuilder.add("apostador", apostador);
        formBuilder.add("valor_apostado", String.valueOf(valorApostado));

        return formBuilder.build();
    }
}

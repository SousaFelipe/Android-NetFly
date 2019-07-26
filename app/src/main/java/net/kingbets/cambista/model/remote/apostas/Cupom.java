package net.kingbets.cambista.model.remote.apostas;


import net.kingbets.cambista.model.BaseModel;
import net.kingbets.cambista.model.remote.futebol.Partida;
import net.kingbets.cambista.utils.Config;
import net.kingbets.cambista.view.widgets.Widget;

import java.util.ArrayList;
import java.util.List;


public class Cupom extends BaseModel {



    public static final String AGUARDANDO   = "A";
    public static final String GANHOU       = "G";
    public static final String PERDEU       = "P";


    public long   cambista;
    public String apostador;
    public double cotacao;
    public double valorApostado;
    public double possivelRetorno;
    public String status;
    public String data;
    public String hora;

    public List<Widget> widgets;



    Cupom() {
        this.widgets = new ArrayList<>();
    }



    public void addAposta(Widget widget) {
        widgets.add(widget);
        refresh();
    }



    public void removeAposta(Widget widget) {
        widgets.remove(widget);
        refresh();
    }



    public boolean mesmoWidget(Widget out) {
        boolean mesmo = false;

        if (widgets.size() > 0) {
            for (Widget in: widgets) {
                if (in.equals(out)) {
                    mesmo = true;
                    break;
                }
            }
        }

        return mesmo;
    }



    public boolean mesmaPartida(Partida partida) {
        boolean val = false;

        for (Widget w: widgets) {
            if (w.getAposta().partida.equals(partida)) {
                val = true;
                break;
            }
        }

        return val;
    }



    public void setValorApostado(double valorApostado) {
        this.valorApostado = (valorApostado > Config.LIMITE_VALOR) ? Config.LIMITE_VALOR : valorApostado;
    }



    public void refresh() {
        if (widgets.size() > 0) {

            double cotacao = 1;

            for (Widget w: widgets) {
                cotacao *= w.getCotacao();
            }

            this.cotacao = (cotacao > Config.LIMITE_COTACAO) ? Config.LIMITE_COTACAO : cotacao;
            this.possivelRetorno = (valorApostado * this.cotacao);

            possivelRetorno = (possivelRetorno > Config.LIMITE_PREMIO) ? Config.LIMITE_PREMIO : possivelRetorno;
        }
        else {
            clear();
        }
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
}

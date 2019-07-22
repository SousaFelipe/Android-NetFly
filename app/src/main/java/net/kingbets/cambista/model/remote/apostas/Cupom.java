package net.kingbets.cambista.model.remote.apostas;


import net.kingbets.cambista.model.BaseModel;
import net.kingbets.cambista.view.widgets.Widget;

import java.util.ArrayList;
import java.util.List;


public class Cupom extends BaseModel {



    public static final String GANHOU = "G";
    public static final String PERDEU = "P";


    public long         cambista;
    public long         cliente;
    public double       cotacao;
    public double       entrada;
    public double       saida;
    public double       comissao;
    public double       pontuacao;
    public String       status;

    public List<Widget> widgets;



    public Cupom() {
        this.widgets = new ArrayList<>();
    }



    public void addAposta(Widget widget) {
        widgets.add(widget);
    }



    public void removeAposta(Widget widget) {
        widgets.remove(widget);
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



    public boolean mesmaPartida(long partida) {
        boolean val = false;

        for (Widget w: widgets) {
            if (w.getAposta().partida == partida) {
                val = true;
                break;
            }
        }

        return val;
    }
}

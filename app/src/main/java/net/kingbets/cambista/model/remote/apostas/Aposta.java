package net.kingbets.cambista.model.remote.apostas;


import net.kingbets.cambista.model.BaseModel;
import net.kingbets.cambista.model.remote.futebol.Partida;


public class Aposta extends BaseModel {


    public static final String AGUARDANDO = "Aguardando";
    public static final String GANHOU = "Ganhou";
    public static final String PERDEU = "Perdeu";




    public long    cupom;
    public Partida partida;
    public String  titulo;
    public String  tipo;
    public String  sentenca;
    public double  cotacao;
    public String  status;



    public Aposta(String tipo) {
        this.tipo = tipo;
        this.status = AGUARDANDO;
    }



    public Aposta withPartida(Partida partida) {
        this.partida  = partida;
        return this;
    }
}

package net.kingbets.cambista.model.remote.apostas;


import net.kingbets.cambista.model.BaseModel;
import net.kingbets.cambista.model.remote.futebol.Partida;


public class Aposta extends BaseModel {


    public long     cupom;
    public Partida  partida;
    public String   titulo;
    public String   tipo;
    public String   sentenca;
    public double   cotacao;
    public String   status;
}

package net.kingbets.cambista.http.models.apostas;


import net.kingbets.cambista.model.BaseModel;
import net.kingbets.cambista.http.models.futebol.Partida;


public class Aposta extends BaseModel {


    public long     cupom;
    public Partida  partida;
    public String   titulo;
    public String   tipo;
    public String   sentenca;
    public double   cotacao;
    public String   status;
}

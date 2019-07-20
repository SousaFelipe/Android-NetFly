package net.kingbets.cambista.model.remote.futebol;


import net.kingbets.cambista.model.BaseModel;


public class Campeonato extends BaseModel {


    public String bandeira;
    public String titulo;
    public String serie;
    public String fim;
    public int partidas;


    public String getRoundFlag() {
        return "img_" + this.bandeira + "_round";
    }


    public String getSquareFlag() {
        return "img_" + this.bandeira + "_square";
    }
}

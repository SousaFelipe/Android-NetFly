package net.kingbets.cambista.model.remote.futebol;


import net.kingbets.cambista.model.BaseModel;


public class Campeonato extends BaseModel {


    public String bandeira;
    public String titulo;
    public String serie;
    public String fim;
    public int partidas;


    public String getFlag() {
        return "img_" + this.bandeira;
    }
}

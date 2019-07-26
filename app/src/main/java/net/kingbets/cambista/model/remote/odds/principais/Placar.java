package net.kingbets.cambista.model.remote.odds.principais;


import net.kingbets.cambista.model.remote.odds.BaseODD;


public class Placar extends BaseODD {


    public static final String TIPO = "tt:placar_exato";


    public String   tipo;
    public int      casa;
    public int      fora;
    public double   odds;
}

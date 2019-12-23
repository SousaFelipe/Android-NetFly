package net.kingbets.cambista.http.models.futebol;


import java.util.ArrayList;
import java.util.List;


public class CampeonatoPartidas {



    public long             id;
    public String           bandeira;
    public String           titulo;
    public List<Partida>    partidas;



    public CampeonatoPartidas() {
        partidas = new ArrayList<>();
    }



    public String getFlag() {
        return "img_" + this.bandeira;
    }
}

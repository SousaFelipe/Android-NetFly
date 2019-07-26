package net.kingbets.cambista.model.local;


import net.kingbets.cambista.model.BaseModel;


public class Perfil extends BaseModel {



    public String   gerente;
    public String   nome;
    public double   limite;
    public int      limiteApostas;
    public double   xp;
    public double   xpMaximo;



    public double xp() {
        return ((xp * 100) / xpMaximo);
    }
}

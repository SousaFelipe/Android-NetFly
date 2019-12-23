package net.kingbets.cambista.http.models.Sistema;


import net.kingbets.cambista.model.BaseModel;


public class Cartao extends BaseModel {



    public String codigo;
    public long cliente;
    public long cambista;
    public double valorInicial;
    public double valorAtual;
    public String validadoAte;
    public int quantBilhetses;
}

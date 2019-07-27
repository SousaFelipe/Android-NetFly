package net.kingbets.cambista.model.remote.apostas;


import net.kingbets.cambista.model.BaseModel;

import java.util.ArrayList;
import java.util.List;


public class Cupom extends BaseModel {



    public String codigo;
    public long   cambista;
    public String apostador;
    public double cotacao;
    public int    quantApostas;
    public double valorApostado;
    public double possivelRetorno;
    public double comissao;
    public String status;
    public String data;
    public String hora;



    public List<Aposta> apostas;



    public Cupom() {
        apostas = new ArrayList<>();
    }
}

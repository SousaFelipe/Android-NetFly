package net.kingbets.cambista.model.remote.Sistema;


public class Status {



    public String bloqueio;
    public double deposito;


    public boolean bloqueado() {
        return bloqueio != null && bloqueio.equals("S");
    }
}

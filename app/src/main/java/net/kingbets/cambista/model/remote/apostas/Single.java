package net.kingbets.cambista.model.remote.apostas;



public class Single extends Cupom {



    private static Single instance = null;



    private Single() {
        super();
    }



    public static synchronized Single instance() {
        if (instance == null) {
            instance = new Single();
        }
        return instance;
    }
}
